package Model.Statements;

import Model.ADTs.Dictionary.IDictionary;
import Model.ADTs.IStack;
import Model.Exceptions.InvalidTypeException;
import Model.Exceptions.MyException;
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
        IValue valueExpression = this.expression.evaluate(state.getSymbolTabel(), state.getHeapTable());
        IType type = valueExpression.getType();
        if(!type.equals(new BoolType())) {
            throw new InvalidTypeException("Conditional expression is not a boolean");
        }
        else {
            IStack<IStatement> exeStack = state.getExeStack();
            BoolValue boolValue = (BoolValue) valueExpression;
            boolean condition = boolValue.getValue();
            if(condition) exeStack.push(this.thenStatement);
            else exeStack.push(this.elseStatement);
        }
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new IfStatement(this.expression.deepCopy(), this.thenStatement.deepCopy(), this.elseStatement.deepCopy());
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnvironment) throws MyException {
        IType typeExpression = this.expression.typeCheck(typeEnvironment);
        if(typeExpression.equals(new BoolType())) {
            this.thenStatement.typeCheck(typeEnvironment.deepCopy());
            this.elseStatement.typeCheck(typeEnvironment.deepCopy());
            return typeEnvironment;
        }
        else throw new InvalidTypeException("The condition of if has not boolean type");
    }

    public String toString() {
        return "if (" + this.expression.toString() + ") then (" + this.thenStatement.toString() + ") else (" +
                this.elseStatement.toString() + ")";
    }
}
