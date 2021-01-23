package Repository;

import Model.Exceptions.MyException;
import Model.ProgramState;

import java.util.List;

public interface IRepository {
    void addProgramState(ProgramState newState);
    void logProgramStateExecution(ProgramState state) throws MyException;
    List<ProgramState> getProgramStateList();
    void setProgramStateList(List<ProgramState> newProgramStateList);
    void clearLogFile() throws MyException;
}
