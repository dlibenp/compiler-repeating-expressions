/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MemoCompiler.Runtime;

/**
 *
 * @author frodo
 */
public class RuntimeStore extends RuntimeOperator {

    public RuntimeStore() {
        super();
    }

    @Override
    public void execute(Context context) {
        Object value = context.getStack().pop();
        IntValue address = (IntValue) context.getStack().pop();
        context.getMemory().assign(address.getValue(), value);
        context.setCurrent(context.getCurrent() + 1);
    }
}
