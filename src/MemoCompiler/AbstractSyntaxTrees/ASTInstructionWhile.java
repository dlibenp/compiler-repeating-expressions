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
public class ASTInstructionWhile extends ASTInstruction{
    private ASTLogicExpression expression;
    private ASTInstruction _do;

    public ASTInstructionWhile(ASTLogicExpression expression, ASTInstruction _do, int line) {
        super(line);
        this.expression = expression;
        this._do = _do;
    }

    @Override
    public Object Visit(Visitor visitor) {
        return visitor.visit(this);
    }

    /**
     * @return the expression
     */
    public ASTLogicExpression getExpression() {
        return expression;
    }

    /**
     * @return the _do
     */
    public ASTInstruction getDo() {
        return _do;
    }
    
}
