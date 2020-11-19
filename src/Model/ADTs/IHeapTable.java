package Model.ADTs;

public interface IHeapTable<T1,T2> extends IDictionary<T1, T2> {

    int getNewFreeLocation();
}
