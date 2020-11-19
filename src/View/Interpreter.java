package View;

import Controller.Controller;
import Model.ADTs.*;
import Model.Expressions.*;
import Model.ProgramState;
import Model.Statements.*;
import Model.Types.IntType;
import Model.Types.ReferenceType;
import Model.Types.StringType;
import Model.Values.IValue;
import Model.Values.IntValue;
import Model.Values.StringValue;
import Repository.IRepository;
import Repository.Repository;

import java.io.BufferedReader;

public class Interpreter {
    public static void main(String[] args) {

        IStack<IStatement> exeStack1 = new MyStack<IStatement>();
        IDictionary<String, IValue> symbolTable1 = new Dictionary<String, IValue>();
        IDictionary<StringValue, BufferedReader> fileTable1 = new FileTable<>();
        IList<IValue> out1 = new List<IValue>();
        IHeapTable<Integer, IValue> heapTable1 = new HeapTable<>();

        // string varf; varf = "test.txt"; open(varf); int varc; readRFile(varf, varc); print(varc); readRFile(varf, varc); print(varc); close(varf)
        IStatement program1 = new CompStatement(
                                 new VarDeclarationStatement("varf", new StringType()),
                                 new CompStatement(
                                     new AssignStatement("varf", new ValueExpression(new StringValue("test.txt"))),
                                     new CompStatement(
                                         new OpenRFileStatement(new VarExpression("varf")),
                                         new CompStatement(
                                             new VarDeclarationStatement("varc", new IntType()),
                                             new CompStatement(
                                                 new ReadFileStatement(new VarExpression("varf"), "varc"),
                                                 new CompStatement(
                                                     new PrintStatement(new VarExpression("varc")),
                                                     new CompStatement(
                                                         new ReadFileStatement(new VarExpression("varf"), "varc"),
                                                         new CompStatement(
                                                             new PrintStatement(new VarExpression("varc")),
                                                             new CloseRFileStatement(new VarExpression("varf"))))))))));
        ProgramState programState1 = new ProgramState(exeStack1, symbolTable1, out1, program1, fileTable1, heapTable1);
        IRepository repository1 = new Repository("log1.txt");
        Controller controller1 = new Controller(repository1);
        controller1.addProgram(programState1);

        IStack<IStatement> exeStack2 = new MyStack<IStatement>();
        IDictionary<String, IValue> symbolTable2 = new Dictionary<String, IValue>();
        IDictionary<StringValue, BufferedReader> fileTable2 = new FileTable<>();
        IList<IValue> out2 = new List<IValue>();
        IHeapTable<Integer, IValue> heapTable2 = new HeapTable<>();

        // string varf; varf=test.txt; openfile(varf); int varc; int a; readfile(varf,varc); print(varc); readfile(varf,a); if (a>varc) then (print(a) else print(varc); closefile(varf)
        IStatement program2 = new CompStatement(
                new VarDeclarationStatement("varf", new StringType()),
                new CompStatement(
                        new AssignStatement("varf", new ValueExpression(new StringValue("test.txt"))),
                        new CompStatement(
                                new OpenRFileStatement(new VarExpression("varf")),
                                new CompStatement(
                                        new VarDeclarationStatement("varc", new IntType()),
                                        new CompStatement(
                                                new VarDeclarationStatement("a", new IntType()),
                                                new CompStatement(
                                                    new ReadFileStatement(new VarExpression("varf"), "varc"),
                                                    new CompStatement(
                                                            new PrintStatement(new VarExpression("varc")),
                                                            new CompStatement(
                                                                    new ReadFileStatement(new VarExpression("varf"), "a"),
                                                                    new CompStatement(
                                                                            new IfStatement(
                                                                                    new RelationalExpression(
                                                                                            new VarExpression("a"),
                                                                                            new VarExpression("varc"),
                                                                                            ">"),
                                                                                    new PrintStatement(new VarExpression("a")),
                                                                                    new PrintStatement(new VarExpression("varc"))),
                                                                            new CloseRFileStatement(new VarExpression("varf")))))))))));

        ProgramState programState2 = new ProgramState(exeStack2, symbolTable2, out2, program2, fileTable2, heapTable2);
        IRepository repository2 = new Repository("log2.txt");
        Controller controller2 = new Controller(repository2);
        controller2.addProgram(programState2);

        IStack<IStatement> exeStack3 = new MyStack<IStatement>();
        IDictionary<String, IValue> symbolTable3 = new Dictionary<String, IValue>();
        IDictionary<StringValue, BufferedReader> fileTable3 = new FileTable<>();
        IList<IValue> out3 = new List<IValue>();
        IHeapTable<Integer, IValue> heapTable3 = new HeapTable<>();

        // string varf; varf=test.txt; openfile(varf); int a; readfile(varf,a); if (10>a) then print(a less than 10) else print(a greater than 10); closefile(varf)
        IStatement program3 = new CompStatement(
                new VarDeclarationStatement("varf", new StringType()),
                new CompStatement(
                        new AssignStatement("varf", new ValueExpression(new StringValue("test.txt"))),
                        new CompStatement(
                                new OpenRFileStatement(new VarExpression("varf")),
                                new CompStatement(
                                        new VarDeclarationStatement("a", new IntType()),
                                        new CompStatement(
                                                new ReadFileStatement(new VarExpression("varf"), "a"),
                                                new CompStatement(
                                                        new IfStatement(
                                                                new RelationalExpression(
                                                                        new ValueExpression(new IntValue(10)),
                                                                        new VarExpression("a"),
                                                                        ">"),
                                                                new PrintStatement(new ValueExpression(new StringValue("a less than 10"))),
                                                                new PrintStatement(new ValueExpression(new StringValue("a greater than 10")))),
                                                        new CloseRFileStatement(new VarExpression("varf"))))))));

        ProgramState programState3 = new ProgramState(exeStack3, symbolTable3, out3, program3, fileTable3, heapTable3);
        IRepository repository3 = new Repository("log3.txt");
        Controller controller3 = new Controller(repository3);
        controller3.addProgram(programState3);


        // int a; int b; string file; file="test.txt"; open(file); readRFile(file, a), readRFile(file, b); a=a*2; b=a+1; print(a); print(b); close(file)
        IStack<IStatement> exeStack4 = new MyStack<IStatement>();
        IDictionary<String, IValue> symbolTable4 = new Dictionary<String, IValue>();
        IDictionary<StringValue, BufferedReader> fileTable4 = new FileTable<>();
        IList<IValue> out4 = new List<IValue>();
        IHeapTable<Integer, IValue> heapTable4 = new HeapTable<>();

        IStatement program4 = new CompStatement(
                                new VarDeclarationStatement("a", new IntType()),
                                new CompStatement(
                                        new VarDeclarationStatement("b", new IntType()),
                                        new CompStatement(
                                                new VarDeclarationStatement("file", new StringType()),
                                                new CompStatement(
                                                        new AssignStatement("file", new ValueExpression(new StringValue("test.txt"))),
                                                        new CompStatement(
                                                                new OpenRFileStatement(new VarExpression("file")),
                                                                new CompStatement(
                                                                        new ReadFileStatement(new VarExpression("file"), "a"),
                                                                        new CompStatement(
                                                                                new ReadFileStatement(new VarExpression("file"), "b"),
                                                                                new CompStatement(
                                                                                        new AssignStatement("a",
                                                                                                new ArithmeticExpression(
                                                                                                        new VarExpression("a"),
                                                                                                        new ValueExpression(new IntValue(2)),
                                                                                                        '*')),
                                                                                        new CompStatement(
                                                                                                new AssignStatement("b",
                                                                                                        new ArithmeticExpression(
                                                                                                                new ValueExpression(new IntValue(1)),
                                                                                                                new VarExpression("a"),
                                                                                                                '+')),
                                                                                                new CompStatement(
                                                                                                        new PrintStatement(new VarExpression("a")),
                                                                                                        new CompStatement(
                                                                                                                new PrintStatement(new VarExpression("b")),
                                                                                                                new CloseRFileStatement(new VarExpression("file")))))))))))));

        ProgramState programState4 = new ProgramState(exeStack4, symbolTable4, out4, program4, fileTable4, heapTable4);
        IRepository repository4 = new Repository("log4.txt");
        Controller controller4 = new Controller(repository4);
        controller4.addProgram(programState4);


        // Ref int v;new(v,20);print(rH(v)); wH(v,30);print(rH(v)+5);
        IStack<IStatement> exeStack5 = new MyStack<>();
        IDictionary<String, IValue> symbolTable5 = new Dictionary<>();
        IDictionary<StringValue, BufferedReader> fileTable5 = new FileTable<>();
        IList<IValue> out5 = new List<>();
        IHeapTable<Integer, IValue> heapTable5 = new HeapTable<>();

        IStatement program5 = new CompStatement(
                new VarDeclarationStatement("v", new ReferenceType(new IntType())),
                new CompStatement(
                        new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
                        new CompStatement(
                                new PrintStatement(new HeapReadingExpression(new VarExpression("v"))),
                                new CompStatement(
                                        new HeapWritingStatement("v", new ValueExpression(new IntValue(30))),
                                        new PrintStatement(
                                                new ArithmeticExpression(
                                                    new HeapReadingExpression(new VarExpression("v")),
                                                    new ValueExpression(new IntValue(5)),
                                                    '+')


                                        )
                                )
                        )
                ));

        ProgramState programState5 = new ProgramState(exeStack5, symbolTable5, out5, program5, fileTable5, heapTable5);
        IRepository repository5 = new Repository("log5.txt");
        Controller controller5 = new Controller(repository5);
        controller5.addProgram(programState5);


        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExampleCommand("1", program1.toString(), controller1));
        menu.addCommand(new RunExampleCommand("2", program2.toString(), controller2));
        menu.addCommand(new RunExampleCommand("3", program3.toString(), controller3));
        menu.addCommand(new RunExampleCommand("4", program4.toString(), controller4));
        menu.addCommand(new RunExampleCommand("5", program5.toString(), controller5));
        menu.show();
    }
}
