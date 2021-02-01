package MemoCompiler.AbstractSyntaxTrees;

/**
 * Clase agregada para las condiciones.
 *
 * @author Gandalf
 */
public class ASTLogicExpressionGreaterThan extends ASTLogicExpressionComparison {

    public ASTLogicExpressionGreaterThan(ASTLogicExpression izq, ASTLogicExpression der, int line) {
        super(izq, der, line);
    }

    public Object Visit(Visitor visitor) {
        return visitor.visit(this);
    }
}
