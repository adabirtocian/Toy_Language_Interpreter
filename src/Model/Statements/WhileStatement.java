package Model.Statements;

import Model.ADTs.IStack;
import Model.Exceptions.InvalidTypeException;
import Model.Exceptions.MyException;
import Model.Expressions.IExpression;
import Model.ProgramState;
import Model.Types.BoolType;
import Model.Types.IType;
import Model.Values.BoolValue;
import Model.Values.IValue;

public class WhileStatement implements IStatement {

    private IExpression expression;
    private IStatement statement;

    public WhileStatement(IExpression expression, IStatement statement) {
        this.expression = expression;
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        IValue valueExpression = this.expression.evaluate(state.getSymbolTabel(), state.getHeapTable());
        IType typeExpression = valueExpression.getType();
        if(! typeExpression.equals(new BoolType()))
            throw new InvalidTypeException("Conditional expression is not a boolean");
        else {
            IStack<IStatement> exeStack = state.getExeStack();
            boolean condition = ((BoolValue) valueExpression).getValue();
            if(condition) {
                exeStack.push(this);
                exeStack.push(this.statement);
            }
        }
        return state;
    }

    @Override
    public IStatement deepCopy() {
        return new WhileStatement(this.expression.deepCopy(), this.statement.deepCopy());
    }
}
