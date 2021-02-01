package MemoCompiler.AbstractSyntaxTrees;

import MemoCompiler.MemoTypes;

/**
 * Clase agregada para las condiciones.
 * 
 * @author Gandalf
 */
public abstract class ASTLogicExpression extends AST {
    private MemoTypes type;

    public MemoTypes getType() {
        return type;
    }

    public void setType(MemoTypes type) {
        this.type = type;
    }
    public ASTLogicExpression(int line) {
        super(line);
    }
}
