package Model.Values;

import Model.Types.IType;
import Model.Types.StringType;

public class StringValue implements IValue {

    private final String value;

    public StringValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object otherObject) {
        if(otherObject instanceof StringValue)
            return ((StringValue) otherObject).getValue().equals(this.value);
        return false;
    }

    @Override
    public IType getType() {
        return new StringType();
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public IValue deepCopy() {
        return new StringValue(this.value);
    }
}


