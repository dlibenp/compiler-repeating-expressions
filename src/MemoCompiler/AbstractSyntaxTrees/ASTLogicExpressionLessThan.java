package MemoCompiler.AbstractSyntaxTrees;

/**
 * Clase agregada para las condiciones.
 *
 * @author Gandalf
 */
public class ASTLogicExpressionLessThan extends ASTLogicExpressionComparison {

    public ASTLogicExpressionLessThan(ASTLogicExpression izq, ASTLogicExpression der, int line) {
        super(izq, der, line);
    }

    public Object Visit(Visitor visitor) {
        return visitor.visit(this);
    }
}
