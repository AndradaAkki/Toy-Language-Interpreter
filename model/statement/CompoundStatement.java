package model.statement;
import model.myException.MyException;
import model.state.*;
import model.type.Type;

public record CompoundStatement(Statement first, Statement second) implements Statement {

    @Override
    // Executes the compound statement by pushing the second statement first, then the first statement onto the execution stack.
    public ProgramState execute(ProgramState state) {
        var stack = state.executionStack();
        stack.push(second);
        stack.push(first);
        return state;
    }

    @Override
    // Creates a deep copy of the compound statement.
    public Statement copyStatement() {
        return new CompoundStatement(first.copyStatement(), second.copyStatement());
    }

    @Override
    public MyDictionary<String, Type> typecheck(
            MyDictionary<String, Type> typeEnv) throws MyException {

        return second.typecheck(first.typecheck(typeEnv));
    }

}