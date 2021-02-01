/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MemoCompiler.Runtime;

/**
 *
 * @author frodo
 */
public abstract class RuntimeEntity {

    public RuntimeEntity() {
    }

    public void execute(Context context) throws Exception {
        throw new Exception("The method or operation is not implemented.");
    }

    @Override
    public String toString() {
        String[] names = this.getClass().getName().split("\\.");
        return names[names.length - 1];
    }
}
