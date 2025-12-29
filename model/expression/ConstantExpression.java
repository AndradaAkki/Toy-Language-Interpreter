package model.expression;

import model.state.Heap;
import model.state.MyDictionary;
import model.state.SymbolTable;
import model.value.Value;
import model.type.Type;

public record ConstantExpression (Value value) implements Expression{

    @Override
    public Value evaluate(SymbolTable symTable, Heap heap) {
        return value;
    }
    @Override

    public Type typecheck(MyDictionary<String, Type> typeEnv) {
        return value.getType();
    }

}
