package parser.tree.node.impl;

import parser.tree.node.ExpressionNode;
import parser.tree.node.ExpressionNodeVisitor;

public class ExponentiationExpressionNode implements ExpressionNode {
	private ExpressionNode base;
	private ExpressionNode exponent;

	public ExponentiationExpressionNode(ExpressionNode base, ExpressionNode exponent) {
		super();
		this.base = base;
		this.exponent = exponent;
	}

	@Override
	public int getType() {
		return EXPONENTIATION_NODE;
	}

	@Override
	public double getValue() {
		return Math.pow(base.getValue(), exponent.getValue());
	}

	@Override
	public void accept(ExpressionNodeVisitor visitor) {
		visitor.visit(this);
		base.accept(visitor);
		exponent.accept(visitor);
	}

}
