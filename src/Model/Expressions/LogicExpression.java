package Model.Expressions;

import Model.ADTs.Dictionary.IDictionary;
import Model.ADTs.HeapTable.IHeapTable;
import Model.Exceptions.InvalidOperatorException;
import Model.Exceptions.InvalidTypeException;
import Model.Exceptions.MyException;
import Model.Types.BoolType;
import Model.Types.IType;
import Model.Values.BoolValue;
import Model.Values.IValue;

public class LogicExpression implements IExpression {
    IExpression expression1;
    IExpression expression2;
    String operator;

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

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public LogicExpression(IExpression expression1, IExpression expression2, String operator) {
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.operator = operator;
    }

    @Override
    public IValue evaluate(IDictionary<String, IValue> symbolTable, IHeapTable<Integer, IValue> heapTable) throws MyException {
        IValue iValue1, iValue2;
        iValue1 = this.expression1.evaluate(symbolTable, heapTable);
        if(iValue1.getType().equals(new BoolType())) {
            iValue2 = this.expression2.evaluate(symbolTable, heapTable);
            if(iValue2.getType().equals(new BoolType())) {

                BoolValue boolValue1 = (BoolValue) iValue1;
                BoolValue boolValue2 = (BoolValue) iValue2;
                boolean realBoolValue1, realBoolValue2;
                realBoolValue1 = boolValue1.getValue();
                realBoolValue2 = boolValue2.getValue();

                if(this.operator.equalsIgnoreCase("and")) {
                    return new BoolValue(realBoolValue1 && realBoolValue2);
                }
                else if(this.operator.equalsIgnoreCase("or")) {
                    return new BoolValue(realBoolValue1 || realBoolValue2);
                }
                else throw new InvalidOperatorException("Logic expression: Invalid logic operator");
            }
            else throw new InvalidTypeException("Logic expression: Operand 2 is not a boolean");
        }
        else throw new InvalidTypeException("Logic expression: Operand 1 is not a boolean");
    }

    @Override
    public String toString() {
        return this.expression1.toString() + " " + this.operator + " " + this.expression2.toString();
    }

    @Override
    public IExpression deepCopy() {
        return new LogicExpression(this.expression1.deepCopy(), this.expression2.deepCopy(), this.operator);
    }

    @Override
    public IType typeCheck(IDictionary<String, IType> typeEnvironment) throws MyException {
        IType type1, type2;
        type1 = this.expression1.typeCheck(typeEnvironment);
        type2 = this.expression2.typeCheck(typeEnvironment);

        if(type1.equals(new BoolType())) {
            if(type2.equals(new BoolType()))
                return new BoolType();
            else throw new InvalidTypeException("Second operator not a boolean");
        }
        else throw new InvalidTypeException("First operator not a boolean");
    }

}
