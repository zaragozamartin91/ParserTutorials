package parser.tree.node;

public class ConstantExpressionNode implements ExpressionNode {
	private double value;

	@Override
	public int getType() {
		return CONSTANT_NODE;
	}

	@Override
	public double getValue() {
		return value;
	}

	public ConstantExpressionNode(double value) {
		super();
		this.value = value;
	}

	public ConstantExpressionNode(String s_val) {
		this.value = Double.valueOf(s_val);
	}
}
