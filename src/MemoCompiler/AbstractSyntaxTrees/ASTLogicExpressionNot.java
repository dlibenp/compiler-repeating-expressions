package MemoCompiler.AbstractSyntaxTrees;

import MemoCompiler.MemoTypes;

/**
 * Clase agregada para las condiciones.
 * 
 * @author Gandalf
 */
public class ASTLogicExpressionNot extends ASTLogicExpression {
    private MemoTypes type;
    private ASTLogicExpression operand;

    public MemoTypes getType() {
        return type;
    }

    public void setType(MemoTypes type) {
        this.type = type;
    }

    public ASTLogicExpression getOperand() {
        return operand;
    }

    public ASTLogicExpressionNot(ASTLogicExpression operand, int line) {
        super(line);
        this.operand = operand;
    }

    @Override
    public Object Visit(Visitor visitor) {
        return visitor.visit(this);
    }
}
