/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MemoCompiler.Errors;

/**
 *
 * @author tomaso
 */
public class CompilerError {

    private int line;
    private String text;

    public CompilerError(int line, String text) {
        this.line = line;
        this.text = text;
    }

    public String Text() {
        return text;
    }

    public int Position() {
        return line;
    }

    @Override
    public String toString() {
        return Text() + " en la línea: " + line + "\n";
    }
}
