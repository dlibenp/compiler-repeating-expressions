
package MemoCompiler.LexicalAnalyzer;


public class Token {

    private TokenKind kind;
    private String lexeme;
    private int entry;
    private int line;
    
    public Token(TokenKind kind, String lexeme,int line, int entry) 
    {
        this.kind   = kind;
        this.lexeme = lexeme;
        this.entry  = entry;
        this.line   = line;
    }
    
    public Token(TokenKind kind, String lexeme, int line) 
    {
        this.kind   = kind;
        this.lexeme = lexeme;
        this.entry  = -1;
        this.line   = line;
    }
    
    public TokenKind getKind()
    {
        return kind;
    }
    
    public String getLexeme()
    {
        return lexeme;
    }
    
    public int getEntry()
    {
        return entry;
    }
    
    public int getLine()
    {
        return line;
    }
    
    @Override
    public String toString()
    {
        return "<" + lexeme + "," + kind +","+line+">";
    }
}
