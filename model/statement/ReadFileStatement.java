package model.statement;

import model.expression.Expression;
import model.myException.ExpressionException;
import model.myException.MyException;
import model.myException.StatementException;
import model.state.MyDictionary;
import model.state.ProgramState;
import model.type.IntType;
import model.type.StringType;
import model.type.Type;
import model.value.IntValue;
import model.value.StringValue;

import java.io.BufferedReader;
import java.io.IOException;

public record ReadFileStatement(Expression expression, String varName) implements Statement {

    @Override
    public ProgramState execute(ProgramState programState) throws Exception {
        var value = expression.evaluate(programState.symbolTable(), programState.heap());
        if(!(value instanceof StringValue(String fileName))){
            throw new StatementException("Type must be String");
        }
        if(!programState.fileTable().isOpen(fileName)){
            throw new ExpressionException(fileName);
        }
        BufferedReader br = programState.fileTable().getOpenFile(fileName);
        String line;
        try {
            line = br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(line == null){
            programState.symbolTable().update(varName,new IntValue(0));
        }
        else{
            programState.symbolTable().update(varName,new IntValue(Integer.parseInt(line)));
        }
        return programState;

    }
    @Override
    public MyDictionary<String, Type> typecheck(
            MyDictionary<String, Type> typeEnv) throws MyException {

        Type exprType = expression.typecheck(typeEnv);
        Type varType = typeEnv.get(varName);

        if (!exprType.equals(new StringType()))
            throw new MyException("ReadFile expression must be string");

        if (!varType.equals(new IntType()))
            throw new MyException("ReadFile variable must be int");

        return typeEnv;
    }

}
