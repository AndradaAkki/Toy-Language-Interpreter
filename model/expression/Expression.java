package model.expression;


import model.myException.MyException;
import model.state.Heap;
import model.state.MyDictionary;
import model.state.SymbolTable;
import model.value.Value;
import model.type.Type;

public interface Expression {
    Value evaluate(SymbolTable symbolTable, Heap heap) throws Exception;
    Type typecheck(MyDictionary<String, Type> typeEnv) throws MyException;

}
