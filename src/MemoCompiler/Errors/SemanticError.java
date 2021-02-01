/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MemoCompiler.Errors;

/**
 *
 * @author Administrator
 */
public class SemanticError extends CompilerError {

    public SemanticError(int line, String text) {
        super(line, text);
    }

    @Override
    public String toString() {
        return "Error semántico: " + super.toString();
    }
}
