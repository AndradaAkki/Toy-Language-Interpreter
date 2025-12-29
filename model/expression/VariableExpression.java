package model.expression;

import model.myException.ExpressionException;
import model.myException.MyException;
import model.state.Heap;
import model.state.MyDictionary;
import model.state.SymbolTable;
import model.value.Value;
import model.type.Type;

public record VariableExpression(String variableName) implements Expression {

    @Override
    public Value evaluate(SymbolTable symTable, Heap heap) {
        if (!symTable.isDefined(variableName)) {
            throw new ExpressionException("Variable not defined: " + variableName);
        }
        return symTable.getValue(variableName);
    }
    @Override
    public Type typecheck(MyDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv.get(variableName);
    }

}

