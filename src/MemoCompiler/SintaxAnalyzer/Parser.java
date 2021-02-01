/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MemoCompiler.SintaxAnalyzer;

import MemoCompiler.AbstractSyntaxTrees.*;
import MemoCompiler.Errors.ErrorReporter;
import MemoCompiler.Errors.SyntacticError;
import MemoCompiler.LexicalAnalyzer.*;
import MemoCompiler.MemoTypes;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Parser {

    Lexer input; //from were do we get the tokens
    Token lookahead; //current token
    ErrorReporter errorReporter;

    public Parser(Lexer input, ErrorReporter errorReporter) throws IOException {
        this.input = input;
        this.errorReporter = errorReporter;
        lookahead = input.nextToken();
    }

    public Token getLookahead() {
        return lookahead;
    }
    
    private void consume() throws IOException {
        lookahead = input.nextToken();
    }

    public void match(TokenKind tk_expected) throws IOException {
        if (tk_expected == lookahead.getKind()) {
            consume();
        } else {
            errorReporter.add(new SyntacticError(lookahead.getLine(), "Se esperaba el token \"" + tk_expected + "\" y se encontró el token \"" + lookahead.getKind() + "\""));
            recuperate(tk_expected);
            consume();
        }
    }

    public void recuperate(TokenKind tk_expected) throws IOException {
        while (lookahead.getKind() != tk_expected && lookahead.getKind() != TokenKind.EOT) {
            consume();
        }
    }
    private void recuperate(List<TokenKind> tipos) throws IOException{
        while (!tipos.contains(lookahead.getKind()) && lookahead.getKind() != TokenKind.EOT ) {
           this.consume();
        }
    }
     public AST Parse() throws IOException{
         AST ast = Program();
         if(lookahead.getKind() != TokenKind.EOT){
           errorReporter.add(new SyntacticError(lookahead.getLine(), "Error Sintáctico: Se esperaba el token fin de fichero y se encontró el token " + lookahead.getKind())); 
           recuperate(TokenKind.EOT);
         }
         return ast;
     }

    // <program> -> void id() { <instruction> <mas_instr> }
    public ASTProgram Program() throws IOException {

        match(TokenKind.Void);
        match(TokenKind.Id);
        match(TokenKind.LeftParen);
        match(TokenKind.RigthParen);
        match(TokenKind.LeftKey);
        
        List<ASTInstruction> instructions = new LinkedList<ASTInstruction>();
        instructions.add(Instruction());
        instructions.addAll(Mas_Instr());
        
        match(TokenKind.RightKey);

        return new ASTProgram(instructions);
    }

    // <instruction> -> <write_instruction> | <declaration_instruction>
    //                 |<assig_instruction> | <if_instruction> | <ParseWhile>
    //                 | <ParseDoWhile> | <ParseFor> | <SemiColon>
    private ASTInstruction Instruction() throws IOException {
        switch(lookahead.getKind()){
            case Write:
                return Write_Instruction();
            case Id:
                return Assig_Instruction();
            case Int:
                return Declaration_Instruction();
            case Float:
                return Declaration_Instruction();
            case Bool:
                return Declaration_Instruction();
            case If:
                // Agregado para las condiciones
                return If_Instruction();
            case While:
                // Agregado para las expresiones repetitivas
                return ParseWhile();
            case Do:
                return ParseDoWhile();
            case For:
                return ParseFor();
            case SemiColon:
                match(TokenKind.SemiColon);
            default:
                errorReporter.add(new SyntacticError(lookahead.getLine(), 
                     "Instrucción no válida, se esperaba write, Int, Float, Bool, If, While, Do, For o Identificador y llegó "+ lookahead.getLexeme()));
                List<TokenKind> list = new ArrayList<TokenKind>();
                list.add(TokenKind.Write);
                list.add(TokenKind.Id);
                list.add(TokenKind.Int);
                list.add(TokenKind.Float);
                list.add(TokenKind.If);
                list.add(TokenKind.SemiColon);
                list.add(TokenKind.While);
                list.add(TokenKind.Do);
                list.add(TokenKind.For);
                recuperate(list);
                return new ASTWrongInstruction(lookahead.getLine());
        }
    }
    // <mas_instr>  ->  <instruction> <mas_instr> | e
    private List<ASTInstruction> Mas_Instr() throws IOException {
         List<ASTInstruction> listInsts = new ArrayList<ASTInstruction>();
         if (lookahead.getKind() == TokenKind.Write || lookahead.getKind() == TokenKind.Read ||
                 lookahead.getKind() == TokenKind.Id || lookahead.getKind() == TokenKind.If ||
                 lookahead.getKind() == TokenKind.While || lookahead.getKind() == TokenKind.Do ||
                 lookahead.getKind() == TokenKind.For || lookahead.getKind() == TokenKind.SemiColon ||
                 lookahead.getKind() == TokenKind.Int || lookahead.getKind() == TokenKind.Float ||
                 lookahead.getKind() == TokenKind.Bool) {
            listInsts.add(Instruction());
            listInsts.addAll(Mas_Instr());
        }
        return listInsts;
    }

    // <assig_instruction> -> tk_id tk_op_asig <expression>;
    private ASTInstructionAssig Assig_Instruction() throws IOException {
        ASTIdentifierReference idref = new ASTIdentifierReference(lookahead.getLexeme(), lookahead.getEntry(), lookahead.getLine());
        match(TokenKind.Id);
        int line =  lookahead.getLine();
        match(TokenKind.Assignment);
        //modificado
        ASTLogicExpression expression = Expression();
        match(TokenKind.SemiColon);
        return new ASTInstructionAssig(idref, expression, line);
    }

    // <declaration> -> <type> tk_id ;
    private ASTInstruction Declaration_Instruction() throws IOException {
        MemoTypes type;
        ASTInstructionDec dcl= null ;
        if (lookahead.getKind() == TokenKind.Int) {
            type = MemoTypes.Int;
            match(TokenKind.Int);
        } else if (lookahead.getKind() == TokenKind.Bool) {
            type = MemoTypes.Bool;
            match(TokenKind.Bool);
        } else {
            type = MemoTypes.Float;
            match(TokenKind.Float);
        }
        int line = lookahead.getLine();
        if (lookahead.getKind() == TokenKind.Id) {
            dcl = new ASTInstructionDec(type, new ASTIdentifierDeclaration(lookahead.getLexeme(), lookahead.getEntry(), line), line);
        }
        match(TokenKind.Id);
        //modificado
        match(TokenKind.SemiColon);
        return (dcl != null) ? dcl : new ASTWrongInstruction(line) ;
    }

    // <write_instruction> ->  tk_write <expression> 
    private ASTInstructionWrite Write_Instruction() throws IOException {
        int line =  lookahead.getLine();
        match(TokenKind.Write);
        return new ASTInstructionWrite(Expression(), line);
    }

    // Método agregado para las condiciones
    // <if_instruction> ->  tk_if tk_( <logic_expression> tk_) tk_then <instruction> <else_part>
    private ASTInstructionIf If_Instruction() throws IOException {
        int ifPosition = lookahead.getLine();
        match(TokenKind.If);
        match(TokenKind.LeftParen);
        ASTLogicExpression condition = Logic_Expression();
        match(TokenKind.RigthParen);
        match(TokenKind.Then);
        return new ASTInstructionIf(condition, Instruction(), Else_Part(), ifPosition);
    }

    // Método agregado para las condiciones
    // <else_part> -> tk_else <instruction> |
    private ASTInstruction Else_Part() throws IOException {
        if (lookahead.getKind() == TokenKind.Else) {
            match(TokenKind.Else);
            return Instruction();
        } else {
            return null;
        }
    }

    // <expression> -> <termino> <mas_termino>
    private ASTLogicExpression Expression() throws IOException {
        ASTLogicExpression term = Termino();
        return Mas_Termino(term);
    }

    // <mas_termino> -> tk_op_sum <termino> <mas_termino> | e
    private ASTLogicExpression Mas_Termino(ASTLogicExpression term) throws IOException {
        if (lookahead.getKind() == TokenKind.Sum) {
            int line = lookahead.getLine();
            match(TokenKind.Sum);
            ASTLogicExpression izq = term;
            ASTLogicExpression der = Termino();
            return Mas_Termino(new ASTExpresionSuma(izq, der, line ));
        }
        return term;
    }
    // <termino> -> <factor> <mas_factor>
    private ASTLogicExpression Termino() throws IOException {
        ASTLogicExpression factor = Factor();
        return Mas_Factor(factor);
    }

    // <mas_factor> -> tk_op_por <factor> <mas_factor> | e
    private ASTLogicExpression Mas_Factor(ASTLogicExpression factor) throws IOException {
        if (lookahead.getKind() == TokenKind.Multiplication) {
            int line = lookahead.getLine();
            match(TokenKind.Multiplication);
            ASTLogicExpression izq = factor;
            ASTLogicExpression der = Factor();
            return Mas_Factor(new ASTExpresionMult(izq, der, line));
        }
        return factor;
    }
    // <factor> ->  !<logic_expression>| <factor2>
     private ASTLogicExpression Factor() throws IOException {
        ASTLogicExpression factor;
        switch(lookahead.getKind()){
            case Not:
                match(TokenKind.Not);
                factor = Logic_Expression();
            break;
            case LeftParen:
            case Id:
            case FloatLiteral:
            case IntLiteral:
            case True:
            case False:
                 factor = Factor2();
            break;
            default:
                factor = new ASTWrongExpression(lookahead.getLine());
                errorReporter.add(new SyntacticError(lookahead.getLine(), "Expresión Incorrecta"));
                recuperate(TokenKind.SemiColon);
               
        }
        return factor;
     }
    // <factor2> ->  (<logic_expression>)| tk_id | tk_val_int | tk_val_float | tk_true | tk_false
    private ASTLogicExpression Factor2() throws IOException {
        ASTLogicExpression factor;
        switch (lookahead.getKind()) {
            case Id:
                factor = new ASTIdentifierValue(lookahead.getLexeme(), lookahead.getEntry(), lookahead.getLine());
                match(TokenKind.Id);
                break;
            case FloatLiteral:
                factor = new ASTFloatValue(lookahead.getEntry(), lookahead.getLexeme(), lookahead.getLine());
                match(TokenKind.FloatLiteral);
                break;
            case IntLiteral:
                factor = new ASTIntValue(lookahead.getEntry(), lookahead.getLexeme(), lookahead.getLine());
                match(TokenKind.IntLiteral);
                break;
            case True:
                factor = new ASTBoolValue(lookahead.getLexeme(), lookahead.getLine());
                match(TokenKind.True);
                break;
            case False:
                factor = new ASTBoolValue(lookahead.getLexeme(), lookahead.getLine());
                match(TokenKind.False);
                break;
            case LeftParen:
                match(TokenKind.LeftParen);
                factor = Logic_Expression();
                match(TokenKind.RigthParen);
                break;
            default:
                factor = new ASTWrongExpression(lookahead.getLine());
                errorReporter.add(new SyntacticError(lookahead.getLine(), "Expresión Incorrecta"));
                recuperate(TokenKind.SemiColon);
                
        }
        return factor;
    }

    // Método agregado para las condiciones
    // <logic_expression> -> <logic_term> <more_logic_terms>
    private ASTLogicExpression Logic_Expression() throws IOException {
        ASTLogicExpression term = Logic_Term();
        return More_Logic_Terms(term);
    }

    // Método agregado para las condiciones
    // <mas_termino> -> tk_op_or <termino> <mas_termino> | e
    private ASTLogicExpression More_Logic_Terms(ASTLogicExpression term) throws IOException {
        if (lookahead.getKind() == TokenKind.Or) {
            int line =  lookahead.getLine();
            match(TokenKind.Or);
            ASTLogicExpression izq = term;
            ASTLogicExpression der = Logic_Term();
            return More_Logic_Terms(new ASTLogicExpressionOr(izq, der, line));
        }
        return term;
    }

    // Método agregado para las condiciones
    // <logic_term> -> <logic_factor> <more_logic_factor>
    private ASTLogicExpression Logic_Term() throws IOException {
        ASTLogicExpression factor = Logic_Factor();
        return More_Logic_Factor(factor);
    }

    // Método agregado para las condiciones
    // <more_logic_factor> -> tk_op_and <logic_factor> <more_logic_factor> | e
    private ASTLogicExpression More_Logic_Factor(ASTLogicExpression factor) throws IOException {
        if (lookahead.getKind() == TokenKind.And) {
            int line =  lookahead.getLine();
            match(TokenKind.And);
            ASTLogicExpression izq = factor;
            ASTLogicExpression der = Logic_Factor();
            return More_Logic_Factor(new ASTLogicExpressionAnd(izq, der, line));
        }
        return factor;
    }
    //<logic_factor> -> <expression_relational> <more_exp_relational> 
    private ASTLogicExpression Logic_Factor() throws IOException {
        ASTLogicExpression expression_relational = expresion_relacional();
        return more_expresion_relacional(expression_relational);
    } 
    
     //<more_exp_relational> -> tk_op_equal <expression_relational> <more_expresion_relacional>| tk_op_differs <expression_relational> <more_expresion_relacional>| e
    private ASTLogicExpression more_expresion_relacional(ASTLogicExpression izq) throws IOException {
      if(lookahead.getKind() == TokenKind.Equals){
          int line = lookahead.getLine();
          match(TokenKind.Equals);
          ASTLogicExpression der = expresion_relacional();
          return more_expresion_relacional(new ASTLogicExpressionEquals((ASTLogicExpression)izq, (ASTLogicExpression)der, line));
      }else if(lookahead.getKind() == TokenKind.Differs){
          int line = lookahead.getLine();
          match(TokenKind.Differs);
          ASTLogicExpression der = expresion_relacional();
          return more_expresion_relacional(new ASTLogicExpressionDiffers((ASTLogicExpression)izq, (ASTLogicExpression)der, line));
      }
      return izq;
    } 
    
   //<expression_relational> -> <termino_relacional><more_term_relacional>
    private ASTLogicExpression expresion_relacional() throws IOException {
       ASTLogicExpression termino_relacional =  termino_relacional();
       return more_termino_relacional(termino_relacional);
    } 
   //<termino_relacional> -> <Expression> 
    private ASTLogicExpression termino_relacional() throws IOException {
       return Expression();
    } 
    //<more_term_relacional> -> tk_op_great <termino_relacional><more_term_relacional> | tk_op_less <termino_relacional><more_term_relacional> |
    //                          tk_op_greatEq <termino_relacional><more_term_relacional> | tk_op_lessEq <termino_relacional><more_term_relacional> 
    //                          | e
    private ASTLogicExpression more_termino_relacional(ASTLogicExpression izq) throws IOException {
       if(lookahead.getKind() == TokenKind.GreaterThan){
           int line = lookahead.getLine();
           match(TokenKind.GreaterThan);
           ASTLogicExpression der =  termino_relacional();
           return more_termino_relacional(new ASTLogicExpressionGreaterThan((ASTLogicExpression)izq, (ASTLogicExpression)der, line));
       }else if(lookahead.getKind() == TokenKind.GreaterThanOrEquals){
           int line = lookahead.getLine();
           match(TokenKind.GreaterThanOrEquals);
           ASTLogicExpression der =  termino_relacional();
           return more_termino_relacional(new ASTLogicExpressionGreaterThanOrEquals((ASTLogicExpression)izq, (ASTLogicExpression)der, line));
       }else if(lookahead.getKind() == TokenKind.LessThan){
           int line = lookahead.getLine();
           match(TokenKind.LessThan);
           ASTLogicExpression der =  termino_relacional();
           return more_termino_relacional(new ASTLogicExpressionLessThan((ASTLogicExpression)izq, (ASTLogicExpression)der, line));
       }else if(lookahead.getKind() == TokenKind.LessThanOrEquals){
           int line = lookahead.getLine();
           match(TokenKind.LessThanOrEquals);
           ASTLogicExpression der =  termino_relacional();
           return more_termino_relacional(new ASTLogicExpressionLessThanOrEquals((ASTLogicExpression)izq, (ASTLogicExpression)der, line));
       }
       return izq;
    } 
   
    
    // Método agregado para las condiciones
    // <logic_factor> -> <expression> <restOfComparison> |
    //                   tk_op_not <logic_factor> | ( <logic_expression> )
//    private ASTLogicExpression Logic_Factor() throws IOException {
//        if (lookahead.getKind() == TokenKind.Not) {
//            int line = lookahead.getLine();
//            return new ASTLogicExpressionNot(Logic_Factor(), line);
//        } else if (lookahead.getKind() == TokenKind.LeftParen) {
//            match(TokenKind.LeftParen);
//            ASTLogicExpression logicExpression = Logic_Expression();
//            match(TokenKind.RigthParen);
//            return logicExpression;
//        } else {
//            ASTExpresion expression = Expression();
//            return Rest_Of_Comparison(expression);
//        }
//    }

    // Método agregado para las condiciones
    // <logic_factor> -> tk_op_equals <expression> |
    //                   tk_op_equals <expression> |
    //                   tk_op_equals <expression> |
    //                   tk_op_equals <expression> |
    //                   tk_op_equals <expression> |
    //                   tk_op_equals <expression>
//    private ASTLogicExpressionComparison Rest_Of_Comparison(ASTExpresion expression) throws IOException {
//        int line = lookahead.getLine();
//        switch (lookahead.getKind()) {
//            case Equals:
//                consume();
//                return new ASTLogicExpressionEquals(expression, Expression(), line);
//            case Differs:
//                consume();
//                return new ASTLogicExpressionDiffers(expression, Expression(), line);
//            case GreaterThan:
//                consume();
//                return new ASTLogicExpressionGreaterThan(expression, Expression(), line);
//            case GreaterThanOrEquals:
//                consume();
//                return new ASTLogicExpressionGreaterThanOrEquals(expression, Expression(), line);
//            case LessThan:
//                consume();
//                return new ASTLogicExpressionLessThan(expression, Expression(), line);
//            case LessThanOrEquals:
//                consume();
//                return new ASTLogicExpressionLessThanOrEquals(expression, Expression(), line);
//            default:
//                errorReporter.add(new SyntacticError(line, "Expresión Lógica Incorrecta"));
//                recuperate(TokenKind.SemiColon);
//                return null;
//        }
//    }
    
    
    // ---------------------
    // Métodos agregados para las expresiones repetitivas
    // ---------------------
    private ASTInstruction ParseWhile()throws IOException{
        //<while> --> while ( <expression> ){ <instructions> }
        int line = lookahead.getLine();
        match(TokenKind.While);
        match(TokenKind.LeftParen);
        ASTLogicExpression condition = Logic_Expression();
        match(TokenKind.RigthParen);
        match(TokenKind.LeftKey);
        ASTInstruction instruction = Instruction();
        match(TokenKind.RightKey);
        return new ASTInstructionWhile(condition, instruction, line);
    }
    
    private ASTInstruction ParseDoWhile() throws IOException{
        //<do_while> --> do { <instructions> } while ( <expression> )
        int line = lookahead.getLine();
        match(TokenKind.Do);
        match(TokenKind.LeftKey);
        ASTInstruction instruction = Instruction();
        match(TokenKind.RightKey);
        match(TokenKind.While);
        match(TokenKind.LeftParen);
        ASTLogicExpression condition = Logic_Expression();
        match(TokenKind.RigthParen);
        match(TokenKind.SemiColon);
        return new ASTInstructionDoWhile(condition, instruction, line);
    }
    
    private ASTInstruction ParseFor() throws IOException{
        //for-->for (<expression>; <instruction>; <instruction>) { <instruction> }
        int line = lookahead.getLine();
        match(TokenKind.For);
        match(TokenKind.LeftParen);
        ASTInstruction primera = Assig_Instruction();
        ASTLogicExpression condition = Logic_Expression();
        match(TokenKind.SemiColon);
        ASTLogicExpression ultima = Expression();
        match(TokenKind.RigthParen);
        match(TokenKind.LeftKey);
        ASTInstruction instruction = Instruction();
        match(TokenKind.RightKey);
        return new ASTInstructionFor(primera, condition, ultima, instruction, line);
    }
    
    private MemoTypes GetType(TokenKind tokenKind){
        switch(tokenKind){
            case Bool:
                return MemoTypes.Bool;
            case Int:
                return MemoTypes.Int;
            case Float:
                return MemoTypes.Float;
            default:
                return MemoTypes.Undefined;
        }
    }
    
    private boolean IsType(TokenKind tokenKind){
        return tokenKind == TokenKind.Bool || tokenKind == TokenKind.Int || tokenKind == TokenKind.Float;
    }
}
