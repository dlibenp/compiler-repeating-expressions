package MemoCompiler.CodeGenerator;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author frodo
 */
import MemoCompiler.AbstractSyntaxTrees.*;
import MemoCompiler.MemoTypes;
import MemoCompiler.Runtime.*;
import MemoCompiler.SymbolsTable.*;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Encoder implements Visitor {

    List<RuntimeEntity> code;
    SymbolsTable st;
    int currentMemDir;

    public List<RuntimeEntity> getCode() {
        return code;
    }

    public Encoder(SymbolsTable st) {
        code = new LinkedList<RuntimeEntity>();
        this.st = st;
        currentMemDir = 0;
    }

    public Object visit(ASTProgram o) {
        ListIterator<ASTInstruction> Iter = o.Instructions().listIterator();
        while (Iter.hasNext()) {
            Iter.next().Visit(this);
        }
        code.add(new RuntimeHalt());
        return null;
    }

    public Object visit(ASTInstructionDec o) {
        o.getId().Visit(this);
        return null;
    }

    public Object visit(ASTInstructionAssig o) {
        o.getId().Visit(this);
        o.getExpresion().Visit(this);
        code.add(new RuntimeStore());
        return null;
    }

    public Object visit(ASTInstructionWrite o) {
        o.getExpresion().Visit(this);
        code.add(new RuntimeWrite());
        return null;
    }

    public Object visit(ASTIdentifierDeclaration o) {
        SymbolInfo info = st.entry(o.getEntry());
        if (info.getType() == MemoTypes.Float) {
            code.add(new RuntimeNewFloat());
            info.setAddress(currentMemDir++);
        } else {
            code.add(new RuntimeNewInt());
            info.setAddress(currentMemDir++);
        }
        return null;
    }

    public Object visit(ASTIdentifierReference o) {
        SymbolInfo info = st.entry(o.getEntry());
        IntValue address = new IntValue(info.getAddress());
        code.add(new RuntimeCargaDir());
        code.add(address);
        return null;
    }

    public Object visit(ASTIdentifierValue o) {
        SymbolInfo info = st.entry(o.getEntry());
        IntValue address = new IntValue(info.getAddress());
        code.add(new RuntimeCarga());
        code.add(address);
        return null;
    }

    public Object visit(ASTFloatValue o) {
        code.add(new RuntimeCargaDir());
        code.add(new FloatValue(Float.parseFloat(o.getLexeme())));
        return null;
    }

    public Object visit(ASTIntValue o) {
        code.add(new RuntimeCargaDir());
        code.add(new IntValue(Integer.parseInt(o.getLexeme())));
        return null;
    }

    public Object visit(ASTExpresionSuma o) {
        o.getIzq().Visit(this);
        o.getDer().Visit(this);
        if (o.getType() == MemoTypes.Int) {
            code.add(new RuntimeSumaInt());
        } else if (o.getType() == MemoTypes.Float) {
            code.add(new RuntimeSumaFloat());
        }
        return null;
    }

    public Object visit(ASTExpresionMult o) {
        o.getIzq().Visit(this);
        o.getDer().Visit(this);
        if (o.getType() == MemoTypes.Int) {
            code.add(new RuntimeMultiplicationInt());
        } else if (o.getType() == MemoTypes.Float) {
            code.add(new RuntimeMultiplicationFloat());
        }
        return null;
    }

    // Métodos agregados para las condiciones
    public Object visit(ASTBoolValue o) {
        code.add(new RuntimeCargaDir());
        code.add(new IntValue("true".equals(o.getLexeme()) ? 1 : 0));
        return null;
    }

    public Object visit(ASTInstructionIf o) {
        o.getCondition().Visit(this);
        code.add(new RuntimeSaltaSiFalso());
        code.add(new IntValue(0));
        int posicionSaltoIf = code.size() - 1;
        o.getIfInstruction().Visit(this);
        if(o.getElseInstruction() != null) {
            code.add(new RuntimeSalta());
            code.add(new IntValue(0));
            int posicionSaltoElse = code.size() - 1;
            code.set(posicionSaltoIf, new IntValue(code.size()));
            o.getElseInstruction().Visit(this);
            code.set(posicionSaltoElse, new IntValue(code.size()));
        } else {
            code.set(posicionSaltoIf, new IntValue(code.size()));
        }
            
        return null;
    }

    public Object visit(ASTLogicExpressionAnd o) {
        o.getIzq().Visit(this);
        o.getDer().Visit(this);
        code.add(new RuntimeAnd());
        return null;
    }

    public Object visit(ASTLogicExpressionOr o) {
        o.getIzq().Visit(this);
        o.getDer().Visit(this);
        code.add(new RuntimeOr());
        return null;
    }

    public Object visit(ASTLogicExpressionNot o) {
        o.getOperand().Visit(this);
        code.add(new RuntimeNot());
        return null;
    }

    public Object visit(ASTLogicExpressionEquals o) {
        o.getIzq().Visit(this);
        o.getDer().Visit(this);
        if (o.getType() == MemoTypes.Int || o.getType() == MemoTypes.Bool) {
            code.add(new RuntimeComparisonEqualsInt());
        } else if (o.getType() == MemoTypes.Float) {
            code.add(new RuntimeComparisonEqualsFloat());
        }
        return null;
    }

    public Object visit(ASTLogicExpressionDiffers o) {
        o.getIzq().Visit(this);
        o.getDer().Visit(this);
        if (o.getType() == MemoTypes.Int || o.getType() == MemoTypes.Bool) {
            code.add(new RuntimeComparisonDiffersInt());
        } else if (o.getType() == MemoTypes.Float) {
            code.add(new RuntimeComparisonDiffersFloat());
        }
        return null;
    }

    public Object visit(ASTLogicExpressionGreaterThan o) {
        o.getIzq().Visit(this);
        o.getDer().Visit(this);
        if (o.getType() == MemoTypes.Int || o.getType() == MemoTypes.Bool) {
            code.add(new RuntimeComparisonGreaterThanInt());
        } else if (o.getType() == MemoTypes.Float) {
            code.add(new RuntimeComparisonGreaterThanFloat());
        }
        return null;
    }

    public Object visit(ASTLogicExpressionGreaterThanOrEquals o) {
        o.getIzq().Visit(this);
        o.getDer().Visit(this);
        if (o.getType() == MemoTypes.Int || o.getType() == MemoTypes.Bool) {
            code.add(new RuntimeComparisonGreaterThanOrEqualsInt());
        } else if (o.getType() == MemoTypes.Float) {
            code.add(new RuntimeComparisonGreaterThanOrEqualsFloat());
        }
        return null;
    }

    public Object visit(ASTLogicExpressionLessThan o) {
        o.getIzq().Visit(this);
        o.getDer().Visit(this);
        if (o.getType() == MemoTypes.Int || o.getType() == MemoTypes.Bool) {
            code.add(new RuntimeComparisonLessThanInt());
        } else if (o.getType() == MemoTypes.Float) {
            code.add(new RuntimeComparisonLessThanFloat());
        }
        return null;
    }

    public Object visit(ASTLogicExpressionLessThanOrEquals o) {
        o.getIzq().Visit(this);
        o.getDer().Visit(this);
        if (o.getType() == MemoTypes.Int || o.getType() == MemoTypes.Bool) {
            code.add(new RuntimeComparisonLessThanOrEqualsInt());
        } else if (o.getType() == MemoTypes.Float) {
            code.add(new RuntimeComparisonLessThanOrEqualsFloat());
        }
        return null;
    }

    public Object visit(ASTWrongInstruction o) {
        return null;
    }

    public Object visit(ASTWrongExpression o) {
        return null;
    }
    
    // ---------------------
    // Métodos agregados para las expresiones repetitivas
    // ---------------------

    public Object visit(ASTInstructionWhile _while) {
        int addreesJump = code.size();
        _while.getExpression().Visit(this);
        code.add(new RuntimeSaltaSiFalso());
        code.add(new IntValue(-1));
        int addressPos = code.size()-1;
        _while.getDo().Visit(this);
        code.add(new RuntimeSalta());
        code.add(new IntValue(addreesJump));
        code.set(addressPos, new IntValue(code.size()));
        return null;
    }

    @Override
    public Object visit(ASTInstructionDoWhile doWhile) {
        int addreesJump = code.size();
        doWhile.getDo().Visit(this);
        doWhile.getExpression().Visit(this);
        code.add(new RuntimeSaltaSiFalso());
        code.add(new IntValue(-1));
        int addressPos = code.size()-1;
        code.add(new RuntimeSalta());
        code.add(new IntValue(addreesJump));
        code.set(addressPos, new IntValue(code.size()));
        return null;
    }

    @Override
    public Object visit(ASTInstructionFor _for) {
        _for.getPrimera().Visit(this);
        int addreesJump = code.size();
        _for.getExpression().Visit(this);
        code.add(new RuntimeSaltaSiFalso());
        code.add(new IntValue(-1));
        int addressPos = code.size()-1;
        _for.getUltima().Visit(this);
        code.add(new RuntimeSalta());
        code.add(new IntValue(addreesJump));
        _for.getInstruccion().Visit(this);
        code.set(addressPos, new IntValue(code.size()));
        return null;
    }
}