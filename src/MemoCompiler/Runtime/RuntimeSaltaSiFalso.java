package MemoCompiler.Runtime;

/**
 *
 * @author gandalf
 */
public class RuntimeSaltaSiFalso extends RuntimeOperator {

    public RuntimeSaltaSiFalso() {
        super();
    }

    @Override
    public void execute(Context context) {
        Integer address = ((IntValue) context.getCode().get(context.getCurrent() + 1)).getValue();
        IntValue result = (IntValue) context.getStack().pop();
        
        if (result.getValue().equals(0)) {
            context.setCurrent(address);
        } else {
            context.setCurrent(context.getCurrent() + 2);
        }
    }
}
