package Model.Types;

import Model.Values.IValue;
import Model.Values.StringValue;

public class StringType implements IType{

    @Override
    public boolean equals(Object otherObject) {
        return otherObject instanceof StringType;
    }

    @Override
    public IValue defaultIValue() {
        return new StringValue("");
    }

    @Override
    public IType deepCopy() {
        return new StringType();
    }

    @Override
    public String toString() {
        return "string";
    }
}
