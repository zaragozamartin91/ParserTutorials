package parser.tree.node;

public class AdditionExpressionNode extends SequenceExpressionNode {
	public AdditionExpressionNode() {
		super();
	}

	public AdditionExpressionNode(ExpressionNode a, boolean positive) {
		super(a, positive);
	}

	@Override
	public int getType() {
		return ADDITION_NODE;
	}

	@Override
	public double getValue() {
		double sum = 0.0;

		for (Term term : terms) {
			if (term.positive) {
				sum += term.expression.getValue();
			} else {
				sum -= term.expression.getValue();
			}
		}

		return sum;
	}
}
