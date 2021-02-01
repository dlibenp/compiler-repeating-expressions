/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MemoCompiler.Runtime;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author frodo
 */
public class Memory {
    private int _size;
    private List<Object> _store;

    public Memory(){
        _store = new ArrayList<Object>();
    }

    public Memory(int maxSize){
        _store = new ArrayList<Object>(maxSize);
    }

    public int addVal(Object val){
        _store.add(val);
        _size++;
        return _size-1;
    }


    public void assign(int dir, Object val){
        _store.set(dir, val);
    }

    public Object getVal(int dir){
        return  _store.get(dir);
    }
    
    public int getSize()
    {
        return _size;
    }
}
