package Model.Statements;

import Model.ADTs.IDictionary;
import Model.Exceptions.MyException;
import Model.Exceptions.StatementException;
import Model.ProgramState;
import Model.Types.IType;
import Model.Values.IValue;

public class VarDeclarationStatement implements IStatement {
    String id;
    IType type;

    public VarDeclarationStatement(String id, IType type) {
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public IType getType() {
        return type;
    }

    public void setType(IType type) {
        this.type = type;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        IDictionary<String, IValue> symbolTabel = state.getSymbolTabel();
        if(symbolTabel.isDefined(this.id)) {
            throw new StatementException("Variable already declared");
        }
        else {
            symbolTabel.add(this.id, this.type.defaultIValue());
        }

        return state;
    }

    @Override
    public IStatement deepCopy() {
        return new VarDeclarationStatement(this.id, this.type.deepCopy());
    }

    public String toString() {
        return this.type + " " + this.id;
    }
}
