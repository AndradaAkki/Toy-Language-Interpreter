package controller;

import model.state.ProgramState;

public interface Controller {
    ProgramState oneStep(ProgramState programState) throws Exception;
    void allSteps() throws Exception;
}
