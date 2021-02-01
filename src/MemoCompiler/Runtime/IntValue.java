/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MemoCompiler.Runtime;

/**
 *
 * @author frodo
 */
public class IntValue extends RuntimeValue<Integer>
{
    public IntValue()
    {
        super(new Integer(0));
    }

    public IntValue(Integer value)
    {
        super(value);
    }
}
