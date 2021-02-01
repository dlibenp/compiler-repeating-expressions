/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MemoCompiler.Runtime;

import MemoCompiler.InOut;

/**
 *
 * @author frodo
 */
public class RuntimeHalt extends RuntimeOperator {

    public RuntimeHalt() {
        super();
    }

    @Override
    public void execute(Context context) {
        context.setHalt(true);
        InOut.Write("Program has halted normally.", null);
    }
}
