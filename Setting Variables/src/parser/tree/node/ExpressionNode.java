package parser.tree.node;

public interface ExpressionNode {
	// los nodos se distinguen unicamente por precedencia o por cantidad de
	// operandos por ejemplo, ADDITION_NODE hace referencia a sumas y restas,
	// MULTIPLICATION_NODE hace referencia a multis y divs

	public static final int VARIABLE_NODE = 1;
	public static final int CONSTANT_NODE = 2;
	public static final int ADDITION_NODE = 3;
	public static final int MULTIPLICATION_NODE = 4;
	public static final int EXPONENTIATION_NODE = 5;
	public static final int FUNCTION_NODE = 6;

	public int getType();

	public double getValue();
	
	public void accept(ExpressionNodeVisitor visitor);
}
