package Model.Statements;
import Model.ADTs.IDictionary;
import Model.Exceptions.MyException;
import Model.ProgramState;
import Model.Types.IType;

public interface IStatement {
    ProgramState execute(ProgramState state) throws MyException;
    IStatement deepCopy();
    IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnvironment) throws MyException;
}
