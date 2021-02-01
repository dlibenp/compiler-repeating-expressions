/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package memo;

import MemoCompiler.AbstractSyntaxTrees.*;
import javax.swing.tree.*;

/**
 *
 * @author maikel
 */
public class BuildTree implements Visitor {
    //private DefaultTreeModel nodoRaiz;
    //DefaultMutableTreeNode

//    public BuildTree()
//    {
//        nodoRaiz = new DefaultTreeModel(null);
//    }
//
//    public DefaultTreeModel getNodoRaiz()
//    {
//        return nodoRaiz;
//    }
    public Object visit(ASTProgram o) {

        DefaultMutableTreeNode nodoProgram = new DefaultMutableTreeNode(o);

        for (int i = 0; i < o.Instructions().size(); i++) {
            DefaultMutableTreeNode nodoInst = (DefaultMutableTreeNode) o.Instructions().get(i).Visit(this);
            nodoProgram.insert(nodoInst, i);
        }
        //DefaultTreeModel nodoRaiz = new DefaultTreeModel(nodoProgram);
        //nodoRaiz.setRoot(nodoProgram);
        return nodoProgram;

    }

    public Object visit(ASTInstructionDec o) {
        DefaultMutableTreeNode nodoInstructionDec = new DefaultMutableTreeNode(o);
        DefaultMutableTreeNode nodoType = new DefaultMutableTreeNode(o.getType());
        nodoInstructionDec.insert(nodoType, 0);
        DefaultMutableTreeNode nodoIdentifier = (DefaultMutableTreeNode) o.getId().Visit(this);
        nodoInstructionDec.insert(nodoIdentifier, 1);

        return nodoInstructionDec;
    }

    public Object visit(ASTInstructionAssig o) {
        DefaultMutableTreeNode nodoInstructionAssig = new DefaultMutableTreeNode(o);
        DefaultMutableTreeNode nodoIdentifier = (DefaultMutableTreeNode) o.getId().Visit(this);
        nodoInstructionAssig.insert(nodoIdentifier, 0);
        DefaultMutableTreeNode nodoExpresion = (DefaultMutableTreeNode) o.getExpresion().Visit(this);
        nodoInstructionAssig.insert(nodoExpresion, 1);

        return nodoInstructionAssig;

    }

    public Object visit(ASTInstructionWrite o) {
        DefaultMutableTreeNode nodoInstructionWrite = new DefaultMutableTreeNode(o);
        DefaultMutableTreeNode nodoExpresion = (DefaultMutableTreeNode) o.getExpresion().Visit(this);
        nodoInstructionWrite.insert(nodoExpresion, 0);

        return nodoInstructionWrite;

    }

    public Object visit(ASTExpresionBinary o) {
        DefaultMutableTreeNode nodoExpresionBinary = new DefaultMutableTreeNode(o);

        DefaultMutableTreeNode nodoIzq = (DefaultMutableTreeNode) o.getIzq().Visit(this);
        nodoExpresionBinary.insert(nodoIzq, 0);

        DefaultMutableTreeNode nodoDer = (DefaultMutableTreeNode) o.getDer().Visit(this);
        nodoExpresionBinary.insert(nodoDer, 1);

        return nodoExpresionBinary;
    }

    public Object visit(ASTIdentifierDeclaration o) {
        DefaultMutableTreeNode nodoIdentifierDeclaration = new DefaultMutableTreeNode(o);
        return nodoIdentifierDeclaration;
    }

    public Object visit(ASTIdentifierReference o) {
        DefaultMutableTreeNode nodoIdentifierReference = new DefaultMutableTreeNode(o);
        return nodoIdentifierReference;

    }

    public Object visit(ASTIdentifierValue o) {
        DefaultMutableTreeNode nodoIdentifierValue = new DefaultMutableTreeNode(o);
        return nodoIdentifierValue;

    }

    public Object visit(ASTFloatValue o) {
        DefaultMutableTreeNode nodoFloatValue = new DefaultMutableTreeNode(o);
        return nodoFloatValue;

    }

    public Object visit(ASTIntValue o) {
        DefaultMutableTreeNode nodoIntValue = new DefaultMutableTreeNode(o);
        return nodoIntValue;

    }

    public Object visit(ASTBoolValue o) {
        DefaultMutableTreeNode nodoBoolValue = new DefaultMutableTreeNode(o);
        return nodoBoolValue;
    }

    public Object visit(ASTInstructionIf o) {
        DefaultMutableTreeNode nodoInstructionIf = new DefaultMutableTreeNode(o);
        DefaultMutableTreeNode nodoExpresion = (DefaultMutableTreeNode) o.getCondition().Visit(this);
        nodoInstructionIf.insert(nodoExpresion, 0);
        DefaultMutableTreeNode nodoInstruccion = (DefaultMutableTreeNode) o.getIfInstruction().Visit(this);
        nodoInstructionIf.insert(nodoInstruccion, 0);
        if (o.getElseInstruction() != null) {
            DefaultMutableTreeNode nodoElse = (DefaultMutableTreeNode) o.getElseInstruction().Visit(this);
            nodoInstructionIf.insert(nodoElse, 0);
        }

        return nodoInstructionIf;
    }

    public Object visit(ASTLogicExpressionAnd o) {
        DefaultMutableTreeNode nodoExpresionBinary = new DefaultMutableTreeNode(o);

        DefaultMutableTreeNode nodoIzq = (DefaultMutableTreeNode) o.getIzq().Visit(this);
        nodoExpresionBinary.insert(nodoIzq, 0);

        DefaultMutableTreeNode nodoDer = (DefaultMutableTreeNode) o.getDer().Visit(this);
        nodoExpresionBinary.insert(nodoDer, 1);

        return nodoExpresionBinary;
    }

    public Object visit(ASTLogicExpressionOr o) {
        DefaultMutableTreeNode nodoExpresionBinary = new DefaultMutableTreeNode(o);

        DefaultMutableTreeNode nodoIzq = (DefaultMutableTreeNode) o.getIzq().Visit(this);
        nodoExpresionBinary.insert(nodoIzq, 0);

        DefaultMutableTreeNode nodoDer = (DefaultMutableTreeNode) o.getDer().Visit(this);
        nodoExpresionBinary.insert(nodoDer, 1);

        return nodoExpresionBinary;
    }

    public Object visit(ASTLogicExpressionNot o) {
        DefaultMutableTreeNode nodoExpresionBinary = new DefaultMutableTreeNode(o);

        DefaultMutableTreeNode nodoIzq = (DefaultMutableTreeNode) o.getOperand().Visit(this);
        nodoExpresionBinary.insert(nodoIzq, 0);

        return nodoExpresionBinary;
    }

    public Object visit(ASTLogicExpressionEquals o) {
        DefaultMutableTreeNode nodoExpresionBinary = new DefaultMutableTreeNode(o);

        DefaultMutableTreeNode nodoIzq =
                (DefaultMutableTreeNode) o.getIzq().Visit(this);
        nodoExpresionBinary.insert(nodoIzq, 0);

        DefaultMutableTreeNode nodoDer =
                (DefaultMutableTreeNode) o.getDer().Visit(this);
        nodoExpresionBinary.insert(nodoDer, 1);

        return nodoExpresionBinary;
    }

    public Object visit(ASTLogicExpressionDiffers o) {
        DefaultMutableTreeNode nodoExpresionBinary = new DefaultMutableTreeNode(o);

        DefaultMutableTreeNode nodoIzq =
                (DefaultMutableTreeNode) o.getIzq().Visit(this);
        nodoExpresionBinary.insert(nodoIzq, 0);

        DefaultMutableTreeNode nodoDer =
                (DefaultMutableTreeNode) o.getDer().Visit(this);
        nodoExpresionBinary.insert(nodoDer, 1);

        return nodoExpresionBinary;
    }

    public Object visit(ASTLogicExpressionGreaterThan o) {
        DefaultMutableTreeNode nodoExpresionBinary = new DefaultMutableTreeNode(o);

        DefaultMutableTreeNode nodoIzq =
                (DefaultMutableTreeNode) o.getIzq().Visit(this);
        nodoExpresionBinary.insert(nodoIzq, 0);

        DefaultMutableTreeNode nodoDer =
                (DefaultMutableTreeNode) o.getDer().Visit(this);
        nodoExpresionBinary.insert(nodoDer, 1);

        return nodoExpresionBinary;
    }

    public Object visit(ASTLogicExpressionGreaterThanOrEquals o) {
        DefaultMutableTreeNode nodoExpresionBinary = new DefaultMutableTreeNode(o);

        DefaultMutableTreeNode nodoIzq =
                (DefaultMutableTreeNode) o.getIzq().Visit(this);
        nodoExpresionBinary.insert(nodoIzq, 0);

        DefaultMutableTreeNode nodoDer =
                (DefaultMutableTreeNode) o.getDer().Visit(this);
        nodoExpresionBinary.insert(nodoDer, 1);

        return nodoExpresionBinary;
    }

    public Object visit(ASTLogicExpressionLessThan o) {
        DefaultMutableTreeNode nodoExpresionBinary = new DefaultMutableTreeNode(o);

        DefaultMutableTreeNode nodoIzq =
                (DefaultMutableTreeNode) o.getIzq().Visit(this);
        nodoExpresionBinary.insert(nodoIzq, 0);

        DefaultMutableTreeNode nodoDer =
                (DefaultMutableTreeNode) o.getDer().Visit(this);
        nodoExpresionBinary.insert(nodoDer, 1);

        return nodoExpresionBinary;
    }

    public Object visit(ASTLogicExpressionLessThanOrEquals o) {
        DefaultMutableTreeNode nodoExpresionBinary = new DefaultMutableTreeNode(o);

        DefaultMutableTreeNode nodoIzq =
                (DefaultMutableTreeNode) o.getIzq().Visit(this);
        nodoExpresionBinary.insert(nodoIzq, 0);

        DefaultMutableTreeNode nodoDer =
                (DefaultMutableTreeNode) o.getDer().Visit(this);
        nodoExpresionBinary.insert(nodoDer, 1);

        return nodoExpresionBinary;
    }

    public Object visit(ASTExpresionSuma o) {
        return visit((ASTExpresionBinary) o);
    }

    public Object visit(ASTExpresionMult o) {
        return visit((ASTExpresionBinary) o);
    }

    public Object visit(ASTWrongInstruction o) {
         DefaultMutableTreeNode nodoWrongInst = new DefaultMutableTreeNode(o);
         return nodoWrongInst;
    }

    public Object visit(ASTWrongExpression o) {
        DefaultMutableTreeNode nodoWrongExp = new DefaultMutableTreeNode(o);
        return nodoWrongExp;
    }

    @Override
    public Object visit(ASTInstructionWhile _while) {
        DefaultMutableTreeNode nodoInstructionCondicion = new DefaultMutableTreeNode(_while);
        DefaultMutableTreeNode nodoExpresion = (DefaultMutableTreeNode) _while.getExpression().Visit(this);
        nodoInstructionCondicion.insert(nodoExpresion, 0);
        DefaultMutableTreeNode nodoInstruccion = (DefaultMutableTreeNode) _while.getDo().Visit(this);
        nodoInstructionCondicion.insert(nodoInstruccion, 0);
        
        return nodoInstructionCondicion;
    }

    @Override
    public Object visit(ASTInstructionDoWhile doWhile) {
        DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(doWhile);
        DefaultMutableTreeNode nodoInstruccionCondicion = (DefaultMutableTreeNode) doWhile.getDo().Visit(this);
        nodo.insert(nodoInstruccionCondicion, 0);
        DefaultMutableTreeNode nodoExpresion = (DefaultMutableTreeNode) doWhile.getExpression().Visit(this);
        nodo.insert(nodoExpresion, 0);
        
        return nodo;
    }

    @Override
    public Object visit(ASTInstructionFor _for) {
        DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(_for);
        DefaultMutableTreeNode nodoInstruccionAssig = (DefaultMutableTreeNode) _for.getPrimera().Visit(this);
        nodo.insert(nodoInstruccionAssig, 0);
        DefaultMutableTreeNode nodoInstruccionExp = (DefaultMutableTreeNode) _for.getExpression().Visit(this);
        nodo.insert(nodoInstruccionExp, 0);
        DefaultMutableTreeNode nodoInstruccionExp2 = (DefaultMutableTreeNode) _for.getUltima().Visit(this);
        nodo.insert(nodoInstruccionExp2, 0);
        DefaultMutableTreeNode nodoInstruccion = (DefaultMutableTreeNode) _for.getInstruccion().Visit(this);
        nodo.insert(nodoInstruccion, 0);
        
        return nodo;
    }
}
