
package MemoCompiler.AbstractSyntaxTrees;

public class ASTInstructionAssig extends ASTInstruction{
    private ASTIdentifier id;
    private ASTLogicExpression expresion;    
    
    public ASTInstructionAssig(ASTIdentifier id, ASTLogicExpression expresion, int line){
        super(line);
        this.id        = id;
        this.expresion = expresion;
    }
    
    @Override
    public Object Visit(Visitor visitor) {
        return visitor.visit(this);
    }
    
    public ASTLogicExpression getExpresion() {
        return expresion;
    }

    public ASTIdentifier getId() {
        return id;
    }

}
