package parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static parser.token.Token.CLOSE_BRACKET;
import static parser.token.Token.FUNCTION;
import static parser.token.Token.MULTDIV;
import static parser.token.Token.NUMBER;
import static parser.token.Token.OPEN_BRACKET;
import static parser.token.Token.PLUSMINUS;
import static parser.token.Token.RAISED;
import static parser.token.Token.VARIABLE;

import org.junit.Test;

import parser.token.StandardTokenizerBuilder;
import parser.token.Tokenizer;
import parser.tree.node.ExpressionNode;
import parser.tree.node.impl.SetVariable;

public class ParserTest {

	private static final double DELTA = 0.000000001;

	@Test
	public void testParse() {
		System.out.println("testParse----------------------------------------------------------------------------------------");

		try {
			Tokenizer tokenizer = new Tokenizer();
			tokenizer.add("sin|cos|exp|ln|sqrt", FUNCTION); // function
			tokenizer.add("\\(", OPEN_BRACKET); // open bracket
			tokenizer.add("\\)", CLOSE_BRACKET); // close bracket
			tokenizer.add("[+-]", PLUSMINUS); // plus or minus
			tokenizer.add("[*/]", MULTDIV); // mult or divide
			tokenizer.add("\\^", RAISED); // raised
			tokenizer.add("[0-9]+", NUMBER); // integer number
			tokenizer.add("[a-zA-Z][a-zA-Z0-9_]*", VARIABLE); // variable

			Parser parser = new Parser(tokenizer);
			parser.parse(" sin(x) * (1 + var_12) ");
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testParseAndGetValue() {
		System.out.println("testParseAndGetValue----------------------------------------------------------------------------------------");

		try {
			Tokenizer tokenizer = StandardTokenizerBuilder.create().buildTokenizer();

			Parser parser = new Parser(tokenizer);
			ExpressionNode expr = parser.parse("3*2^4 + sqrt(1+3)");
			double result = expr.getValue();
			System.out.println("value is " + result);
			assertEquals(50.0, result , DELTA);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testParseUsingVariables() {
		System.out.println("testParseUsingVariables----------------------------------------------------------------------------------------");

		try {
			Tokenizer tokenizer = StandardTokenizerBuilder.create().buildTokenizer();

			Parser parser = new Parser(tokenizer);
			ExpressionNode expr = parser.parse("sin(pi/2)");
			expr.accept(new SetVariable("pi", Math.PI));

			double result = expr.getValue();
			System.out.println("result is " + result);
			assertEquals(1.0, result , DELTA);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}
