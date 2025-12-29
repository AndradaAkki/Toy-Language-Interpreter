package model.statement;

import model.myException.MyException;
import model.myException.StatementException;
import model.state.MyDictionary;
import model.state.ProgramState;
import model.type.Type;

public record VarDeclStatement(Type type, String variableName) implements Statement {

    @Override
    public ProgramState execute (ProgramState state) {
        var symTable = state.symbolTable();
        if (symTable.isDefined(variableName)) {
            throw new StatementException("Variable " + variableName + " is already defined.");
        }
        symTable.declareVariable(variableName, type);
        return state;
    }

    @Override
    public String format() {
        return type.toString() + " " + variableName;
    }

    @Override
    public MyDictionary<String, Type> typecheck(
            MyDictionary<String, Type> typeEnv) throws MyException {

        typeEnv.put(variableName, type);
        return typeEnv;
    }


}
