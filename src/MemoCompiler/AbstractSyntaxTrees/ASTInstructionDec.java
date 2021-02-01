
package MemoCompiler.AbstractSyntaxTrees;

import MemoCompiler.MemoTypes;


public class ASTInstructionDec extends ASTInstruction {

    private MemoTypes type;
    private ASTIdentifier id;
    public  ASTIdentifier getId() {
        return id;
    }
    public MemoTypes getType() {
        return type;
    }

    public ASTInstructionDec(MemoTypes type, ASTIdentifier id, int line) {
        super(line);
        this.type = type;
        this.id = id;
    }

      
    @Override
    public Object Visit(Visitor visitor) {
        return visitor.visit(this);
    }
}
