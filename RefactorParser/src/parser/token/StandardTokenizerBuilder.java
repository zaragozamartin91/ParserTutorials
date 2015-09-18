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
		tokenizer.add("sin|cos|exp|ln|sqrt", FUNCTION); 
		tokenizer.add("\\(", OPEN_BRACKET); 
		tokenizer.add("\\)", CLOSE_BRACKET);
		tokenizer.add("[+-]", PLUSMINUS); 
		tokenizer.add("[*/]", MULTDIV); 
		tokenizer.add("\\^", RAISED);
		tokenizer.add("[0-9]*\\.?[0-9]+", NUMBER); 
		tokenizer.add("[a-zA-Z][a-zA-Z0-9_]*", VARIABLE); 

		return tokenizer;
	}

	protected StandardTokenizerBuilder() {
	}

	public static StandardTokenizerBuilder create() {
		return new StandardTokenizerBuilder();
	}
}
