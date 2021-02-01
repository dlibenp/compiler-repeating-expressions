/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MemoCompiler.SymbolsTable;

import MemoCompiler.LexicalAnalyzer.*;
import java.util.*;
/**
 *
 * @author DDC Programación, UCI
 */
public class SymbolsTable {
    
    private LinkedList<SymbolInfo> items;
    
    public SymbolsTable()
    {
        items = new LinkedList<SymbolInfo>();
    }
    
    public int add(String lexeme, TokenKind kind)
    {
        SymbolInfo item = new SymbolInfo(lexeme, kind);
        
        int index = items.indexOf(item);
        if (index == -1)
        {
            items.add(item);
            index = items.size() - 1;
        }
        return index;
    }
    
    public SymbolInfo entry(int index) 
    {
        return items.get(index);
    }
    
    public int Count()
    {
        return items.size();
    }
}
