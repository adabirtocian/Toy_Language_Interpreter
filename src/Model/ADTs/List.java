package Model.ADTs;

import Model.Exceptions.InvalidKeyException;
import Model.Exceptions.MyException;

import java.util.ArrayList;

public class List<T> implements IList<T> {

    private final ArrayList<T> list;

    public List() {
        this.list = new ArrayList<>();
    }

    @Override
    public void add(T value) {
        this.list.add(value);
    }

    @Override
    public T pop() throws MyException {
        if(this.list.isEmpty())
            throw new InvalidKeyException("Empty list");
        return this.list.get(0);
    }

    public String toString() {
        return this.list.toString();
    }

    @Override
    public int size() {
        return this.list.size();
    }

    @Override
    public T getValue(int index) {
        return this.list.get(index);
    }


}
