package model.statement;

import model.expression.Expression;
import model.myException.MyException;
import model.state.MyDictionary;
import model.state.ProgramState;

import model.state.SymbolTable;
import model.type.RefType;
import model.type.Type;
import model.value.RefValue;
import model.value.Value;

public class NewStatement implements Statement {
    private final String varName;
    private final Expression expression;

    public NewStatement(String varName, Expression expression) {
        this.varName = varName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        var symTable = state.symbolTable();
        var heap = state.heap();

        if (!symTable.isDefined(varName)) {
            throw new RuntimeException("Variable " + varName + " is not defined.");
        }

        Value varValue = symTable.getValue(varName);
        if (!(varValue.getType() instanceof RefType refType)) {
            throw new RuntimeException("Variable " + varName + " is not of RefType.");
        }

        Value exprValue = expression.evaluate(symTable, heap);
        if (!exprValue.getType().equals(refType.getInner())) {
            throw new RuntimeException("Type mismatch: variable " + varName + " and expression do not match.");
        }

        int newAddress = heap.allocate(exprValue);
        RefValue newRefValue = new RefValue(newAddress, refType.getInner());
        symTable.update(varName, newRefValue);

        return state;
    }

    @Override
    public String toString() {
        return "new(" + varName + ", " + expression + ")";
    }

    @Override
    public MyDictionary<String, Type> typecheck(
            MyDictionary<String, Type> typeEnv) throws MyException {

        Type varType = typeEnv.get(varName);
        Type exprType = expression.typecheck(typeEnv);

        if (!(varType instanceof RefType refType))
            throw new MyException("new applied to non-ref variable");

        if (!refType.getInner().equals(exprType))
            throw new MyException("new: inner type mismatch");

        return typeEnv;
    }



}
