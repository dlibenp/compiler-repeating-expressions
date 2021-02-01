/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MemoCompiler.Runtime;

import java.util.List;
import java.util.Stack;

/**
 *
 * @author frodo
 */
public class Context {
        private Stack<Object> stack;
        private Memory memory;
        private int current;
        private boolean halt;
        private List<RuntimeEntity> code;

        public Context(List<RuntimeEntity> code)
        {
            this.code = code;
            this.stack = new Stack<Object>();
            this.memory = new Memory();
            current = 0;
            halt = false;
        }

        public Stack<Object> getStack()
        {
            return stack;
        }

        public Memory getMemory()
        {
                return memory;
        }

        public int getCurrent()
        {
                return current;
        }
        public void setCurrent(int value)
        {
                current = value;
        }

        public boolean Halt(){
            return halt;
        }
        
       public void setHalt(boolean value){
            halt = value;
       }
       public List<RuntimeEntity> getCode(){
                return code;
       }
}
