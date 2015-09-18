package parser.token;

import static org.junit.Assert.*;
import jdk.nashorn.internal.runtime.ParserException;

import org.junit.Test;

public class TokenizerTest {

	@Test
	public void testTokenize() {

		// If you paid close attention you might have realized that the regular
		// expression for variable tokens also matches any function token. This
		// is not a bug. By storing the token information in a linked list, and
		// always ensuring that we look for matches from the beginning of the
		// list, we are giving precedence to patterns added first

		Tokenizer tokenizer = new Tokenizer();
		tokenizer.add("sin|cos|exp|ln|sqrt", 1); // function
		tokenizer.add("\\(", 2); // open bracket
		tokenizer.add("\\)", 3); // close bracket
		tokenizer.add("[+-]", 4); // plus or minus
		tokenizer.add("[*/]", 5); // mult or divide
		tokenizer.add("\\^", 6); // raised
		tokenizer.add("[0-9]+", 7); // integer number
		tokenizer.add("[a-zA-Z][a-zA-Z0-9_]*", 8); // variable

		try {
			tokenizer.tokenize(" sin(x) * (1 + var_12) ");

			for (Token tok : tokenizer.getTokens()) {
				System.out.println("" + tok.token + " " + tok.sequence);
			}
		} catch (ParserException e) {
			System.out.println(e.getMessage());
			fail();
		}
	}

}
