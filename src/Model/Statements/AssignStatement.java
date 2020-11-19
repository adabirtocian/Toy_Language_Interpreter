package Model.Statements;

import Model.ADTs.IDictionary;
import Model.ADTs.IStack;
import Model.Exceptions.MyException;
import Model.Exceptions.StatementException;
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
        IStack<IStatement> exeStack = state.getExeStack();
        IDictionary<String, IValue> symbolTable = state.getSymbolTabel();

        if(symbolTable.isDefined(this.id)) {
            IValue value = this.expression.evaluate(symbolTable, state.getHeapTable());
            IType typeId = (IType) (symbolTable.lookup(this.id)).getType();
            if(value.getType().equals(typeId)) {
                symbolTable.update(this.id, value);
            } else {
                throw new StatementException("Declared type of " + this.id + " does not match the type of the assigned expression\n");
            }
        } else {
            throw new StatementException("Variable " + this.id + " was not yet declared\n");
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
