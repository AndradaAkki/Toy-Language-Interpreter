package model.state;

import model.statement.Statement;

public interface ExecutionStack {

    //pushes a statement onto the stack
    void push(Statement statement);

    //removes and returns the top statement
    Statement pop();

    //returns a boolean indicating whether the stack is empty
    boolean isEmpty();

    String Format();
}
