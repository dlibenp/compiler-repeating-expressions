package MemoCompiler.Runtime;

/**
 *
 * @author frodo
 */
public class RuntimeSumaInt extends RuntimeOperator {

    public RuntimeSumaInt() {
        super();
    }

    @Override
    public void execute(Context context) {
        IntValue der = (IntValue) context.getStack().pop();
        IntValue izq = (IntValue) context.getStack().pop();

        IntValue rval = new IntValue(izq.getValue() + der.getValue());
        context.getStack().push(rval);
        context.setCurrent(context.getCurrent() + 1);
    }
}
