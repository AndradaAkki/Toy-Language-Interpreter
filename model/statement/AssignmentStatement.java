package model.statement;

import model.expression.Expression;
import model.myException.MyException;
import model.myException.StatementException;
import model.state.MyDictionary;
import model.state.ProgramState;
import model.state.SymbolTable;
import model.type.Type;
import model.value.Value;

public record AssignmentStatement (Expression expression, String variableName) implements Statement {

    @Override
    //Gets the current program state and updates the symbol table with the new value for the variable
    public ProgramState execute(ProgramState state) throws Exception {
        SymbolTable symbolTable= state.symbolTable();
        if(!symbolTable.isDefined(variableName)){
            throw new StatementException("Variable not defined: " + variableName);
        }
        Value value= expression.evaluate(symbolTable, state.heap());
        if (!value.getType().equals(symbolTable.getType(variableName))){
            throw new StatementException("Type mismatch in assignment to variable: " + variableName);
        }
        symbolTable.update(variableName, value);
        return null;
    }

    @Override
    public Statement copyStatement() {
        return new AssignmentStatement(expression, variableName);
    }

    @Override
    public MyDictionary<String, Type> typecheck(
            MyDictionary<String, Type> typeEnv) throws MyException {

        Type varType = typeEnv.get(variableName);
        Type exprType = expression.typecheck(typeEnv);

        if (!varType.equals(exprType))
            throw new MyException(
                    "Assignment: right and left hand sides have different types"
            );

        return typeEnv;
    }


}
