package parser.tree.node;

import parser.tree.node.impl.AdditionExpressionNode;
import parser.tree.node.impl.ConstantExpressionNode;
import parser.tree.node.impl.ExponentiationExpressionNode;
import parser.tree.node.impl.FunctionExpressionNode;
import parser.tree.node.impl.MultiplicationExpressionNode;
import parser.tree.node.impl.VariableExpressionNode;

public interface ExpressionNodeVisitor {
	public void visit(VariableExpressionNode node);

	public void visit(ConstantExpressionNode node);

	public void visit(AdditionExpressionNode node);

	public void visit(MultiplicationExpressionNode node);

	public void visit(ExponentiationExpressionNode node);

	public void visit(FunctionExpressionNode node);
}