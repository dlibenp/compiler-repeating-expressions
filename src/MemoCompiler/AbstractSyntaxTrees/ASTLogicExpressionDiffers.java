package MemoCompiler.AbstractSyntaxTrees;

/**
 * Clase agregada para las condiciones.
 *
 * @author Gandalf
 */
public class ASTLogicExpressionDiffers extends ASTLogicExpressionComparison {

    public ASTLogicExpressionDiffers(ASTLogicExpression izq, ASTLogicExpression der, int line) {
        super(izq, der, line);
    }

    public Object Visit(Visitor visitor) {
        return visitor.visit(this);
    }
}
