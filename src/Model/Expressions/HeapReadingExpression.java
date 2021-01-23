package Model.Expressions;

import Model.ADTs.Dictionary.IDictionary;
import Model.ADTs.HeapTable.IHeapTable;
import Model.Exceptions.InvalidKeyException;
import Model.Exceptions.InvalidTypeException;
import Model.Exceptions.MyException;
import Model.Types.IType;
import Model.Types.ReferenceType;
import Model.Values.IValue;
import Model.Values.ReferenceValue;

public class HeapReadingExpression implements IExpression {

    private IExpression expression;

    public HeapReadingExpression(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public IValue evaluate(IDictionary<String, IValue> symbolTable, IHeapTable<Integer, IValue> heapTable) throws MyException {
        IValue expressionResult = this.expression.evaluate(symbolTable, heapTable);
        if(! (expressionResult.getType() instanceof ReferenceType))
            throw new InvalidTypeException("Heap reading: expression does not evaluate to reference type");

        int address = ((ReferenceValue)expressionResult).getHeapAddress();

        if(! heapTable.isDefined(address))
            throw new InvalidKeyException("Heap reading: invalid address in the heap");
        
        return heapTable.lookup(address);
    }

    @Override
    public IExpression deepCopy() {
        return new HeapReadingExpression(this.expression.deepCopy());
    }

    @Override
    public IType typeCheck(IDictionary<String, IType> typeEnvironment) throws MyException {
        IType type = this.expression.typeCheck(typeEnvironment);
        if(type instanceof ReferenceType) {
            ReferenceType reference = (ReferenceType) type;
            return reference.getInner();
        }
        else throw new InvalidTypeException("Heap reading: Argument not a reference type");
    }

    @Override
    public String toString() {
        return "readHeap(" + this.expression.toString() + ")";
    }

}
