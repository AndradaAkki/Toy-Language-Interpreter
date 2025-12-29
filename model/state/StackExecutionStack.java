package model.state;

import model.myException.ADTException;
import model.statement.Statement;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class StackExecutionStack implements ExecutionStack {
    private final List<Statement> stack = new LinkedList<>();


    @Override
    public void push(Statement statement) {
        stack.addFirst(statement);
    }

    @Override
    public Statement pop() {
        if (stack.isEmpty()) {
            throw new ADTException("Execution stack is empty");
        }
        return stack.removeFirst();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public String Format() {
        return stack.stream()
                .map(Object::toString)
                .collect(Collectors.joining(" | "));
    }

}
