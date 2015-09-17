package parser.tree.node;

import static org.junit.Assert.*;

import org.junit.Test;

public class ExpressionNodeTest {

	private static final double DELTA = 0.0000000000000000001;

	@Test
	public void testGetValue() {
		// testeando 3*2^4 - sqrt(1+3)

		SequenceExpressionNode add1 = new AdditionExpressionNode();
		add1.add(new ConstantExpressionNode(1), true);
		add1.add(new ConstantExpressionNode(3), true);
		assertEquals(4.0, add1.getValue(), DELTA);

		ExponentiationExpressionNode exp = new ExponentiationExpressionNode(add1,
				new ConstantExpressionNode(0.5));
		assertEquals(2.0, exp.getValue(), DELTA);

		ExpressionNode sqrt = new FunctionExpressionNode(FunctionExpressionNode.SQRT, add1);
		assertEquals(2.0, sqrt.getValue(), DELTA);

		exp = new ExponentiationExpressionNode(new ConstantExpressionNode(2.0),
				new ConstantExpressionNode(4.0));
		assertEquals(16.0, exp.getValue(), DELTA);

		SequenceExpressionNode mult = new MultiplicationExpressionNode();
		mult.add(new ConstantExpressionNode(3.0), true);
		mult.add(exp, true);
		assertEquals(48.0, mult.getValue(), DELTA);

		SequenceExpressionNode add2 = new AdditionExpressionNode();
		add2.add(mult, true);
		add2.add(sqrt, false);
		assertEquals(46.0, add2.getValue(), DELTA);
	}

}
