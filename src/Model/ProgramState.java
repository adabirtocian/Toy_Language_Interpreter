package Model;

import Model.ADTs.Dictionary.IDictionary;
import Model.ADTs.HeapTable.IHeapTable;
import Model.ADTs.List.IList;
import Model.ADTs.Stack.IStack;
import Model.Exceptions.MyException;
import Model.Statements.IStatement;
import Model.Values.IValue;
import Model.Values.StringValue;
import java.io.BufferedReader;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ProgramState{

    IStack<IStatement> exeStack;
    IDictionary<String, IValue> symbolTabel;
    IList<IValue> out;
    IStatement originalProgram;
    IDictionary<StringValue, BufferedReader> fileTable;
    IHeapTable<Integer, IValue> heapTable;
    private final int programStateId;
    static int lastUsedId = 0;

    public IStack<IStatement> getExeStack() {
        return exeStack;
    }

    public IDictionary<String, IValue> getSymbolTabel() {
        return symbolTabel;
    }

    public IList<IValue> getOut() {
        return out;
    }

    public IStatement getOriginalProgram() {
        return originalProgram;
    }

    public IDictionary<StringValue, BufferedReader> getFileTable() { return fileTable; }

    public IHeapTable<Integer, IValue> getHeapTable() {
        return this.heapTable;
    }

    public int getId() {return this.programStateId; }

    public ProgramState(IStack<IStatement> exeStack, IDictionary<String, IValue> symbolTabel, IList<IValue> out,
                        IStatement originalProgram, IDictionary<StringValue, BufferedReader> fileTable,
                        IHeapTable<Integer, IValue> heapTable) {
        this.exeStack = exeStack;
        this.symbolTabel = symbolTabel;
        this.out = out;
        this.originalProgram = originalProgram.deepCopy();
        this.fileTable = fileTable;
        this.heapTable = heapTable;
        this.exeStack.push(originalProgram);

        Lock lock = new ReentrantLock();
        lock.lock();
        this.programStateId = lastUsedId + 1;
        lastUsedId++;
        lock.unlock();
    }

    public boolean isNotCompleted() {
        return ! this.exeStack.isEmpty();
    }

    public ProgramState oneStep() throws MyException {
        if(this.exeStack.isEmpty()) throw new MyException("Program state stack is empty");
        IStatement currentStatement = this.exeStack.pop();

        return currentStatement.execute(this);
    }

    public String toString() {
        return  "================== Next step ==================\n" +
                "Id: " + programStateId + "\n\n" +
                "ExeStack\n" +
                this.exeStack.toString() + "\n\n" +
                "Symbol table\n" +
                this.symbolTabel.toString() + "\n\n" +
                "FileTable\n" +
                this.fileTable.toString() + "\n\n" +
                "HeapTable\n" +
                this.heapTable.toString() + "\n\n" +
                "Output\n" +
                this.out.toString() + "\n\n";
    }

}
