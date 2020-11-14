package Model.Values;

import Model.Types.IType;
import Model.Types.IntType;

public class IntValue implements IValue{
    int value;

    public IntValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object otherObject) {
        if(otherObject instanceof IntValue)
            return ((IntValue) otherObject).getValue() == this.value;
        return false;
    }

    @Override
    public IType getType() {
        return new IntType();
    }

    public int getValue() {
        return this.value;
    }

    public String toString() {
        return String.valueOf(this.value);
    }

    @Override
    public IValue deepCopy() {
        return new IntValue(this.value);
    }
}
