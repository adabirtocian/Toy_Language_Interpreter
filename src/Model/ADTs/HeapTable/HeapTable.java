package Model.ADTs.HeapTable;

import Model.ADTs.Dictionary.Dictionary;

public class HeapTable<T1,T2> extends Dictionary<T1,T2> implements IHeapTable<T1,T2> {

    private int freeLocation = 1;

    @Override
    public int getNewFreeLocation() {
        return this.freeLocation++;
    }

}
