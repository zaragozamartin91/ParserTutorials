package parser.token;

import static parser.token.Token.CLOSE_BRACKET;
import static parser.token.Token.FUNCTION;
import static parser.token.Token.MULTDIV;
import static parser.token.Token.NUMBER;
import static parser.token.Token.OPEN_BRACKET;
import static parser.token.Token.PLUSMINUS;
import static parser.token.Token.RAISED;
import static parser.token.Token.VARIABLE;

public class StandardTokenizerBuilder implements TokenizerBuilder {
	@Override
	public Tokenizer buildTokenizer() {
		Tokenizer tokenizer = new Tokenizer();
		tokenizer.add("sin|cos|exp|ln|sqrt", FUNCTION); // function
		tokenizer.add("\\(", OPEN_BRACKET); // open bracket
		tokenizer.add("\\)", CLOSE_BRACKET); // close bracket
		tokenizer.add("[+-]", PLUSMINUS); // plus or minus
		tokenizer.add("[*/]", MULTDIV); // mult or divide
		tokenizer.add("\\^", RAISED); // raised
		tokenizer.add("[0-9]+", NUMBER); // integer number
		tokenizer.add("[a-zA-Z][a-zA-Z0-9_]*", VARIABLE); // variable

		return tokenizer;
	}

	protected StandardTokenizerBuilder() {
	}

	public static StandardTokenizerBuilder create() {
		return new StandardTokenizerBuilder();
	}
}
