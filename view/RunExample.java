package view;

import controller.Controller;
import controller.ProgramController;
import model.myException.MyException;
import model.state.*;
import model.statement.Statement;
import repository.ProgramRepository;

public class RunExample extends Command {

    private final Statement program;
    private final String logFilePath;

    public RunExample(String key, String desc, Statement program, String logFilePath) {
        super(key, desc);
        this.program = program;
        this.logFilePath = logFilePath;
    }

    @Override
    public void execute() {

        Controller ctrl;

        try {
            // TYPECHECK happens HERE
            program.typecheck(new MyDictionary<>());
        } catch (MyException e) {
            System.out.println("Typecheck error: " + e.getMessage());
            System.out.println("Program was not executed due to typecheck errors.");
            return;
        }

        // Build controller ONLY if typecheck passed
        var stack = new StackExecutionStack();
        var symTable = new MapSymbolTable();
        var out = new ListOut();
        var fileTable = new MapFileTable();
        var heap = new MapHeap();

        stack.push(program);

        ProgramState state = new ProgramState(stack, symTable, out, fileTable, heap);
        ProgramRepository repo = new ProgramRepository(logFilePath);
        repo.setProgramState(state);

        ctrl = new ProgramController(repo);

        try {
            ctrl.allSteps();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
