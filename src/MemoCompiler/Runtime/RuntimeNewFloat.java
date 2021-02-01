/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MemoCompiler.Runtime;

/**
 *
 * @author frodo
 */
public class RuntimeNewFloat extends RuntimeOperator
{
    public RuntimeNewFloat()
    {
        super();
    }

    @Override
    public  void execute(Context context)
    {
        context.getMemory().addVal(new FloatValue());
        context.setCurrent( context.getCurrent() + 1);
    }
}
