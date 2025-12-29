package model.statement;

import model.expression.Expression;
import model.myException.MyException;
import model.myException.StatementException;
import model.state.MyDictionary;
import model.state.ProgramState;
import model.type.StringType;
import model.type.Type;
import model.value.StringValue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;

public record OpenRFileStatement(Expression expression) implements Statement {
    @Override
    public ProgramState execute(ProgramState programState) throws Exception {
        var value = expression.evaluate(programState.symbolTable(), programState.heap());
        if(!(value instanceof StringValue(String fileName))){
            throw new StatementException("Expression does not evaluate to a string value");
        }

        if(programState.fileTable().isOpen(fileName)){
            throw new StatementException("File already opened: " + fileName);
        }

        BufferedReader br;
        try {
            br = new BufferedReader(new java.io.FileReader(fileName));
        } catch (Exception e) {
            throw new StatementException("Could not open file: " + fileName);
        }
        programState.fileTable().addOpenFile(fileName, br);
        return programState;
    }

    @Override
    public MyDictionary<String, Type> typecheck(
            MyDictionary<String, Type> typeEnv) throws MyException {

        Type type = expression.typecheck(typeEnv);

        if (!type.equals(new StringType()))
            throw new MyException("OpenRFile requires a string expression");

        return typeEnv;
    }

}
