package Model.Statements;

import Model.ADTs.IStack;
import Model.Exceptions.MyException;
import Model.Exceptions.StatementException;
import Model.Expressions.IExpression;
import Model.ProgramState;
import Model.Types.BoolType;
import Model.Types.IType;
import Model.Values.BoolValue;
import Model.Values.IValue;

public class IfStatement implements IStatement{
    IExpression expression;
    IStatement thenStatement;
    IStatement elseStatement;

    public IfStatement(IExpression IExpression, IStatement thenStatement, IStatement elseStatement) {
        this.expression = IExpression;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    public IExpression getExpression() {
        return expression;
    }

    public void setExpression(IExpression expression) {
        this.expression = expression;
    }

    public IStatement getThenStatement() {
        return thenStatement;
    }

    public void setThenStatement(IStatement thenStatement) {
        this.thenStatement = thenStatement;
    }

    public IStatement getElseStatement() {
        return elseStatement;
    }

    public void setElseStatement(IStatement elseStatement) {
        this.elseStatement = elseStatement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        IValue valueExpression = this.expression.evaluate(state.getSymbolTabel());
        IType type = valueExpression.getType();
        if(!type.equals(new BoolType())) {
            throw new StatementException("Conditional expression is not a boolean");
        }
        else {
            IStack<IStatement> exeStack = state.getExeStack();
            BoolValue boolValue = (BoolValue) valueExpression;
            boolean condition = boolValue.getValue();
            if(condition) exeStack.push(this.thenStatement);
            else exeStack.push(this.elseStatement);
        }
        return state;
    }

    @Override
    public IStatement deepCopy() {
        return new IfStatement(this.expression.deepCopy(), this.thenStatement.deepCopy(), this.elseStatement.deepCopy());
    }

    public String toString() {
        return "(if (" + this.expression.toString() + ") then (" + this.thenStatement.toString() + ") else (" +
                this.elseStatement.toString() + "))";
    }
}
