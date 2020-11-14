package Model.Values;

import Model.Types.IType;


public interface IValue {
    boolean equals(Object otherObject);
    IType getType();
    String toString();
    IValue deepCopy();
}
