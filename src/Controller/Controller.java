package Controller;

import Model.ADTs.IStack;
import Model.Exceptions.ADTException;
import Model.Exceptions.MyException;
import Model.Exceptions.StatementException;
import Model.ProgramState;
import Model.Statements.IStatement;
import Repository.IRepository;

public class Controller {
    IRepository repository;
    StringBuilder stringAllSteps;


    public Controller(IRepository repository) {
        this.repository = repository;
        this.stringAllSteps = new StringBuilder();
    }

    public void addProgram(ProgramState programState) {
        this.repository.addProgramState(programState);
    }

    public ProgramState oneStep(ProgramState state) throws MyException {
        IStack<IStatement> exeStack = state.getExeStack();
        if(exeStack.isEmpty()) {
            throw new StatementException("ExeStack is empty");
        }

        IStatement currentStatement = exeStack.pop();
        return currentStatement.execute(state);
    }

    public void allSteps() throws MyException{
        ProgramState programState = this.repository.getCurrentProgramState();
        this.repository.logProgramStateExecution();
        while(!programState.getExeStack().isEmpty()) {
            ProgramState newProgramState = this.oneStep(programState);
            //this.addStepOutput(newProgramState);
            this.repository.logProgramStateExecution();
        }
    }

    public String getOutput() throws MyException {
        return "Output\n[" + this.repository.getCurrentProgramState().getOut().toString() + "]\n";
    }

    public String getAllSteps() {
        return this.stringAllSteps.toString();
    }

    public void addStepOutput(ProgramState currentProgramState) {
        this.stringAllSteps.append("==== Next step ====\n");
        this.stringAllSteps.append(currentProgramState.toString());
    }
}
