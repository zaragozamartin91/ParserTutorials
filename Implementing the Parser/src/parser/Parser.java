package parser;

import java.util.LinkedList;

import jdk.nashorn.internal.runtime.ParserException;
import parser.token.Token;

public class Parser {
	private LinkedList<Token> tokens = new LinkedList<>();
	private Token lookahead;

	public void parse(LinkedList<Token> tokens) {
		this.tokens = (LinkedList<Token>) tokens.clone();
		this.lookahead = this.tokens.getFirst();

		// The parser will have one method for every non-terminal symbol of the
		// grammar that we designed in the last post. This means the method
		// expression() will parse the non-terminal symbol expression
		expression();

		// Once the expression has been parsed completely there should be no
		// symbols left in the list. This means that the lookahead should be
		// equal to Token.EPSILON
		if (lookahead.token != Token.EPSILON) {
			throw new ParserException("Unexpected symbol " + lookahead + " found");
		}
	}

	private void nextToken() {
		tokens.pop();
		if (tokens.isEmpty()) {
			lookahead = new Token(Token.EPSILON, "", -1);
		} else {
			lookahead = tokens.getFirst();
		}
	}

	private void expression() {
	    // expression -> signed_term sum_op
		signedTerm();
		sumOp();
	}

	private void sumOp() {
		// TODO Auto-generated method stub
		
	}

	private void signedTerm() {
		// TODO Auto-generated method stub
		
	}
}
