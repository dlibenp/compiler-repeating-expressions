/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MemoCompiler.Runtime;

/**
 *
 * @author frodo
 */
public class RuntimePush extends RuntimeOperator
{
    public RuntimePush()
    {
        super();
    }

    @Override
    public  void execute(Context context)
    {
        Object value = context.getCode().get(context.getCurrent() + 1);
        context.getStack().push(value);
        context.setCurrent(context.getCurrent() + 2);
    }
}
