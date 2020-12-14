package Model.Expressions;

import Model.ADTs.IDictionary;
import Model.ADTs.IHeapTable;
import Model.Exceptions.MyException;
import Model.Types.IType;
import Model.Values.IValue;

public interface IExpression {
    IValue evaluate(IDictionary<String, IValue> symbolTable, IHeapTable<Integer, IValue> heapTable) throws MyException;
    String toString();
    IExpression deepCopy();
    IType typeCheck(IDictionary<String, IType> typeEnvironment) throws MyException;
}
