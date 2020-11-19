package Model;

import Model.ADTs.IDictionary;
import Model.ADTs.IHeapTable;
import Model.ADTs.IList;
import Model.ADTs.IStack;
import Model.Statements.IStatement;
import Model.Values.IValue;
import Model.Values.StringValue;
import java.io.BufferedReader;


public class ProgramState{

    IStack<IStatement> exeStack;
    IDictionary<String, IValue> symbolTabel;
    IList<IValue> out;
    IStatement originalProgram;
    IDictionary<StringValue, BufferedReader> fileTable;
    IHeapTable<Integer, IValue> heapTable;

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
    }

    public String toString() {
        return  "================== Next step ==================\n" +
                "FileTable\n" +
                this.fileTable.toString() + "\n\n" +
                "ExeStack\n" +
                this.exeStack.toString() + "\n\n" +
                "Symbol table\n" +
                this.symbolTabel.toString() + "\n\n" +
                "Output\n" +
                this.out.toString() + "\n\n" +
                "HeapTable\n" +
                this.heapTable.toString() + "\n\n";
    }

}
