package Repository;

import Model.Exceptions.MyException;
import Model.Exceptions.LogProgramStateException;
import Model.ProgramState;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository{

    List<ProgramState> myProgramStates;
    String filePath;

    public Repository(String filePath) {
        this.myProgramStates  = new ArrayList<>();
        this.filePath = filePath;
    }

    @Override
    public void addProgramState(ProgramState newState) {
        this.myProgramStates.add(newState);
    }

    @Override
    public void logProgramStateExecution(ProgramState state) throws MyException {
        try {
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(this.filePath, true)));
            logFile.print(state.toString());
            logFile.close();
        } catch (IOException exception) {
            throw new LogProgramStateException("LogProgramState: Log file could not be opened");
        }
    }

    @Override
    public List<ProgramState> getProgramStateList() {
        return this.myProgramStates;
    }

    @Override
    public void setProgramStateList(List<ProgramState> newProgramStateList) {
        this.myProgramStates = newProgramStateList;
    }

    @Override
    public void clearLogFile() throws MyException {
        try{
            PrintWriter logFile = new PrintWriter(this.filePath);
            logFile.close();
        } catch (IOException exception) {
            throw new LogProgramStateException("ClearLogFile: Log file could not be closed");
        }
    }
}
