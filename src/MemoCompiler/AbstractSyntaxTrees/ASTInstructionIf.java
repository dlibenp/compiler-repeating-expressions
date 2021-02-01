package MemoCompiler.AbstractSyntaxTrees;

/**
 * Clase agregada para las condiciones.
 *
 * @author Gandalf
 */
public class ASTInstructionIf extends ASTInstruction {

    private ASTLogicExpression condition;
    private ASTInstruction ifInstruction;
    private ASTInstruction elseInstruction;

    public ASTLogicExpression getCondition() {
        return condition;
    }

    public ASTInstruction getIfInstruction() {
        return ifInstruction;
    }

    public ASTInstruction getElseInstruction() {
        return elseInstruction;
    }

    public ASTInstructionIf(ASTLogicExpression condition, ASTInstruction ifInstruction, ASTInstruction elseInstruction, int line) {
        super(line);
        this.condition = condition;
        this.ifInstruction = ifInstruction;
        this.elseInstruction = elseInstruction;
    }

    public Object Visit(Visitor visitor) {
        return visitor.visit(this);
    }
}
