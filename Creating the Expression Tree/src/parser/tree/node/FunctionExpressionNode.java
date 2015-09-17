package parser.tree.node;

import parser.ParserException;

public class FunctionExpressionNode implements ExpressionNode {
	public static final int SIN = 1;
	public static final int COS = 2;
	public static final int TAN = 3;

	public static final int ASIN = 4;
	public static final int ACOS = 5;
	public static final int ATAN = 6;

	public static final int SQRT = 7;
	public static final int EXP = 8;

	public static final int LN = 9;
	public static final int LOG = 10;
	public static final int LOG2 = 11;

	private int function;
	private ExpressionNode argument;

	public FunctionExpressionNode(int function, ExpressionNode argument) {
		super();
		this.function = function;
		this.argument = argument;
	}

	public int getType() {
		return ExpressionNode.FUNCTION_NODE;
	}

	// TODO: REFACTORIZAR USANDO LAMBDAS Y MAPAS
	public double getValue() {
		switch (function) {
		case SIN:
			return Math.sin(argument.getValue());
		case COS:
			return Math.cos(argument.getValue());
		case TAN:
			return Math.tan(argument.getValue());
		case ASIN:
			return Math.asin(argument.getValue());
		case ACOS:
			return Math.acos(argument.getValue());
		case ATAN:
			return Math.atan(argument.getValue());
		case SQRT:
			return Math.sqrt(argument.getValue());
		case EXP:
			return Math.exp(argument.getValue());
		case LN:
			return Math.log(argument.getValue());
		case LOG:
			return Math.log(argument.getValue()) * 0.43429448190325182765;
		case LOG2:
			return Math.log(argument.getValue()) * 1.442695040888963407360;
		}
		throw new EvaluationException("Invalid function id " + function + "!");
	}

	public static int stringToFunction(String str) {
		if (str.equals("sin"))
			return SIN;
		if (str.equals("cos"))
			return COS;
		if (str.equals("tan"))
			return TAN;

		if (str.equals("asin"))
			return ASIN;
		if (str.equals("acos"))
			return ACOS;
		if (str.equals("atan"))
			return ATAN;

		if (str.equals("sqrt"))
			return SQRT;
		if (str.equals("exp"))
			return EXP;

		if (str.equals("ln"))
			return LN;
		if (str.equals("log"))
			return LOG;
		if (str.equals("log2"))
			return LOG2;

		throw new ParserException("Unexpected Function " + str + " found!");
	}

	// The method getAllFunctions() returns a string containing all supported
	// functions, separated by a |. This string may be used by the tokenizer to
	// create a regular expression that identifies functions inside a string.
	public static String getAllFunctions() {
		return "sin|cos|tan|asin|acos|atan|sqrt|exp|ln|log|log2";
	}

}
