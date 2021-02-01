/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MemoCompiler.AbstractSyntaxTrees;

/**
 * Clase agregada para las condiciones.
 * 
 * @author Gandalf
 */
public class ASTLogicExpressionAnd extends ASTLogicExpressionBinary {

    public ASTLogicExpressionAnd(ASTLogicExpression izq, ASTLogicExpression der, int line) {
        super(izq, der, line);
    }

    public Object Visit(Visitor visitor) {
        return visitor.visit(this);
    }
}
