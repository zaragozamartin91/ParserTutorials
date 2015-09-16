package parser.tree.node;

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

}
