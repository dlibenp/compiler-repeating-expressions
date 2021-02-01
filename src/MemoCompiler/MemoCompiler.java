package MemoCompiler;

import MemoCompiler.AbstractSyntaxTrees.*;
import MemoCompiler.CodeGenerator.Encoder;
import MemoCompiler.Errors.*;
import MemoCompiler.LexicalAnalyzer.*;
import MemoCompiler.Runtime.RuntimeEntity;
import MemoCompiler.SemanticAnalyxer.Checker;
import MemoCompiler.SintaxAnalyzer.*;
import MemoCompiler.Stream.*;
import MemoCompiler.SymbolsTable.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;

public class MemoCompiler {

    private Lexer scanner;
    private ErrorReporter errorReporter;
    private SymbolsTable symbolsTable;
    private Parser parser;
    private AST tree;
    private List<RuntimeEntity> code;

//    private AST tree;
    public MemoCompiler(SourceStream source) throws IOException {
        symbolsTable = new SymbolsTable();
        errorReporter = new ErrorReporter();
        scanner = new Lexer(source, symbolsTable, errorReporter);
        parser = new Parser(scanner, errorReporter);

        //       tree = null;
    }

    public AST getTree() {
        return tree;
    }

    public List<RuntimeEntity> getCode() {
        return code;
    }

    public LinkedList<Token> scanAll() throws IOException {
        int contid = 0, contres =0; 
        LinkedList<Token> tokenList = new LinkedList<Token>();
        Token curr_token = null;
        tokenList.add(parser.getLookahead());
        
        do {
            curr_token = scanner.nextToken();
            tokenList.add(curr_token);
        } while (curr_token.getKind() != TokenKind.EOT);
        this.errorReporter = scanner.getErrorList();
        for (int i = 0; i < tokenList.size(); i++) {
            if (tokenList.get(i).getKind() == TokenKind.Id) {
                contid++;
            }
            if (scanner.getKeywordsTable().get(tokenList.get(i).getLexeme())!=null) {
                contres++;
            }
        }
        JOptionPane.showMessageDialog(null,"La cantidad de id es "+contid+"La cantidad de PR es "+contres);
        return tokenList;
        
    }

    public void Parse() throws IOException {
        tree = parser.Parse();
    }

    public void Compile() throws IOException, Exception {
        tree = parser.Parse();
      
        Visitor check = new Checker(symbolsTable, errorReporter);
        tree.Visit(check);
      
        if (errorReporter.isEmpty()) {
            Visitor encoder = new Encoder(symbolsTable);
            tree.Visit(encoder);
            code = ((Encoder) encoder).getCode();
        }
    }

    public void run() throws Exception {
        if (errorReporter.isEmpty() && code != null && code.size() > 0) {
            Interpreter interprete = new Interpreter(code);
            interprete.Execute();
        }
    }


    public ErrorReporter ErrorReporter() {
        return errorReporter;
    }

   
}
