/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MemoCompiler.Errors;

/**
 *
 * @author tomaso
 */
public class LexicalError extends CompilerError {

    public LexicalError(int line, String text) {
        super(line, text);
    }

    @Override
    public String toString() {
        return "Error léxico: " + super.toString();
    }
}
