package MemoCompiler.AbstractSyntaxTrees;

import MemoCompiler.MemoTypes;

/**
 * Clase agregada para las condiciones.
 * 
 * @author Gandalf
 */
public abstract class ASTLogicExpressionComparison extends ASTLogicExpression {
    private MemoTypes type;
    private ASTLogicExpression izq;
    private ASTLogicExpression der;

    public MemoTypes getType() {
        return type;
    }

    public void setType(MemoTypes type) {
        this.type = type;
    }

    public ASTLogicExpression getDer() {
        return der;
    }

    public ASTLogicExpression getIzq() {
        return izq;
    }

    public ASTLogicExpressionComparison(ASTLogicExpression izq, ASTLogicExpression der, int line) {
        super(line);
        this.izq = izq;
        this.der = der;
    }
}
