package Model.ADTs;
import Model.Exceptions.ADTException;
import Model.Exceptions.MyException;

import java.util.Stack;

public class MyStack<T> implements IStack<T> {

    private final Stack<T> myStack;

    public MyStack() {
        this.myStack = new Stack<T>();
    }

    @Override
    public void push(T v) {
        this.myStack.add(v);
    }

    @Override
    public T pop() throws MyException {
        if(this.myStack.isEmpty()) {
            throw new ADTException("Stack: Empty stack");
        }
        return this.myStack.pop();
    }

    @Override
    public boolean isEmpty() {
        return this.myStack.isEmpty();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Object[] values = this.myStack.toArray();
        for(int i= values.length - 1; i >= 0; --i) {
            stringBuilder.append(values[i]);
            stringBuilder.append(" | ");
        }
        return stringBuilder.toString();
    }

}
