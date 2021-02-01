/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MemoCompiler.Runtime;

/**
 *
 * @author frodo
 */
    public class RuntimeCarga extends RuntimeOperator
    {
        public RuntimeCarga()
        {
            super();
        }

        @Override
        public  void execute(Context context)
        {
            Integer address = ((IntValue)context.getCode().get(context.getCurrent() + 1)).getValue();
            context.getStack().push(context.getMemory().getVal(address));
            context.setCurrent(context.getCurrent()+2);
        }
    }
