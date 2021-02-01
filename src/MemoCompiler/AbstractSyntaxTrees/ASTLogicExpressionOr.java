/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MemoCompiler.AbstractSyntaxTrees;

/**
 * @author Gandalf
 */
public class ASTLogicExpressionOr extends ASTLogicExpressionBinary {

    public ASTLogicExpressionOr(ASTLogicExpression izq, ASTLogicExpression der, int line) {
        super(izq, der, line);
    }

    public Object Visit(Visitor visitor) {
        return visitor.visit(this);
    }
}
