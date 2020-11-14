package Repository;

import Model.ADTs.IList;
import Model.ADTs.List;
import Model.Exceptions.MyException;
import Model.Exceptions.LogProgramStateException;
import Model.ProgramState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Repository implements IRepository{

    IList<ProgramState> myProgramStates;
    String filePath;

    public Repository(String filePath) {
        this.myProgramStates  = new List<>();
        this.filePath = filePath;
    }

    @Override
    public ProgramState getCurrentProgramState() throws MyException {
        return this.myProgramStates.pop();
    }

    @Override
    public void addProgramState(ProgramState newState) {
        this.myProgramStates.add(newState);
    }

    @Override
    public void logProgramStateExecution() throws MyException {
        try {
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(this.filePath, true)));
            for(int i = 0; i < this.myProgramStates.size(); i++){
                logFile.print(this.myProgramStates.getValue(i).toString());
            }
            logFile.close();
        } catch (IOException exception) {
            throw new LogProgramStateException("LogProgramState: Log file could not be opened");
        }
    }
}
