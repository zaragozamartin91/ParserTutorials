package parser.token;

import static parser.token.Token.*;

public class StandardTokenizerBuilder implements TokenizerBuilder {
	@Override
	public Tokenizer buildTokenizer() {
		Tokenizer tokenizer = new Tokenizer();
		tokenizer.add("sin|cos|exp|ln|sqrt", FUNCTION); 
		tokenizer.add("\\(", OPEN_BRACKET); 
		tokenizer.add("\\)", CLOSE_BRACKET);
		tokenizer.add("[+-]", PLUSMINUS); 
		tokenizer.add("[*]", Token.MULT);
		tokenizer.add("[/]", Token.DIV);
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
