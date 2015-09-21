package parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static parser.token.Token.*;

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
			tokenizer.add("[*]", MULT); // mult or divide
			tokenizer.add("[/]", DIV); // mult or divide
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
	public void testParseUsingSheetColumnRowVariables() {
		System.out.println("testParseUsingSheetColumnRowVariables----------------------------------------------------------------------------------------");

		try {
			Tokenizer tokenizer = new Tokenizer();
			tokenizer.add("sin|cos|exp|ln|sqrt", FUNCTION); // function
			tokenizer.add("\\(", OPEN_BRACKET); // open bracket
			tokenizer.add("\\)", CLOSE_BRACKET); // close bracket
			tokenizer.add("[+-]", PLUSMINUS); // plus or minus
			tokenizer.add("[*]", MULT); // mult or divide
			tokenizer.add("[/]", DIV); // mult or divide
			tokenizer.add("\\^", RAISED); // raised
			tokenizer.add("[0-9]+", NUMBER); // integer number
//			tokenizer.add("[a-zA-Z][a-zA-Z0-9_]*", VARIABLE); // variable
			tokenizer.add("\\w+![A-Z]\\d+",VARIABLE);
			
			Parser parser = new Parser(tokenizer);
			ExpressionNode expr = parser.parse(" 17 + 2 - hoja1!C22 ");
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
			assertEquals(50.0, result, DELTA);
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
			assertEquals(1.0, result, DELTA);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testParseUsingVariablesAndStaticTokenizerConstructor() {
		System.out.println("testParseUsingVariablesAndStaticTokenizerConstructor----------------------------------------------------------------------------------------");

		try {
			Tokenizer tokenizer = Tokenizer.createStandardExpressionTokenizer();

			Parser parser = new Parser(tokenizer);
			ExpressionNode expr = parser.parse("sin(pi/2)");
			expr.accept(new SetVariable("pi", Math.PI));

			double result = expr.getValue();
			System.out.println("result is " + result);
			assertEquals(1.0, result, DELTA);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	
	@Test
	public void testParseComplexExpression() {
		System.out.println("testParseComplexExpression----------------------------------------------------------------------------------------");

		try {
			Tokenizer tokenizer = Tokenizer.createStandardExpressionTokenizer();

			Parser parser = new Parser(tokenizer);
			ExpressionNode expr = parser.parse("(3^2 -2 +7) / 7");

			double result = expr.getValue();
			System.out.println("result is " + result);
			assertEquals(2.0, result, DELTA);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	
	@Test
	public void testParseMultipleAdditionsSubstractionsMultsAndDivs() {
		System.out.println("testParseMultipleAdditionsSubstractionsMultsAndDivs----------------------------------------------------------------------------------------");

		try {
			Tokenizer tokenizer = Tokenizer.createStandardExpressionTokenizer();

			Parser parser = new Parser(tokenizer);
			ExpressionNode expr = parser.parse("(12.0 + 0.25 + 0.75 - 0.5 - 0.5 -11.0) * 3 * 2.0 * 2 / 4.0 / 3.0 * 98.98");

			double result = expr.getValue();
			System.out.println("result is " + result);
			assertEquals(98.98, result, DELTA);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}
