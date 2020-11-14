package Model.Expressions;

import Model.ADTs.IDictionary;
import Model.Exceptions.EvaluationException;
import Model.Exceptions.MyException;
import Model.Types.IntType;
import Model.Values.BoolValue;
import Model.Values.IValue;
import Model.Values.IntValue;

public class RelationalExpressions implements IExpression{

    private IExpression expression1;
    private IExpression expression2;
    private String operator;

    public RelationalExpressions(IExpression expression1, IExpression expression2, String operator) {
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.operator = operator;
    }

    @Override
    public IValue evaluate(IDictionary<String, IValue> symbolTable) throws MyException {
        IValue iValue1 = this.expression1.evaluate(symbolTable);
        if(iValue1.getType().equals(new IntType())) {
            IValue iValue2 = this.expression2.evaluate(symbolTable);
            if(iValue2.getType().equals(new IntType())) {
                IntValue intValue1 = (IntValue) iValue1;
                IntValue intValue2 = (IntValue) iValue2;
                int realIntValue1 = intValue1.getValue();
                int realIntValue2 = intValue2.getValue();
                switch (this.operator) {
                    case "<":
                        return new BoolValue(realIntValue1 < realIntValue2);
                    case "<=":
                        return new BoolValue(realIntValue1 <= realIntValue2);
                    case "==":
                        return  new BoolValue(realIntValue1 == realIntValue2);
                    case "!=":
                        return new BoolValue(realIntValue1 != realIntValue2);
                    case ">":
                        return new BoolValue(realIntValue1 > realIntValue2);
                    case ">=":
                        return new BoolValue(realIntValue1 >= realIntValue2);
                }
            }
            else throw new EvaluationException("Second operand is not an Integer");
        }
        else throw new EvaluationException("First operand is not an Integer");

        return null;
    }

    @Override
    public IExpression deepCopy() {
        return new RelationalExpressions(this.expression1.deepCopy(), this.expression2.deepCopy(), this.operator);
    }

    @Override
    public String toString() {
        return this.expression1.toString() + this.operator + this.expression2.toString();
    }
}
