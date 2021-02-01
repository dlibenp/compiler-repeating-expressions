

package MemoCompiler.AbstractSyntaxTrees;


public class ASTIdentifierReference extends ASTIdentifier{

    public ASTIdentifierReference(String lexeme, int entry, int line) {
        super(lexeme, entry, line);
    }
    
    @Override
    public Object Visit(Visitor visitor) {
        return visitor.visit(this);
    }

}
