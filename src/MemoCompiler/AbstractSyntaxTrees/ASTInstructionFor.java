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
public class ASTInstructionFor extends ASTInstruction{
    private ASTInstruction primera;
    private ASTLogicExpression expression;
    private ASTLogicExpression ultima;
    private ASTInstruction instruccion;

    public ASTInstructionFor(ASTInstruction primera, ASTLogicExpression expression, 
            ASTLogicExpression ultima, ASTInstruction instruccion,int line) {
        super(line);
        this.primera = primera;
        this.expression = expression;
        this.ultima = ultima;
        this.instruccion = instruccion;
    }

    @Override
    public Object Visit(Visitor visitor) {
        return visitor.visit(this);
    }

    /**
     * @return the primera
     */
    public ASTInstruction getPrimera() {
        return primera;
    }

    /**
     * @return the expression
     */
    public ASTLogicExpression getExpression() {
        return expression;
    }

    /**
     * @return the ultima
     */
    public ASTLogicExpression getUltima() {
        return ultima;
    }

    /**
     * @return the instruccion
     */
    public ASTInstruction getInstruccion() {
        return instruccion;
    }
    
}
