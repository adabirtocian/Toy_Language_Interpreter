package Model.Types;

import Model.Values.IValue;

public interface IType {
    boolean equals(Object otherObject);
    String toString();
    IValue defaultIValue();
    IType deepCopy();
}
