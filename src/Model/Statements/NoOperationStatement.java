package Model.Statements;

import Model.Exceptions.MyException;
import Model.ProgramState;

public class NoOperationStatement implements IStatement {

    public NoOperationStatement() {}

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new NoOperationStatement();
    }
}
