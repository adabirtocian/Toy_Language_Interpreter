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
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new AssignStatement(this.id, this.expression.deepCopy());
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnvironment) throws MyException {
        IType typeVar = typeEnvironment.lookup(this.id);
        IType typeExpression = this.expression.typeCheck(typeEnvironment);
        if(typeVar.equals(typeExpression))
            return typeEnvironment;
        else throw new InvalidTypeException("Assignment statement: right hand side and left hand side have different types");
    }

    public String toString() {
        return this.id + "=" + this.expression.toString();
    }

}
