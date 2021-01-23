package Model.ADTs.Stack;

import Model.Exceptions.MyException;

import java.util.Stack;

public interface IStack<T> {
    void push(T v);
    T pop() throws MyException;
    boolean isEmpty();
    String toString();
    T peek() throws MyException;
    Stack<T> getContent();
}
