package model.statement;

import model.state.Out;
import model.state.ProgramState;
import model.type.Type;
import model.state.MyDictionary;

public class NopStatement implements Statement {
    @Override
    public ProgramState execute(ProgramState state) {
        return state;
    }

    @Override
    public String toString() {
        return "NOP";
    }

    @Override
    public  MyDictionary<String, Type> typecheck(
            MyDictionary<String, Type> typeEnv) {
        return typeEnv;
    }
}
