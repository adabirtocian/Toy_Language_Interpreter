package Model.Statements;

import Model.ADTs.IDictionary;
import Model.Exceptions.InvalidTypeException;
import Model.Exceptions.MyException;
import Model.Exceptions.UndeclaredVariableException;
import Model.Expressions.IExpression;
import Model.ProgramState;
import Model.Types.IType;
import Model.Types.ReferenceType;
import Model.Values.IValue;
import Model.Values.ReferenceValue;

public class NewStatement implements IStatement{

    private String variableName;
    private IExpression expression;

    public NewStatement(String variableName, IExpression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        var symbolTable = state.getSymbolTabel();
        var heapTable = state.getHeapTable();
        if(! symbolTable.isDefined(this.variableName))
            throw new UndeclaredVariableException("Heap allocation: variable " +  this.variableName +" not declared");

        IValue variableValue = symbolTable.lookup(this.variableName);
        if(! (variableValue.getType() instanceof ReferenceType))
            throw new InvalidTypeException("Heap allocation: variable " + this.variableName + " not a reference type");

        IValue expressionResult = this.expression.evaluate(symbolTable, heapTable);

        if(! expressionResult.getType().equals(((ReferenceValue)variableValue).getLocationType()))
            throw new InvalidTypeException("Heap allocation: reference location type and expression type do not match");

        int address = heapTable.getNewFreeLocation();
        heapTable.add(address, expressionResult);
        symbolTable.update(this.variableName, new ReferenceValue(address, ((ReferenceValue)variableValue).getLocationType()));

        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new NewStatement(this.variableName, this.expression.deepCopy());
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnvironment) throws MyException {
        IType typeVar = typeEnvironment.lookup(this.variableName);
        IType typeExpression = this.expression.typeCheck(typeEnvironment);
        if(typeVar.equals(new ReferenceType(typeExpression)))
            return typeEnvironment;
        else throw new InvalidTypeException("New statement: right hand side and left hand side have different types");
    }

    @Override
    public String toString() {
        return "new(" + this.variableName + ", " + this.expression.toString() + ")";
    }
}
