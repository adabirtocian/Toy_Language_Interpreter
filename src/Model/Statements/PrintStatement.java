package Model.Statements;

import Model.ADTs.Dictionary.IDictionary;
import Model.ADTs.List.IList;
import Model.Exceptions.MyException;
import Model.Expressions.IExpression;
import Model.ProgramState;
import Model.Types.IType;
import Model.Values.IValue;

public class PrintStatement implements IStatement {
    IExpression expression;

    public PrintStatement(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        IList<IValue> out = state.getOut();
        IValue valueExpression = this.expression.evaluate(state.getSymbolTabel(), state.getHeapTable());
        out.add(valueExpression);

        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new PrintStatement(this.expression.deepCopy());
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnvironment) throws MyException {
        this.expression.typeCheck(typeEnvironment);
        return typeEnvironment;
    }

    public String toString() {
        return "print(" + this.expression.toString() + ")";
    }
}
