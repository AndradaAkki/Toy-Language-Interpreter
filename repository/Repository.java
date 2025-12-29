package repository;

import model.state.ProgramState;

public interface Repository {

    void setProgramState(ProgramState programState);
    ProgramState getProgramState();
    void logProgramState() throws Exception;

}
