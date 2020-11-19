package Model.Statements;

import Model.ADTs.IDictionary;
import Model.Exceptions.MyException;
import Model.Exceptions.OpenFileException;
import Model.Exceptions.StatementException;
import Model.Expressions.IExpression;
import Model.ProgramState;
import Model.Types.StringType;
import Model.Values.IValue;
import Model.Values.StringValue;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class OpenRFileStatement  implements IStatement{

    private final IExpression expression;

    public OpenRFileStatement(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        IDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();
        IValue expressionValue = this.expression.evaluate(state.getSymbolTabel(), state.getHeapTable());

        if( ! expressionValue.getType().equals(new StringType()))
            throw new StatementException("OpenFile: Expression not a string");

        if(fileTable.isDefined((StringValue) expressionValue))
            throw new OpenFileException("OpenFile: File already exists");

        String filePath = ((StringValue) expressionValue).getValue();
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader reader = new BufferedReader(fileReader);
            fileTable.add((StringValue) expressionValue, reader);

        } catch (IOException exception) {
            throw new OpenFileException("OpenFile: File" + filePath + " not found");
        }
        return state;
    }

    @Override
    public IStatement deepCopy() {
        return new OpenRFileStatement(this.expression.deepCopy());
    }

    public String toString() {
        return "openfile(" + this.expression.toString() +")";
    }
}
