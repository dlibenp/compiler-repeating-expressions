package MemoCompiler.AbstractSyntaxTrees;


public interface Visitor
{
    public Object visit(ASTProgram o);
    public Object visit(ASTInstructionDec o);
    public Object visit(ASTInstructionAssig o);    
    public Object visit(ASTInstructionWrite o);
    public Object visit(ASTExpresionSuma o);
    public Object visit(ASTExpresionMult o);
    public Object visit(ASTIdentifierDeclaration o);
    public Object visit(ASTIdentifierReference o);
    public Object visit(ASTIdentifierValue o);
    public Object visit(ASTFloatValue o);
    public Object visit(ASTIntValue o);    

    // Métodos agregados para las condiciones
    public Object visit(ASTBoolValue o);
    public Object visit(ASTInstructionIf o);
    public Object visit(ASTLogicExpressionAnd o);
    public Object visit(ASTLogicExpressionOr o);
    public Object visit(ASTLogicExpressionNot o);
    public Object visit(ASTLogicExpressionEquals o);
    public Object visit(ASTLogicExpressionDiffers o);
    public Object visit(ASTLogicExpressionGreaterThan o);
    public Object visit(ASTLogicExpressionGreaterThanOrEquals o);
    public Object visit(ASTLogicExpressionLessThan o);
    public Object visit(ASTLogicExpressionLessThanOrEquals o);
    public Object visit(ASTWrongInstruction o);
    public Object visit(ASTWrongExpression o);
    
    // Métodos agregados para las expresienes repetitivas
    public Object visit(ASTInstructionWhile o);
    public Object visit(ASTInstructionDoWhile o);
    public Object visit(ASTInstructionFor o);
}
