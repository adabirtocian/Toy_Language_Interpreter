package Model.Statements.Files;

import Model.ADTs.Dictionary.IDictionary;
import Model.Exceptions.*;
import Model.Expressions.IExpression;
import Model.ProgramState;
import Model.Statements.IStatement;
import Model.Types.IType;
import Model.Types.StringType;
import Model.Values.IValue;
import Model.Values.StringValue;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class OpenRFileStatement  implements IStatement {

    private final IExpression expression;

    public OpenRFileStatement(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        IDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();
        IValue expressionValue = this.expression.evaluate(state.getSymbolTabel(), state.getHeapTable());

        if( ! expressionValue.getType().equals(new StringType()))
            throw new InvalidTypeException("OpenFile: Expression not a string");

        if(fileTable.isDefined((StringValue) expressionValue))
            throw new InvalidKeyException("OpenFile: File already exists");

        String filePath = ((StringValue) expressionValue).getValue();
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader reader = new BufferedReader(fileReader);
            fileTable.add((StringValue) expressionValue, reader);

        } catch (IOException exception) {
            throw new OpenFileException("OpenFile: File " + filePath + " not found");
        }
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new OpenRFileStatement(this.expression.deepCopy());
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnvironment) throws MyException {
        IType typeExpression = this.expression.typeCheck(typeEnvironment);
        if(typeExpression.equals(new StringType())) return typeEnvironment;
        else throw new InvalidTypeException("Expression does not have string type");
    }

    public String toString() {
        return "openfile(" + this.expression.toString() +")";
    }
}
