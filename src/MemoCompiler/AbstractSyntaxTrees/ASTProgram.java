/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MemoCompiler.AbstractSyntaxTrees;
import java.util.LinkedList;
import java.util.List;

public class ASTProgram extends AST{
private List<ASTInstruction> instructions;

    public ASTProgram(List<ASTInstruction> instructions)        
    {
        super(0);
        this.instructions = new LinkedList<ASTInstruction>(instructions);
    }

    public List<ASTInstruction> Instructions()
    {
        return instructions;     
    }

    @Override
    public Object Visit(Visitor visitor) {
        return visitor.visit(this);
    }
}
