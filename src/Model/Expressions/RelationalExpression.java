package Model.Expressions;

import Model.ADTs.Dictionary.IDictionary;
import Model.ADTs.HeapTable.IHeapTable;
import Model.Exceptions.InvalidOperatorException;
import Model.Exceptions.InvalidTypeException;
import Model.Exceptions.MyException;
import Model.Types.BoolType;
import Model.Types.IType;
import Model.Types.IntType;
import Model.Values.BoolValue;
import Model.Values.IValue;
import Model.Values.IntValue;

public class RelationalExpression implements IExpression{

    private IExpression expression1;
    private IExpression expression2;
    private String operator;

    public RelationalExpression(IExpression expression1, IExpression expression2, String operator) {
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.operator = operator;
    }

    @Override
    public IValue evaluate(IDictionary<String, IValue> symbolTable, IHeapTable<Integer, IValue> heapTable) throws MyException {
        IValue iValue1 = this.expression1.evaluate(symbolTable, heapTable);
        if(iValue1.getType().equals(new IntType())) {
            IValue iValue2 = this.expression2.evaluate(symbolTable, heapTable);
            if(iValue2.getType().equals(new IntType())) {
                IntValue intValue1 = (IntValue) iValue1;
                IntValue intValue2 = (IntValue) iValue2;
                int realIntValue1 = intValue1.getValue();
                int realIntValue2 = intValue2.getValue();
                return switch (this.operator) {
                    case "<" -> new BoolValue(realIntValue1 < realIntValue2);
                    case "<=" -> new BoolValue(realIntValue1 <= realIntValue2);
                    case "==" -> new BoolValue(realIntValue1 == realIntValue2);
                    case "!=" -> new BoolValue(realIntValue1 != realIntValue2);
                    case ">" -> new BoolValue(realIntValue1 > realIntValue2);
                    case ">=" -> new BoolValue(realIntValue1 >= realIntValue2);
                    default -> throw new InvalidOperatorException("Relational expression: Invalid relational operator");
                };
            }
            else throw new InvalidTypeException("Second operand is not an Integer");
        }
        else throw new InvalidTypeException("First operand is not an Integer");
    }

    @Override
    public IExpression deepCopy() {
        return new RelationalExpression(this.expression1.deepCopy(), this.expression2.deepCopy(), this.operator);
    }

    @Override
    public IType typeCheck(IDictionary<String, IType> typeEnvironment) throws MyException {
        IType type1, type2;
        type1 = this.expression1.typeCheck(typeEnvironment);
        type2 = this.expression2.typeCheck(typeEnvironment);

        if(type1.equals(new IntType())) {
            if(type2.equals(new IntType()))
                return new BoolType();
            else throw new InvalidTypeException("Second operator not an integer");
        }
        else throw new InvalidTypeException("First operator not an integer");
    }

    @Override
    public String toString() {
        return this.expression1.toString() + this.operator + this.expression2.toString();
    }
}
