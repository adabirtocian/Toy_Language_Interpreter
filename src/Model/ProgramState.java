package Model;

import Model.ADTs.IDictionary;
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

    public IStack<IStatement> getExeStack() {
        return exeStack;
    }

    public void setExeStack(IStack<IStatement> exeStack) {
        this.exeStack = exeStack;
    }

    public IDictionary<String, IValue> getSymbolTabel() {
        return symbolTabel;
    }

    public void setSymbolTabel(IDictionary<String, IValue> symbolTabel) {
        this.symbolTabel = symbolTabel;
    }

    public IList<IValue> getOut() {
        return out;
    }

    public void setOut(IList<IValue> out) {
        this.out = out;
    }

    public IStatement getOriginalProgram() {
        return originalProgram;
    }

    public void setOriginalProgram(IStatement originalProgram) {
        this.originalProgram = originalProgram;
    }

    public IDictionary<StringValue, BufferedReader> getFileTable() { return fileTable; }

    public void setFileTable(IDictionary<StringValue, BufferedReader> fileTable) { this.fileTable = fileTable; }

    public ProgramState(IStack<IStatement> exeStack, IDictionary<String, IValue> symbolTabel, IList<IValue> out,
                        IStatement originalProgram, IDictionary<StringValue, BufferedReader> fileTable) {
        this.exeStack = exeStack;
        this.symbolTabel = symbolTabel;
        this.out = out;
        this.originalProgram = originalProgram.deepCopy();
        this.fileTable = fileTable;
        this.exeStack.push(originalProgram);
    }

    public String toString() {
        return  "================== Next step ==================\n" +
                "FileTable\n" +
                this.fileTable.toString() + "\n\n" +
                "ExeStack\n" +
                this.exeStack.toString() + "\n\n" +
                "Symbol table\n{" +
                this.symbolTabel.toString() + "}\n\n" +
                "Output\n[" +
                this.out.toString() + "]\n\n";
    }

}
