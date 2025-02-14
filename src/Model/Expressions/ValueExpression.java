package Model.Expressions;

import Model.ADTs.Dictionary.IDictionary;
import Model.ADTs.HeapTable.IHeapTable;
import Model.Exceptions.MyException;
import Model.Types.IType;
import Model.Values.IValue;


public class ValueExpression implements IExpression {
    IValue value;

    public ValueExpression(IValue value) {
        this.value = value;
    }

    public IValue getValue() {
        return value;
    }

    public void setValue(IValue value) {
        this.value = value;
    }

    @Override
    public IValue evaluate(IDictionary<String, IValue> symbolTable, IHeapTable<Integer, IValue> heapTable)throws MyException {
        return this.value;
    }

    @Override
    public String toString() {
        return this.value.toString();
    }

    @Override
    public IExpression deepCopy() {
        return new ValueExpression(this.value.deepCopy());
    }

    @Override
    public IType typeCheck(IDictionary<String, IType> typeEnvironment) throws MyException {
        return this.value.getType();
    }


}
