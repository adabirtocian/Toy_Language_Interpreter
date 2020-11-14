package Model.Statements;

import Model.ADTs.IList;
import Model.Exceptions.MyException;
import Model.Expressions.IExpression;
import Model.ProgramState;
import Model.Values.IValue;

public class PrintStatement implements IStatement {
    IExpression expression;

    public PrintStatement(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        IList<IValue> out = state.getOut();
        IValue valueExpression = this.expression.evaluate(state.getSymbolTabel());
        out.add(valueExpression);

        return state;
    }

    @Override
    public IStatement deepCopy() {
        return new PrintStatement(this.expression.deepCopy());
    }

    public String toString() {
        return "print(" + this.expression.toString() + ")";
    }
}
