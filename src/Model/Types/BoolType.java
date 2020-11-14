package Model.Types;
import Model.Values.BoolValue;
import Model.Values.IValue;


public class BoolType implements IType {

    @Override
    public boolean equals(Object otherObject) {
        return otherObject instanceof BoolType;
    }

    @Override
    public String toString() {
        return "bool";
    }

    @Override
    public IValue defaultIValue() {
        return new BoolValue(false);
    }

    @Override
    public IType deepCopy() {
        return new BoolType();
    }


}
