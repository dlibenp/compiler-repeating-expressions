package MemoCompiler.Runtime;

/**
 * @author gandalf
 */
public class RuntimeAnd extends RuntimeOperator {

    public RuntimeAnd() {
        super();
    }

    @Override
    public void execute(Context context) {
        IntValue der = (IntValue) context.getStack().pop();
        IntValue izq = (IntValue) context.getStack().pop();

        IntValue rval = new IntValue(izq.getValue() != 0 && der.getValue() != 0 ? 1 : 0);
        context.getStack().push(rval);
        context.setCurrent(context.getCurrent() + 1);
    }
}
