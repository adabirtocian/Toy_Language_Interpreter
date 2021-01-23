package Model.Statements.Loops;

import Model.ADTs.Dictionary.IDictionary;
import Model.ADTs.IStack;
import Model.Exceptions.InvalidTypeException;
import Model.Exceptions.MyException;
import Model.Expressions.IExpression;
import Model.ProgramState;
import Model.Statements.IStatement;
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
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new WhileStatement(this.expression.deepCopy(), this.statement.deepCopy());
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnvironment) throws MyException {
        IType typeExpression = this.expression.typeCheck(typeEnvironment);
        if(typeExpression.equals(new BoolType())) {
            this.statement.typeCheck(typeEnvironment.deepCopy());
            return typeEnvironment;
        }
        else throw new InvalidTypeException("The condition of while is not boolean");
    }

    @Override
    public String toString() {
        return "while( " + this.expression.toString() + " ) {" + this.statement.toString() + "}";
    }
}
