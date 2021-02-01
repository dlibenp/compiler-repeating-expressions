/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MemoCompiler.SemanticAnalyxer;

import MemoCompiler.AbstractSyntaxTrees.*;
import MemoCompiler.Errors.ErrorReporter;
import MemoCompiler.Errors.SemanticError;
import MemoCompiler.MemoTypes;
import MemoCompiler.SymbolsTable.*;
import java.util.List;

public class Checker implements Visitor {

    private SymbolsTable st;
    private ErrorReporter errorReporter;

    public Checker(SymbolsTable st, ErrorReporter errorReporter) {
        this.st = st;
        this.errorReporter = errorReporter;
    }

    public Object visit(ASTProgram o) {
        List<ASTInstruction> list = o.Instructions();
        for (ASTInstruction instruction : list) {
            instruction.Visit(this);
        }
        return null;
    }

    public Object visit(ASTInstructionDec o) {
        o.getId().Visit(this);
        SymbolInfo symbol = st.entry(o.getId().getEntry());
        symbol.setType(o.getType());
        return null;
    }

    public Object visit(ASTInstructionAssig o) {
        MemoTypes type2 = (MemoTypes) o.getExpresion().Visit(this);
        MemoTypes type1 = (MemoTypes) o.getId().Visit(this);
        if (type1 != type2) {
            errorReporter.add(new SemanticError(o.getId().getLine(),
                    "Asignación de tipos incompatibles"));
        }
        return null;
    }

    public Object visit(ASTInstructionWrite o) {
        o.getExpresion().Visit(this);
        return null;
    }

    public Object visit(ASTExpresionBinary o) {
        o.getIzq().Visit(this);
        o.getDer().Visit(this);

        if (o.getIzq().getType() == MemoTypes.Undefined || o.getDer().getType() == MemoTypes.Undefined) {
            o.setType(MemoTypes.Undefined);
            return MemoTypes.Undefined;
        }

        if (o.getIzq().getType() != o.getDer().getType()) {
            o.setType(MemoTypes.Undefined);
            errorReporter.add(new SemanticError(o.getLine(), "Operación entre tipos incompatibles"));
            return MemoTypes.Undefined;
        } else {
            o.setType(o.getIzq().getType());
        }

        return o.getType();
    }

    public Object visit(ASTIdentifierDeclaration o) {
        SymbolInfo symbol = st.entry(o.getEntry());
        if (symbol.isDeclared()) {
            errorReporter.add(new SemanticError(o.getLine(), "La variable " + o.getLexeme() + " ya fue definida"));
        } else {
            symbol.setDeclared(true);
        }
        return null;
    }

    public Object visit(ASTIdentifierReference o) {
        SymbolInfo symbol = st.entry(o.getEntry());
        if (!symbol.isDeclared()) {
            o.setType(MemoTypes.Undefined);
            errorReporter.add(new SemanticError(o.getLine(), "Asignación a la variable " + o.getLexeme() + " no declarada"));
        } else {
            o.setType(symbol.getType());
        }
        symbol.setInit(true);

        return o.getType();
    }

    public Object visit(ASTIdentifierValue o) {
        SymbolInfo symbol = st.entry(o.getEntry());
        o.setType(symbol.getType());
        if (symbol.isDeclared() && symbol.isInit()) {
            return symbol.getType();
        }
        if (!symbol.isDeclared()) {
            errorReporter.add(new SemanticError(o.getLine(), "La variable " + o.getLexeme() + " no ha sido declarada"));
            o.setType(MemoTypes.Undefined);
        }
        if (!symbol.isInit()) {
            errorReporter.add(new SemanticError(o.getLine(), "Uso de la variable " + o.getLexeme() + " sin haber sido inicializada"));
        }
        return o.getType();
    }

    public Object visit(ASTFloatValue o) {
        o.setType(MemoTypes.Float);
        return MemoTypes.Float;
    }

    public Object visit(ASTIntValue o) {
        o.setType(MemoTypes.Int);
        return MemoTypes.Int;
    }

    public Object visit(ASTExpresionSuma o) {
        return visit((ASTExpresionBinary) o);
    }

    public Object visit(ASTExpresionMult o) {
        return visit((ASTExpresionBinary) o);
    }

    // ---------------------
    // Métodos agregados para las condiciones
    // ---------------------
    public Object visit(ASTBoolValue o) {
        o.setType(MemoTypes.Bool);
        return MemoTypes.Bool;
    }

    public Object visit(ASTInstructionIf o) {
        o.getCondition().Visit(this);
        o.getIfInstruction().Visit(this);
        if (o.getElseInstruction() != null) {
            o.getElseInstruction().Visit(this);
        }
        return null;
    }

    public Object visit(ASTLogicExpressionAnd o) {
        o.getIzq().Visit(this);
        o.getDer().Visit(this);

        return MemoTypes.Bool;
    }

    public Object visit(ASTLogicExpressionOr o) {
        o.getIzq().Visit(this);
        o.getDer().Visit(this);

        return MemoTypes.Bool;
    }

    public Object visit(ASTLogicExpressionNot o) {
        o.getOperand().Visit(this);

        return MemoTypes.Bool;
    }

    public Object visit(ASTLogicExpressionComparison o) {
        o.getIzq().Visit(this);
        o.getDer().Visit(this);

        if (o.getIzq().getType() == MemoTypes.Undefined || o.getDer().getType() == MemoTypes.Undefined) {
            o.setType(MemoTypes.Undefined);
            return MemoTypes.Undefined;
        }

        if (o.getIzq().getType() != o.getDer().getType()) {
            o.setType(MemoTypes.Undefined);
            errorReporter.add(new SemanticError(o.getLine(), "Comparación entre tipos incompatibles."));
            return MemoTypes.Undefined;
        } else {
            o.setType(o.getIzq().getType());
        }

        return MemoTypes.Bool;
    }

    public Object visit(ASTLogicExpressionEquals o) {
        return visit((ASTLogicExpressionComparison) o);
    }

    public Object visit(ASTLogicExpressionDiffers o) {
        return visit((ASTLogicExpressionComparison) o);
    }

    public Object visit(ASTLogicExpressionGreaterThan o) {
        return visit((ASTLogicExpressionComparison) o);
    }

    public Object visit(ASTLogicExpressionGreaterThanOrEquals o) {
        return visit((ASTLogicExpressionComparison) o);
    }

    public Object visit(ASTLogicExpressionLessThan o) {
        return visit((ASTLogicExpressionComparison) o);
    }

    public Object visit(ASTLogicExpressionLessThanOrEquals o) {
        return visit((ASTLogicExpressionComparison) o);
    }

    public Object visit(ASTWrongInstruction o) {
        return null;
    }

    public Object visit(ASTWrongExpression o) {
       return MemoTypes.Undefined;
    }
    
    // ---------------------
    // Métodos agregados para las expresiones repetitivas
    // ---------------------

    @Override
    public Object visit(ASTInstructionWhile _while) {
        MemoTypes expressionType = (MemoTypes)_while.getExpression().Visit(this);
        if (expressionType != MemoTypes.Bool) {
            errorReporter.add(new SemanticError(_while.getLine(), 
                    "No se puede convertir'{0}' a 'bool'".format(expressionType.toString().toLowerCase())));
        }
        _while.getDo().Visit(this);
        return null;
    }

    @Override
    public Object visit(ASTInstructionDoWhile doWhile) {
        doWhile.getDo().Visit(this);
        MemoTypes expressionType = (MemoTypes)doWhile.getExpression().Visit(this);
        if (expressionType != MemoTypes.Bool) {
            errorReporter.add(new SemanticError(doWhile.getLine(), 
                    "No se puede convertir'{0}' a 'bool'".format(expressionType.toString().toLowerCase())));
        }
        return null;
    }

    @Override
    public Object visit(ASTInstructionFor _for) {
        _for.getPrimera().Visit(this);
        MemoTypes expressionType = (MemoTypes)_for.getExpression().Visit(this);
        if (expressionType != MemoTypes.Bool) {
            errorReporter.add(new SemanticError(_for.getLine(), 
                    "No se puede convertir'{0}' a 'bool'".format(expressionType.toString().toLowerCase())));
        }
        _for.getUltima().Visit(this);
        _for.getInstruccion().Visit(this);
        return null;
    }
}
