/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MemoCompiler.Runtime;

/**
 *
 * @author frodo
 */
public class RuntimeSumaFloat extends RuntimeOperator {

    public RuntimeSumaFloat() {
        super();
    }

    @Override
    public void execute(Context context) {
        FloatValue der = (FloatValue) context.getStack().pop();
        FloatValue izq = (FloatValue) context.getStack().pop();

        FloatValue rval = new FloatValue(izq.getValue() + der.getValue());
        context.getStack().push(rval);
        context.setCurrent(context.getCurrent() + 1);
    }
}
