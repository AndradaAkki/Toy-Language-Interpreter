package model.statement;

import model.expression.Expression;
import model.myException.ExpressionException;
import model.myException.MyException;
import model.state.ProgramState;
import model.type.StringType;
import model.type.Type;
import model.value.StringValue;
import model.state.MyDictionary;

public record CloseRFileStatement(Expression expression) implements Statement {

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        var value = expression.evaluate(state.symbolTable(), state.heap());
        if(!(value instanceof StringValue(String fileName))){
            throw new ExpressionException("Type must be String");
        }

        state.fileTable().closeFile(fileName);
        return state;
    }

    @Override
    public MyDictionary<String, Type> typecheck(
            MyDictionary<String, Type> typeEnv) throws MyException {

        Type type = expression.typecheck(typeEnv);

        if (!type.equals(new StringType()))
            throw new MyException("CloseRFile requires a string expression");

        return typeEnv;
    }

}
