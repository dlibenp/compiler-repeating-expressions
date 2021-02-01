package MemoCompiler.Runtime;

/**
 * @author gandalf
 */
public class RuntimeNot extends RuntimeOperator {

    public RuntimeNot() {
        super();
    }

    @Override
    public void execute(Context context) {
        IntValue oper = (IntValue) context.getStack().pop();

        IntValue rval = new IntValue(oper.getValue() == 0 ? 1 : 0);
        context.getStack().push(rval);
        context.setCurrent(context.getCurrent() + 1);
    }
}
