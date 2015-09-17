package parser.tree.node;

import java.util.LinkedList;

/**
 * Describe una secuencia de operaciones a realizar como secuencia de
 * sumas/restas o multiplicaciones/divisiones.
 * 
 * @author martin
 *
 */
public abstract class SequenceExpressionNode implements ExpressionNode {
	protected LinkedList<Term> terms = new LinkedList<Term>();;

	public SequenceExpressionNode() {
	}

	public SequenceExpressionNode(ExpressionNode a, boolean positive) {
		this.terms.add(new Term(positive, a));
	}

	public void add(ExpressionNode a, boolean positive) {
		this.terms.add(new Term(positive, a));
	}
}
