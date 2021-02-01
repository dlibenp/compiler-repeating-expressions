/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MemoCompiler.Runtime;

/**
 *
 * @author frodo
 */
public class RuntimeValue<T> extends RuntimeEntity
{
    private T value;


    public RuntimeValue(T value)
    {
        this.value = value;
    }

    public T getValue()
    {
        return this.value;
    }

    @Override
    public  String toString()
    {
        return value.toString();
    }
}

