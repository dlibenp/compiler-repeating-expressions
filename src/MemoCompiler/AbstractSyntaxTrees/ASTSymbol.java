/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MemoCompiler.AbstractSyntaxTrees;

/**
 *
 * @author Administrator
 */
public abstract class ASTSymbol extends ASTExpresion {

    private int entry;
    private String lexeme;

    public String getLexeme() {
        return lexeme;
    }

    public int getEntry() {
        return entry;
    }

    public ASTSymbol(int entry, String lexeme, int line) {
        super(line);
        this.entry = entry;
        this.lexeme = lexeme;
    }

    @Override
    public String toString() {
        return lexeme;
    }
}
