package Model.Statements;

import Model.ADTs.IDictionary;
import Model.ADTs.IStack;
import Model.Exceptions.*;
import Model.Expressions.IExpression;
import Model.ProgramState;
import Model.Types.StringType;
import Model.Values.IValue;
import Model.Values.StringValue;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFileStatement implements IStatement {

    IExpression expression;

    public CloseRFileStatement(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        IDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();

        IValue expressionValue = this.expression.evaluate(state.getSymbolTabel(), state.getHeapTable());
        if(! expressionValue.getType().equals(new StringType()))
            throw new InvalidTypeException("CloseFile: Expression value not string");

        try {
            BufferedReader reader = state.getFileTable().lookup((StringValue)expressionValue);
            reader.close();
            fileTable.remove((StringValue)expressionValue);
        }
        catch (MyException exception) {
            throw new InvalidKeyException("CloseFile: No reader for the given key");
        }
        catch (IOException exception) {
            throw new CloseFileException("CloseFile: Could not close the file");
        }
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new CloseRFileStatement(this.expression.deepCopy());
    }

    public String toString() {
        return "closefile(" + this.expression.toString() + ")";
    }
}
