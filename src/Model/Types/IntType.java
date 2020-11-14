package Model.Types;

import Model.Values.IValue;
import Model.Values.IntValue;

public class IntType implements IType {

    @Override
    public boolean equals(Object otherObject) {
        return otherObject instanceof IntType;
    }

    @Override
    public String toString() {
        return "int";
    }

    @Override
    public IValue defaultIValue() {
        return new IntValue(0);
    }

    @Override
    public IType deepCopy() {
        return new IntType();
    }


}
