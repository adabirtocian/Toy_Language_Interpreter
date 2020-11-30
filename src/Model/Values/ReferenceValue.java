package Model.Values;

import Model.Types.IType;
import Model.Types.ReferenceType;


public class ReferenceValue implements IValue {

    private int heapAddress;
    private IType locationType;

    public ReferenceValue(int heapAddress, IType locationType) {
        this.heapAddress = heapAddress;
        this.locationType = locationType;
    }

    @Override
    public boolean equals(Object another) {
        if(another instanceof ReferenceValue)
            return ((ReferenceValue)another).getHeapAddress() == this.heapAddress && ((ReferenceValue)another).getLocationType().equals(this.locationType);
        return false;
    }

    @Override
    public String toString() {
        return "(" + this.heapAddress + "," + this.locationType.toString() + ")";
    }

    @Override
    public IType getType() {
        return new ReferenceType(this.locationType);
    }

    @Override
    public IValue deepCopy() {
        return new ReferenceValue(this.heapAddress, this.locationType.deepCopy());
    }

    public int getHeapAddress(){
        return this.heapAddress;
    }

    public IType getLocationType() {
        return this.locationType;
    }
}
