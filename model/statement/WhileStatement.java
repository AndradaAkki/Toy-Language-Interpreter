package model.statement;
import model.expression.Expression;
import model.myException.MyException;
import model.state.MyDictionary;
import model.state.ProgramState;
import model.type.BoolType;
import model.type.Type;
import model.value.BoolValue;

public class WhileStatement implements Statement {
    private final Expression condition;
    private final Statement body;

    public WhileStatement(Expression condition, Statement body) {
        this.condition = condition;
        this.body = body;
    }
    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        var stack = state.executionStack();
        var value = condition.evaluate(state.symbolTable(), state.heap());
        if (!(value instanceof BoolValue boolValue)) {
            throw new RuntimeException("While condition is not a boolean.");
        }
        if (boolValue.getValue()) {
            stack.push(this);
            stack.push(body);
        }
        return state;
    }
    @Override
    public String toString() {
        return "while(" + condition + ") { " + body + " }";
    }

    @Override
    public MyDictionary<String, Type> typecheck(
            MyDictionary<String, Type> typeEnv) throws MyException {

        Type condType = condition.typecheck(typeEnv);

        if (!condType.equals(new BoolType()))
            throw new MyException("While condition is not boolean");

        body.typecheck(typeEnv.deepCopy());
        return typeEnv;
    }

}
