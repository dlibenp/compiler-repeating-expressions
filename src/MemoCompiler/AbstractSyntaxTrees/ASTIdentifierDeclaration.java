

package MemoCompiler.AbstractSyntaxTrees;


public class ASTIdentifierDeclaration extends ASTIdentifier{

    public ASTIdentifierDeclaration(String lexeme, int entry, int line) {
        super(lexeme, entry, line);
    }

    
    @Override
    public Object Visit(Visitor visitor) {
        return visitor.visit(this);
    }

}
