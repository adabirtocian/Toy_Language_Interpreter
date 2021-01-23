package Model.Statements.Heap;

import Model.ADTs.Dictionary.IDictionary;
import Model.ADTs.IHeapTable;
import Model.Exceptions.*;
import Model.Expressions.IExpression;
import Model.ProgramState;
import Model.Statements.IStatement;
import Model.Types.IType;
import Model.Types.ReferenceType;
import Model.Values.IValue;
import Model.Values.ReferenceValue;

public class HeapWritingStatement implements IStatement {

    private String variableName;
    private IExpression expression;

    public HeapWritingStatement(String variableName, IExpression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        IDictionary<String, IValue> symbolTable = state.getSymbolTabel();
        IHeapTable<Integer, IValue> heapTable = state.getHeapTable();

        if(! symbolTable.isDefined(this.variableName))
            throw new UndeclaredVariableException("Heap writing: variable " + this.variableName + " not defined");

        IValue variableValue = symbolTable.lookup(this.variableName);
        if(! (variableValue.getType() instanceof ReferenceType))
            throw new InvalidTypeException("Heap writing: variable " + this.variableName + " is not a reference type");

        int address = ((ReferenceValue) variableValue).getHeapAddress();
        if(!heapTable.isDefined(address))
            throw new InvalidKeyException("Heap writing: address " + address + " not in heap table");

        IValue expressionResult = this.expression.evaluate(symbolTable, heapTable);
        if(! expressionResult.getType().equals(((ReferenceValue)variableValue).getLocationType()))
            throw new InvalidTypeException("Heap writing: mismatched types");

        heapTable.update(address, expressionResult);

        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new HeapWritingStatement(this.variableName, this.expression.deepCopy());
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnvironment) throws MyException {
        IType typeVar = typeEnvironment.lookup(this.variableName);
        IType typeExpression = this.expression.typeCheck(typeEnvironment);
        if(typeVar.equals(new ReferenceType(typeExpression)))
            return typeEnvironment;
        throw new InvalidTypeException("Heap writing: right hand side and left hand side have different types");
    }

    @Override
    public String toString() {
        return "writeHeap(" + this.variableName + "," + this.expression.toString() + ")";
    }
}
