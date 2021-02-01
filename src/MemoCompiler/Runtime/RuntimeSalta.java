package MemoCompiler.Runtime;

/**
 *
 * @author gandalf
 */
public class RuntimeSalta extends RuntimeOperator {

    public RuntimeSalta() {
        super();
    }

    @Override
    public void execute(Context context) {
        Integer address = ((IntValue) context.getCode().get(context.getCurrent() + 1)).getValue();
        context.setCurrent(address);
    }
}
