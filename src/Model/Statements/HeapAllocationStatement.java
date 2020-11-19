package Model.Statements;

import Model.Exceptions.InvalidTypeException;
import Model.Exceptions.MyException;
import Model.Exceptions.UndeclaredVariableException;
import Model.Expressions.IExpression;
import Model.ProgramState;
import Model.Types.ReferenceType;
import Model.Values.IValue;
import Model.Values.ReferenceValue;

public class HeapAllocationStatement implements IStatement{

    private String variableName;
    private IExpression expression;

    public HeapAllocationStatement(String variableName, IExpression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        if(! state.getSymbolTabel().isDefined(this.variableName))
            throw new UndeclaredVariableException("Heap allocation: variable " +  this.variableName +" not declared");

        IValue refValue = state.getSymbolTabel().lookup(this.variableName);
        if(! (refValue.getType() instanceof ReferenceType))
            throw new InvalidTypeException("Heap allocation: variable " + this.variableName + " not a reference type");

        IValue expressionResult = this.expression.evaluate(state.getSymbolTabel(), state.getHeapTable());

        if(! expressionResult.getType().equals(((ReferenceValue)refValue).getLocationType()))
            throw new InvalidTypeException("Heap allocation: reference location type and expression type do not match");

        int address = state.getHeapTable().getNewFreeLocation();
        state.getHeapTable().add(address, expressionResult);
        state.getSymbolTabel().update(this.variableName, new ReferenceValue(address, ((ReferenceValue)refValue).getLocationType()));

        return state;
    }

    @Override
    public IStatement deepCopy() {
        return new HeapAllocationStatement(this.variableName, this.expression.deepCopy());
    }

    @Override
    public String toString() {
        return "new(" + this.variableName + ", " + this.expression.toString() + ")";
    }
}
