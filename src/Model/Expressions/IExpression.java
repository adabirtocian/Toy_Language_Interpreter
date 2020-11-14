package Model.Expressions;

import Model.ADTs.IDictionary;
import Model.Exceptions.MyException;
import Model.Values.IValue;

public interface IExpression {
    IValue evaluate(IDictionary<String, IValue> symbolTable) throws MyException;
    String toString();
    IExpression deepCopy();
}
