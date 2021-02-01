/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MemoCompiler.Runtime;

/**
 *
 * @author frodo
 */
public class FloatValue extends RuntimeValue<Float>
{
    public FloatValue()
    {
        super(new Float(0.0));
    }
    public FloatValue(Float value)
    {
        super(value);
    }
}
