
package MemoCompiler.AbstractSyntaxTrees;


public class ASTIntValue extends ASTSymbol{

    public ASTIntValue(int entry, String lexeme, int line) {
        super(entry, lexeme, line);
    }
    
    @Override
    public Object Visit(Visitor visitor) {
        return visitor.visit(this);
    }

}
