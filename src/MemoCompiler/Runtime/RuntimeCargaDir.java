/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MemoCompiler.Runtime;

/**
 *
 * @author frodo
 */
public class RuntimeCargaDir extends RuntimeOperator {

    public RuntimeCargaDir() {
        super();
    }

    @Override
    public void execute(Context context) {
        Object address = context.getCode().get(context.getCurrent() + 1);
        context.getStack().push(address);
        context.setCurrent(context.getCurrent() + 2);
    }
}
