package controller;

import model.state.ProgramState;
import model.statement.Statement;
import repository.Repository;

import java.awt.event.ActionListener;
import java.util.List;
import model.value.RefValue;
import model.value.Value;

import java.util.*;
import java.util.stream.Collectors;


public class ProgramController implements Controller {
    private final Repository repository;

    public ProgramController(Repository repository) {
        this.repository = repository;
    }

    @Override
    public ProgramState oneStep(ProgramState programState) throws Exception {
        var stack = programState.executionStack();
        if (stack.isEmpty()) {
            throw new RuntimeException("Program state stack is empty");
        }
        Statement currentStatement = stack.pop();
        return currentStatement.execute(programState);
    }
    private List<Integer> getAddressesFromSymTable(Collection<Value> values) {
        return values.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> ((RefValue) v).getAddress())
                .collect(Collectors.toList());
    }


    private Map<Integer, Value> safeGarbageCollector(List<Integer> symTableAddr, Map<Integer, Value> heap) {

        Set<Integer> reachable = new HashSet<>(symTableAddr);

        boolean changed;
        do {
            changed = false;

            for (Value value : heap.values()) {
                if (value instanceof RefValue refValue) {
                    if (reachable.add(refValue.getAddress())) {
                        changed = true;
                    }
                }
            }

        } while (changed);

        return heap.entrySet()
                .stream()
                .filter(e -> reachable.contains(e.getKey()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));
    }



    @Override
    public void allSteps() throws Exception {
        ProgramState program = repository.getProgramState();

        while (!program.executionStack().isEmpty()) {

            oneStep(program);

            // GARBAGE COLLECTION
            List<Integer> addresses = getAddressesFromSymTable(
                    program.symbolTable().getValues()
            );

            Map<Integer, Value> newHeap = safeGarbageCollector(
                    addresses,
                    program.heap().getContent()
            );

            program.heap().setContent(newHeap);


            repository.logProgramState();
        }

        System.out.println("Final output:\n" + program.out());
    }

}
