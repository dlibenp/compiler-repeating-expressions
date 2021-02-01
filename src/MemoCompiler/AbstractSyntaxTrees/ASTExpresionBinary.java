package MemoCompiler.AbstractSyntaxTrees;

public abstract class ASTExpresionBinary extends ASTExpresion {
    private ASTLogicExpression izq;
    private ASTLogicExpression der;

    public ASTLogicExpression getDer() {
        return der;
    }

    public ASTLogicExpression getIzq() {
        return izq;
    }

    public ASTExpresionBinary(ASTLogicExpression izq, ASTLogicExpression der, int line) {
        super(line);
        this.izq = izq;
        this.der = der;
    }
}
