

package MemoCompiler.AbstractSyntaxTrees;


public class ASTFloatValue extends ASTSymbol{
    public ASTFloatValue(int entry, String lexeme, int line) {
        super(entry, lexeme, line);
    }
    
    
    @Override
    public Object Visit(Visitor visitor) {
        return visitor.visit(this);
    }
    
}
