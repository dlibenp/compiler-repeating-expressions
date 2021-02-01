/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MemoCompiler.AbstractSyntaxTrees;

/**
 *
 * @author orisha
 */
public class ASTInstructionDoWhile extends ASTInstructionWhile{
    
    public ASTInstructionDoWhile(ASTLogicExpression expression, ASTInstruction _do, int line) {
        super(expression, _do, line);
    }
    
    @Override
    public Object Visit(Visitor visitor) {
        return visitor.visit(this);
    }
    
}
