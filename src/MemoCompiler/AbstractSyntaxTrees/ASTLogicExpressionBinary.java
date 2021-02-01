package MemoCompiler.AbstractSyntaxTrees;



/**
 * Clase agregada para las condiciones.
 * 
 * @author Gandalf
 */
public abstract class ASTLogicExpressionBinary extends ASTLogicExpression {
    private ASTLogicExpression izq;
    private ASTLogicExpression der;

    public ASTLogicExpression getDer() {
        return der;
    }

    public ASTLogicExpression getIzq() {
        return izq;
    }

    public ASTLogicExpressionBinary(ASTLogicExpression izq, ASTLogicExpression der, int line) {
        super(line);
        this.izq = izq;
        this.der = der;
    }
}
