package Model.Statements;

import Model.ADTs.Dictionary.IDictionary;
import Model.ADTs.Stack.IStack;
import Model.Exceptions.MyException;
import Model.ProgramState;
import Model.Types.IType;

public class CompStatement implements IStatement {
    IStatement firstStatement;
    IStatement secondStatement;

    public CompStatement(IStatement firstStatement, IStatement secondStatement) {
        this.firstStatement = firstStatement;
        this.secondStatement = secondStatement;
    }

    public IStatement getFirstStatement() {
        return firstStatement;
    }

    public void setFirstStatement(IStatement firstStatement) {
        this.firstStatement = firstStatement;
    }

    public IStatement getSecondStatement() {
        return secondStatement;
    }

    public void setSecondStatement(IStatement secondStatement) {
        this.secondStatement = secondStatement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        IStack<IStatement> exeStack = state.getExeStack();
        exeStack.push(this.secondStatement);
        exeStack.push(this.firstStatement);

        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new CompStatement(this.firstStatement.deepCopy(), this.secondStatement.deepCopy());
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnvironment) throws MyException {
        return this.secondStatement.typeCheck(this.firstStatement.typeCheck(typeEnvironment));
    }

    public String toString() {
        return "(" + this.firstStatement.toString() + "; " + this.secondStatement.toString() + ")";
    }
}
