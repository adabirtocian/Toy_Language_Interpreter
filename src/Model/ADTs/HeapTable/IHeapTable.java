package Model.ADTs.HeapTable;

import Model.ADTs.Dictionary.IDictionary;

public interface IHeapTable<T1,T2> extends IDictionary<T1, T2> {

    int getNewFreeLocation();
}
