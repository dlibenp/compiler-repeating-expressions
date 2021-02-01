
package MemoCompiler.AbstractSyntaxTrees;

import MemoCompiler.MemoTypes;

public class ASTMemoType {
    private MemoTypes type;

    public ASTMemoType(MemoTypes type) {
        this.type = type;
    }

    
    public MemoTypes getType() {
        return type;
    }

    public void setType(MemoTypes type) {
        this.type = type;
    }
    
}
