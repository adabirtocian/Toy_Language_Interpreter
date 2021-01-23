package Model.Statements;

import Model.ADTs.Dictionary.IDictionary;
import Model.Exceptions.MyException;
import Model.ProgramState;
import Model.Types.IType;

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

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnvironment) throws MyException {
        return typeEnvironment;
    }
}
