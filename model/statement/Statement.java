package model.statement;

import model.myException.MyException;
import model.state.MyDictionary;
import model.state.ProgramState;
import model.type.Type;

public interface Statement {
    ProgramState execute(ProgramState programState) throws Exception;
    default Statement copyStatement() {
        return this;
    }
    default String format(){
        return this.toString();
    }
    MyDictionary<String, Type> typecheck(
            MyDictionary<String, Type> typeEnv) throws MyException;

}
