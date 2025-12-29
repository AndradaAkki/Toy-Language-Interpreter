package model.statement;

import model.expression.Expression;
import model.myException.MyException;
import model.state.MyDictionary;
import model.state.ProgramState;
import model.type.Type;

public record PrintStatement(Expression expression) implements Statement {

    @Override
    // Execute the print statement by evaluating the expression and adding the result to the output list
    public ProgramState execute(ProgramState state) throws Exception {
        state.out().add(expression.evaluate(state.symbolTable(),state.heap()));
        return state;
    }
    @Override
    public MyDictionary<String, Type> typecheck(
            MyDictionary<String, Type> typeEnv) throws MyException {

        expression.typecheck(typeEnv);
        return typeEnv;
    }

}