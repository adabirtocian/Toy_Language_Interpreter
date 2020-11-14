package Repository;

import Model.Exceptions.MyException;
import Model.ProgramState;

public interface IRepository {
    ProgramState getCurrentProgramState() throws MyException;
    void addProgramState(ProgramState newState);
    void logProgramStateExecution() throws MyException;
}
