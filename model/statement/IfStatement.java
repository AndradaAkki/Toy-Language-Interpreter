package model.statement;

import model.expression.Expression;
import model.myException.MyException;
import model.myException.StatementException;
import model.state.MyDictionary;
import model.state.ProgramState;
import model.type.BoolType;
import model.type.Type;
import model.value.BoolValue;
import model.value.Value;

public record IfStatement(Expression condition, Statement thenBranch, Statement elseBranch) implements Statement {

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        Value result = condition.evaluate(state.symbolTable(), state.heap());
        if (result instanceof BoolValue booleanValue) {
            if (booleanValue.getValue()) {
                state.executionStack().push(thenBranch);
            } else {
                state.executionStack().push(elseBranch);
            }
        } else {
            throw new StatementException("Condition expression does not evaluate to a boolean.");
        }
        return state;
    }

    @Override
    public String toString() {
        return "if(" + condition + ") then {" + thenBranch + "} else {" + elseBranch + "}";
    }

    @Override
    public MyDictionary<String, Type> typecheck(
            MyDictionary<String, Type> typeEnv) throws MyException {

        Type condType = condition.typecheck(typeEnv);

        if (!condType.equals(new BoolType()))
            throw new MyException("If condition is not boolean");

        thenBranch.typecheck(typeEnv.deepCopy());
        elseBranch.typecheck(typeEnv.deepCopy());

        return typeEnv;
    }


}
