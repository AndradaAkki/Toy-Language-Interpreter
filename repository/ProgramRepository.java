package repository;

import model.myException.MyException;
import model.state.ProgramState;

import java.io.*;

public class ProgramRepository implements Repository {
    private ProgramState programState;
    private final String logFilePath;
    public ProgramRepository(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    @Override
    public void setProgramState(ProgramState state) {
        programState = state;
    }

    @Override
    public ProgramState getProgramState() {
        return programState;
    }

    @Override
    public void logProgramState() throws Exception {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(
                new FileWriter(logFilePath, true)))) {

            out.println("ExeStack:");
            out.println(programState.executionStack().Format());

            out.println("SymTable:");
            out.println(programState.symbolTable());


            out.println("Heap:");
            out.println(programState.heap());

            out.println("Out:");
            out.println(programState.out());

            out.println("------------\n");

        } catch (IOException e) {
            throw new MyException("Could not write log file: " + e.getMessage());
        }
    }

}
