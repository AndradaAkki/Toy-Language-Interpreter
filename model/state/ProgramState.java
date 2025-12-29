package model.state;

public record ProgramState(ExecutionStack executionStack, SymbolTable symbolTable, Out out, FileTable fileTable, Heap heap) {

    @Override
    public String toString() {
        return "Execution Stack: " + executionStack.Format() +
                "\nSymbol Table: " + symbolTable +
                "\nOutput: " + out ;

    }
}
