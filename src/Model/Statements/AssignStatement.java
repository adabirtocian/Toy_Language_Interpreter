package Model.Statements;

import Model.ADTs.IDictionary;
import Model.ADTs.IStack;
import Model.Exceptions.*;
import Model.Expressions.IExpression;
import Model.ProgramState;
import Model.Types.IType;
import Model.Values.IValue;

public class AssignStatement implements IStatement {
    String id;
    IExpression expression;

    public AssignStatement(String id, IExpression expression) {
        this.id = id;
        this.expression = expression;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public IExpression getExpression() {
        return expression;
    }

    public void setExpression(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        IDictionary<String, IValue> symbolTable = state.getSymbolTabel();

        if(symbolTable.isDefined(this.id)) {
            IValue value = this.expression.evaluate(symbolTable, state.getHeapTable());
            IType typeId = (symbolTable.lookup(this.id)).getType();
            if(value.getType().equals(typeId)) {
                symbolTable.update(this.id, value);
            }
            else {
                throw new InvalidTypeException("Assignment statement: Mismatched types");
            }
        }
        else {
            throw new UndeclaredVariableException("Assignment statement: Variable " + this.id + " was not yet declared");
        }
        return state;
    }

    @Override
    public IStatement deepCopy() {
        return new AssignStatement(this.id, this.expression.deepCopy());
    }

    public String toString() {
        return this.id + "=" + this.expression.toString();
    }

}
