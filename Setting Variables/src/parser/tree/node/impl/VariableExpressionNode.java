package parser.tree.node.impl;

import parser.tree.node.EvaluationException;
import parser.tree.node.ExpressionNode;
import parser.tree.node.ExpressionNodeVisitor;

public class VariableExpressionNode implements ExpressionNode {
	private String name;
	private double value;
	private boolean valueSet;

	public VariableExpressionNode(String name) {
		this.name = name;
		valueSet = false;
	}

	public int getType() {
		return ExpressionNode.VARIABLE_NODE;
	}

	public void setValue(double value) {
		this.value = value;
		this.valueSet = true;
	}

	public double getValue() {
		if (valueSet)
			return value;
		else
			throw new EvaluationException("Variable '" + name + "' was not initialized.");
	}

	public void accept(ExpressionNodeVisitor visitor) {
		visitor.visit(this);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isValueSet() {
		return valueSet;
	}
}
