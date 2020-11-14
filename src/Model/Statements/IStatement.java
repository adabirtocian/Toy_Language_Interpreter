package Model.Statements;
import Model.Exceptions.MyException;
import Model.ProgramState;

public interface IStatement {
    ProgramState execute(ProgramState state) throws MyException;
    IStatement deepCopy();
}
