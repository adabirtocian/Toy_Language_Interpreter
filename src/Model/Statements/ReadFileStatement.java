package Model.Statements;

import Model.ADTs.IDictionary;
import Model.ADTs.IStack;
import Model.Exceptions.MyException;
import Model.Exceptions.ReadFileException;
import Model.Expressions.IExpression;
import Model.ProgramState;
import Model.Types.IntType;
import Model.Types.StringType;
import Model.Values.IValue;
import Model.Values.IntValue;
import Model.Values.StringValue;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFileStatement implements IStatement{

    private final IExpression expression;
    private final String variableName;

    public ReadFileStatement(IExpression expression, String variableName) {
        this.expression = expression;
        this.variableName = variableName;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        IDictionary<String, IValue> symbolTable = state.getSymbolTabel();

        if(symbolTable.isDefined(this.variableName)) {
            if(! symbolTable.lookup(this.variableName).getType().equals(new IntType()))
                throw new ReadFileException("ReadFile: Variable type is not int");
        } else throw new ReadFileException("ReadFile: Variable not defined");

        IValue expressionValue = this.expression.evaluate(symbolTable, state.getHeapTable());
        if(! expressionValue.getType().equals(new StringType()))
            throw new ReadFileException("ReadFile: Expression value not string");

        try {
            BufferedReader reader = state.getFileTable().lookup((StringValue)expressionValue);
            String line = reader.readLine();
            IntValue intValue;
            if(line == null) intValue = new IntValue(0);
            else intValue = new IntValue(Integer.parseInt(line));
            symbolTable.update(this.variableName, intValue);
        }
        catch (MyException exception) {
            throw new ReadFileException("ReadFile: No reader for the given key");
        }
        catch (IOException exception) {
            throw new ReadFileException("ReadFile: Could not read a value from the file");
        }

        return state;
    }

    @Override
    public IStatement deepCopy() {
        return new ReadFileStatement(this.expression.deepCopy(), this.variableName);
    }

    public String toString(){
        return "readfile(" +  this.expression.toString() + "," + this.variableName + ")";
    }
}
