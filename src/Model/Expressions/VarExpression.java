package Model.Expressions;

import Model.ADTs.IDictionary;
import Model.ADTs.IHeapTable;
import Model.Exceptions.MyException;
import Model.Types.IType;
import Model.Values.IValue;

public class VarExpression implements IExpression {
    String id;

    public VarExpression(String id) {
        this.id = id;
    }

    @Override
    public IValue evaluate(IDictionary<String, IValue> symbolTable, IHeapTable<Integer, IValue> heapTable) throws MyException {
        return symbolTable.lookup(this.id);
    }

    public String toString() {
        return this.id;
    }

    @Override
    public IExpression deepCopy() {
        return new VarExpression(this.id);
    }

    @Override
    public IType typeCheck(IDictionary<String, IType> typeEnvironment) throws MyException {
        return typeEnvironment.lookup(this.id);
    }

}
