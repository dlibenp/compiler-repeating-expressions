

package MemoCompiler.AbstractSyntaxTrees;


public class ASTIdentifierValue extends ASTIdentifier{

    public ASTIdentifierValue(String lexeme, int entry, int line) {
        super(lexeme, entry, line);
    }
    
    @Override
    public Object Visit(Visitor visitor) {
        return visitor.visit(this);
    }

}
