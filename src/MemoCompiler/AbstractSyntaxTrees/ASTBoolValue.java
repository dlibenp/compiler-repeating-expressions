package MemoCompiler.AbstractSyntaxTrees;

/**
 * Clase agregada para las condiciones.
 *
 * @author Gandalf
 */
public class ASTBoolValue extends ASTSymbol {

    public ASTBoolValue(String lexeme, int line) {
        super(-1, lexeme, line);
    }

    public Object Visit(Visitor visitor) {
        return visitor.visit(this);
    }
}
