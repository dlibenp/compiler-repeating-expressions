/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MemoCompiler.AbstractSyntaxTrees;

/**
 *
 * @author Administrator
 */
public class ASTExpresionSuma extends ASTExpresionBinary{

    public ASTExpresionSuma(ASTLogicExpression izq, ASTLogicExpression der, int line) {
        super(izq, der, line);
    }    

    @Override
    public Object Visit(Visitor visitor) {
        return visitor.visit(this);
    }
    
}
