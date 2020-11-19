package Model.Expressions;

import Model.ADTs.IDictionary;
import Model.ADTs.IHeapTable;
import Model.Exceptions.MyException;
import Model.Values.IValue;

public interface IExpression {
    IValue evaluate(IDictionary<String, IValue> symbolTable, IHeapTable<Integer, IValue> heapTable) throws MyException;
    String toString();
    IExpression deepCopy();
}
