package Model.Types;

import Model.Values.IValue;
import Model.Values.ReferenceValue;

public class ReferenceType implements IType{

    private IType inner;

    public ReferenceType(IType inner) {
        this.inner = inner;
    }

    public IType getInner() {
        return this.inner;
    }

    @Override
    public boolean equals(Object another) {
        if(another instanceof ReferenceType)
            return ((ReferenceType) another).getInner().equals(this.inner);
        return false;
    }

    @Override
    public String toString() {
        return "ref(" + this.inner.toString() + ")";
    }

    @Override
    public IValue defaultIValue() {
        return new ReferenceValue(0, this.inner);
    }

    @Override
    public IType deepCopy() {
        return new ReferenceType(this.inner.deepCopy());
    }
}
