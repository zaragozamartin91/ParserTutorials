package parser.tree.node.impl;

import parser.tree.node.ExpressionNodeVisitor;

public class SetVariable implements ExpressionNodeVisitor {
	private String name;
	private Double value;

	public SetVariable(String name, Double value) {
		super();
		this.name = name;
		this.value = value;
	}

	@Override
	public void visit(VariableExpressionNode node) {
		if (node.getName().equals(name)) {
			node.setValue(value);
		}
	}

	@Override
	public void visit(ConstantExpressionNode node) {
	}

	@Override
	public void visit(AdditionExpressionNode node) {
	}

	@Override
	public void visit(MultiplicationExpressionNode node) {
	}

	@Override
	public void visit(ExponentiationExpressionNode node) {
	}

	@Override
	public void visit(FunctionExpressionNode node) {
	}
}
