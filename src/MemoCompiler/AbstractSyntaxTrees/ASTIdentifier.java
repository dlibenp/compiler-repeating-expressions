
package MemoCompiler.AbstractSyntaxTrees;


public abstract class ASTIdentifier extends ASTSymbol{

    public ASTIdentifier(String lexeme, int entry, int line) {
        super(entry, lexeme, line);
    }
    
}
