
package MemoCompiler.AbstractSyntaxTrees;


public class ASTInstructionWrite extends ASTInstruction {

    private ASTLogicExpression expresion;

    public ASTInstructionWrite(ASTLogicExpression expresion, int line) {
        super(line);
        this.expresion = expresion;
    }

    public ASTLogicExpression getExpresion() {
        return expresion;
    }

    @Override
    public Object Visit(Visitor visitor) {
       return visitor.visit(this);
    }
}
