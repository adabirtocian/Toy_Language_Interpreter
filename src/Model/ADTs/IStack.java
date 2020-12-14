package Model.ADTs;

import Model.Exceptions.MyException;

public interface IStack<T> {
    void push(T v);
    T pop() throws MyException;
    boolean isEmpty();
    String toString();
    T peek() throws MyException;
}
