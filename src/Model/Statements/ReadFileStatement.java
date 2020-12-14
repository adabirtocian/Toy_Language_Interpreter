package Model.Statements;

import Model.ADTs.IDictionary;
import Model.Exceptions.*;
import Model.Expressions.IExpression;
import Model.ProgramState;
import Model.Types.IType;
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
                throw new InvalidTypeException("ReadFile: Variable type is not int");
        } else throw new UndeclaredVariableException("ReadFile: Variable not defined");

        IValue expressionValue = this.expression.evaluate(symbolTable, state.getHeapTable());
        if(! expressionValue.getType().equals(new StringType()))
            throw new InvalidTypeException("ReadFile: Expression value not string");

        try {
            BufferedReader reader = state.getFileTable().lookup((StringValue)expressionValue);
            String line = reader.readLine();
            IntValue intValue;
            if(line == null) intValue = new IntValue(0);
            else intValue = new IntValue(Integer.parseInt(line));
            symbolTable.update(this.variableName, intValue);
        }
        catch (MyException exception) {
            throw new InvalidKeyException("ReadFile: No reader for the given key");
        }
        catch (IOException exception) {
            throw new ReadFileException("ReadFile: Could not read a value from the file");
        }

        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new ReadFileStatement(this.expression.deepCopy(), this.variableName);
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnvironment) throws MyException {
        IType typeVar = typeEnvironment.lookup(this.variableName);
        IType typeExpression = this.expression.typeCheck(typeEnvironment);
        if(typeExpression.equals(new StringType())) {
            if(typeVar.equals(new IntType()))
                return typeEnvironment;
            else throw new InvalidTypeException("Variable requires int type");
        }
        else throw new InvalidTypeException("Expression does not have string type");
    }

    public String toString(){
        return "readfile(" +  this.expression.toString() + "," + this.variableName + ")";
    }
}
