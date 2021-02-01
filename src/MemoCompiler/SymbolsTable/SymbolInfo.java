
package MemoCompiler.SymbolsTable;

import MemoCompiler.*;
import MemoCompiler.LexicalAnalyzer.*;

/**
 *
 * @author frodo
 */
public class SymbolInfo {
    
    private String lexeme;
    private TokenKind kind;
    private boolean declared;
    private MemoTypes type;
    private boolean  init;

    
    private int address;
    
    public SymbolInfo(String lexeme, TokenKind kind) 
    {
        this.lexeme = lexeme;
        this.kind = kind;
        this.declared = false;
        this.init = false;
        this.type = MemoTypes.Undefined;
        this.address = -1;
    }
    
    public String getLexeme() 
    {
        return lexeme;
    }
    
    public TokenKind getKind()
    {
        return kind;
    }
    public void setKind(TokenKind kind)
    {
        this.kind = kind;
    }
    
    public boolean getDeclared()
    {
        return declared;
    }
    public void setDeclared(boolean declared)
    {
        this.declared = declared;
    }
    
    public int getAddress()
    {
        return address;
    }
    public void setAddress(int address)
    {
        this.address = address;
    }
    
    public void setInit(boolean init) {
        this.init = init;
    }

    public boolean isDeclared() {
        return declared;
    }

    public boolean isInit() {
        return init;
    }
    
    @Override
    public boolean equals(Object obj)
    {
        String objLexeme = ((SymbolInfo)obj).getLexeme();
        return objLexeme.equals(lexeme);
    }
    
    @Override
    public int hashCode()
    {
        return lexeme.hashCode();
    }
    
    public void setType(MemoTypes type)
    {
        this.type = type;
    }
    
    public MemoTypes getType()
    {
        return type;
    }
}
