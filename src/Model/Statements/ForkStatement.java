package Model.Statements;

import Model.ADTs.IStack;
import Model.ADTs.MyStack;
import Model.Exceptions.MyException;
import Model.ProgramState;

public class ForkStatement implements IStatement {

    private IStatement statement;

    public ForkStatement(IStatement statement) {
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        IStack<IStatement> newExeStack = new MyStack<>();

        return new ProgramState(newExeStack, state.getSymbolTabel().deepCopy(), state.getOut(),
                this.statement, state.getFileTable(), state.getHeapTable());
    }

    @Override
    public String toString() {
        return "fork( " + this.statement.toString() + " )";
    }

    @Override
    public IStatement deepCopy() {
        return new ForkStatement(this.statement.deepCopy());
    }
}
