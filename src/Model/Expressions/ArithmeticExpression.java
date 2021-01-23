package Model.Expressions;

import Model.ADTs.Dictionary.IDictionary;
import Model.ADTs.IHeapTable;
import Model.Exceptions.DivisionByZeroException;
import Model.Exceptions.InvalidTypeException;
import Model.Exceptions.MyException;
import Model.Types.IType;
import Model.Types.IntType;
import Model.Values.IValue;
import Model.Values.IntValue;

public class ArithmeticExpression implements IExpression{
    IExpression expression1;
    IExpression expression2;
    char operation;

    public ArithmeticExpression(IExpression expression1, IExpression expression2, char operation) {
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.operation = operation;
    }

    public IExpression getExpression1() {
        return expression1;
    }

    public void setExpression1(IExpression expression1) {
        this.expression1 = expression1;
    }

    public IExpression getExpression2() {
        return expression2;
    }

    public void setExpression2(IExpression expression2) {
        this.expression2 = expression2;
    }

    public char getOperation() {
        return operation;
    }

    public void setOperation(char operation) {
        this.operation = operation;
    }

    @Override
    public IValue evaluate(IDictionary<String, IValue> symbolTable, IHeapTable<Integer, IValue> heapTable) throws MyException {
        IValue iValue1, iValue2;
        iValue1 = this.expression1.evaluate(symbolTable, heapTable);
        if(iValue1.getType().equals(new IntType())) {
            iValue2 = this.expression2.evaluate(symbolTable, heapTable);
            if(iValue2.getType().equals(new IntType())) {
                IntValue intValue1 = (IntValue)iValue1;
                IntValue intValue2 = (IntValue)iValue2;
                int realIntValue1,realIntValue2;
                realIntValue1 = intValue1.getValue();
                realIntValue2 = intValue2.getValue();
                if(this.operation == '+') return new IntValue(realIntValue1+realIntValue2);
                if(this.operation == '-') return new IntValue(realIntValue1-realIntValue2);
                if(this.operation == '*') return new IntValue(realIntValue1*realIntValue2);
                if(this.operation == '/') {
                    if (realIntValue2 == 0) throw new DivisionByZeroException("Division by zero");
                    else return new IntValue(realIntValue1 / realIntValue2);
                }
            }
            else throw new InvalidTypeException("Second operand is not an Integer");
        }
        else throw new InvalidTypeException("First operand is not an Integer");

        return null;
    }

    @Override
    public String toString() {
        return this.expression1.toString() + this.operation + this.expression2.toString();
    }

    @Override
    public IExpression deepCopy() {
        IExpression expression1 = this.expression1.deepCopy();
        IExpression expression2 = this.expression2.deepCopy();
        return new ArithmeticExpression(expression1, expression2, this.operation);
    }

    @Override
    public IType typeCheck(IDictionary<String, IType> typeEnvironment) throws MyException {
        IType type1, type2;
        type1 = this.expression1.typeCheck(typeEnvironment);
        type2 = this.expression2.typeCheck(typeEnvironment);

        if(type1.equals(new IntType())) {
            if(type2.equals(new IntType()))
                return new IntType();
            else throw new InvalidTypeException("Second operator not an integer");
        }
        else throw new InvalidTypeException("First operator not an integer");
    }

}
