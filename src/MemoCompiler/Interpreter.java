/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MemoCompiler;

import MemoCompiler.Runtime.Context;
import MemoCompiler.Runtime.RuntimeEntity;
import java.util.List;

/**
 *
 * @author frodo
 */
    public class Interpreter
    {
        private Context context;

        public Interpreter(List<RuntimeEntity> code)
        {
            context = new Context(code);
        }

        public void Execute() throws Exception
        {
            InOut.Write("********** Memo Interpreter (JAVA Version 3.0) **********", null);
            InOut.Write("Program is running...", null);
            while (!context.Halt())
                context.getCode().get(context.getCurrent()).execute(context);
        }
    }

