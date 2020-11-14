package Model.ADTs;

import Model.Exceptions.MyException;

public interface IList<T> {
    void add(T value);
    T pop() throws MyException;
    String toString();
    int size();
    T getValue(int index);
}
