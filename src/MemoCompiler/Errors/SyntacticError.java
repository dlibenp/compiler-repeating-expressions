/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MemoCompiler.Errors;

/**
 *
 * @author tomaso
 */
public class SyntacticError extends CompilerError {

    public SyntacticError(int line, String text) {
        super(line, text);
    }

    @Override
    public String toString() {
        return "Error sintáctico: " + super.toString();
    }
}
