package parser.tree.node;

public class Term {
	public final boolean positive;
	public final ExpressionNode expression;

	public Term(boolean positive, ExpressionNode expression) {
		super();
		this.positive = positive;
		this.expression = expression;
	}
}
