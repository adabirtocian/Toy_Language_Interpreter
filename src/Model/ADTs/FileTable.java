package Model.ADTs;

import Model.Exceptions.ADTException;
import Model.Exceptions.MyException;
import java.util.HashMap;

public class FileTable<T1,T2> implements IDictionary<T1,T2> {

    private final HashMap<T1, T2> fileTable;

    public FileTable() {
        this.fileTable = new HashMap<>();
    }

    @Override
    public void add(T1 key, T2 value) throws MyException {
        if(this.fileTable.containsKey(key))
            throw new ADTException("FileTable: Duplicate key");
        this.fileTable.put(key, value);
    }

    @Override
    public void update(T1 key, T2 newValue) throws MyException {
        if( ! this.fileTable.containsKey(key))
            throw new ADTException("FileTable: Inexistent key");
        this.fileTable.replace(key, newValue);
    }

    @Override
    public void remove(T1 key) throws MyException {
        if( ! this.fileTable.containsKey(key))
            throw new ADTException("FileTable: Inexistent key");
        this.fileTable.remove(key);
    }

    @Override
    public T2 lookup(T1 key) throws MyException {
        if( ! this.fileTable.containsKey(key))
            throw new ADTException("FileTable: Inexistent key");

        return this.fileTable.get(key);
    }

    @Override
    public boolean isDefined(T1 key) {
        return this.fileTable.containsKey(key);
    }

    public String toString() {
        return fileTable.keySet().toString();
    }
}
