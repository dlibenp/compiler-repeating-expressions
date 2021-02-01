/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MemoCompiler.Runtime;

/**
 *
 * @author frodo
 */
public class RuntimeNewInt extends RuntimeOperator
{
    public RuntimeNewInt()
    {
        super();
    }

    @Override
    public  void execute(Context context)
    {
        context.getMemory().addVal(new IntValue());
        context.setCurrent( context.getCurrent() + 1);
    }
}
