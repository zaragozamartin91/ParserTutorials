package parser;

import java.util.LinkedList;

import parser.token.Token;
import parser.tree.node.ConstantExpressionNode;
import parser.tree.node.ExpressionNode;
import parser.tree.node.VariableExpressionNode;

public class Parser {
	private LinkedList<Token> tokens = new LinkedList<>();
	private Token lookahead;

	public void parse(LinkedList<Token> tokens) {
		this.tokens = (LinkedList<Token>) tokens.clone();
		this.lookahead = this.tokens.getFirst();
		__print("begin: " + lookahead);

		// The parser will have one method for every non-terminal symbol of the
		// grammar that we designed in the last post. This means the method
		// expression() will parse the non-terminal symbol expression
		expression();

		// Once the expression has been parsed completely there should be no
		// symbols left in the list. This means that the lookahead should be
		// equal to Token.EPSILON
		if (lookahead.token != Token.EPSILON) {
			throw new ParserException("Unexpected symbol %s found", lookahead);
		}
	}

	private void __print(String s) {
		System.out.println("Parser::" + s);
	}

	private void nextToken() {
		__print("SEQUENCE: " + lookahead.sequence + " CONSUMED!");

		tokens.pop();
		if (tokens.isEmpty()) {
			lookahead = new Token(Token.EPSILON, "", -1);
		} else {
			lookahead = tokens.getFirst();
		}

		__print("nextToken: " + lookahead);
	}

	private void expression() {
		__print("expression -> signed_term sum_op");

		// expression -> signed_term sum_op
		signedTerm();
		sumOp();
	}

	private void sumOp() {
		// si el siguiente token es de tipo TERMINAL , entonces se debe
		// desapilar un token y continuar con la secuencia

		if (lookahead.token == Token.PLUSMINUS) {
			__print("sum_op -> PLUSMINUS term sum_op");

			// sum_op -> PLUSMINUS term sum_op
			nextToken();
			term();
			sumOp();
		} else {
			__print("sum_op -> EPSILON");
			// sum_op -> EPSILON
		}
	}

	private void term() {
		__print("term -> factor term_op");
		// term -> factor term_op
		factor();
		termOp();
	}

	private void termOp() {
		if (lookahead.token == Token.MULTDIV) {
			__print("term_op -> MULTDIV signed_factor term_op");
			// term_op -> MULTDIV signed_factor term_op
			nextToken();
			signedFactor();
			termOp();
		} else {
			__print("term_op -> EPSILON");
			// term_op -> EPSILON
		}
	}

	private void signedFactor() {
		if (lookahead.token == Token.PLUSMINUS) {
			__print("signed_factor -> PLUSMINUS factor");
			// signed_factor -> PLUSMINUS factor
			nextToken();
			factor();
		} else {
			__print("signed_factor -> factor");
			// signed_factor -> factor
			factor();
		}
	}

	private void factor() {
		__print("factor -> argument factor_op");
		// factor -> argument factor_op
		argument();
		factorOp();
	}

	private void factorOp() {
		if (lookahead.token == Token.RAISED) {
			__print("factor_op -> RAISED expression");
			// factor_op -> RAISED expression
			nextToken();
			expression();
		} else {
			__print("factor_op -> EPSILON");
			// factor_op -> EPSILON
		}
	}

	private void argument() {
		if (lookahead.token == Token.FUNCTION) {
			__print("argument -> FUNCTION argument");
			// argument -> FUNCTION argument
			nextToken();
			argument();
		} else if (lookahead.token == Token.OPEN_BRACKET) {
			__print("argument -> OPEN_BRACKET expression CLOSE_BRACKET");
			// argument -> OPEN_BRACKET expression CLOSE_BRACKET
			nextToken();
			expression();

			if (lookahead.token != Token.CLOSE_BRACKET) {
				throw new ParserException("Close brackets expected, " + lookahead.sequence + " found instead!");
			}

			nextToken();
		} else {
			__print("argument -> value");
			// argument -> value
			value();
		}
	}

	private ExpressionNode value() {
		if (lookahead.token == Token.NUMBER) {
			__print("value -> NUMBER");
			// value -> NUMBER
			ExpressionNode expr = new ConstantExpressionNode(lookahead.sequence);
			nextToken();
			return expr;
		}

		if (lookahead.token == Token.VARIABLE) {
			__print("value -> VARIABLE");
			// value -> VARIABLE
			ExpressionNode expr = new VariableExpressionNode(lookahead.sequence);
			nextToken();
			return expr;
		}

		if (lookahead.token == Token.EPSILON)
			throw new ParserException("Unexpected end of input");
		else
			throw new ParserException("Unexpected symbol %s found", lookahead);
	}

	private void signedTerm() {
		// there are two possible rules. If the next token is PLUSMINUS we can
		// eat it up and then parse the non-terminal term. Otherwise we parse
		// the non-terminal term directly

		if (lookahead.token == Token.PLUSMINUS) {
			__print("signedTerm -> PLUSMINUS term");
			// signedTerm -> PLUSMINUS term
			nextToken();
			term();
		} else {
			__print("signedTerm -> term");
			// signedTerm -> term
			term();
		}
	}
}
