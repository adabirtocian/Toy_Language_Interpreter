package Model.ADTs;

import Model.Exceptions.MyException;

public interface IDictionary<T1,T2> {

    void add(T1 key, T2 value) throws MyException;
    void update(T1 key, T2 newValue) throws MyException;
    void remove(T1 key) throws MyException;
    T2 lookup(T1 key) throws MyException;
    String toString();
    boolean isDefined(T1 key);
}
