package Model.Values;

import Model.Types.BoolType;
import Model.Types.IType;

public class BoolValue implements IValue {
    boolean value;

    public BoolValue(boolean value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object otherObject) {
        if(otherObject instanceof BoolValue)
            return ((BoolValue) otherObject).getValue() == this.value;
        return false;
    }

    @Override
    public IType getType() {
        return new BoolType();
    }

    public boolean getValue() {
        return this.value;
    }

    public String toString() {
        return String.valueOf(this.value);
    }

    @Override
    public IValue deepCopy() {
        return new BoolValue(this.value);
    }
}
