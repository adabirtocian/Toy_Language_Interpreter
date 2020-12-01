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

        IStack<IStatement> exeStack1 = new MyStack<>();
        IDictionary<String, IValue> symbolTable1 = new Dictionary<>();
        IDictionary<StringValue, BufferedReader> fileTable1 = new FileTable<>();
        IList<IValue> out1 = new List<>();
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

        IStack<IStatement> exeStack2 = new MyStack<>();
        IDictionary<String, IValue> symbolTable2 = new Dictionary<>();
        IDictionary<StringValue, BufferedReader> fileTable2 = new FileTable<>();
        IList<IValue> out2 = new List<>();
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

        IStack<IStatement> exeStack3 = new MyStack<>();
        IDictionary<String, IValue> symbolTable3 = new Dictionary<>();
        IDictionary<StringValue, BufferedReader> fileTable3 = new FileTable<>();
        IList<IValue> out3 = new List<>();
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
        IStack<IStatement> exeStack4 = new MyStack<>();
        IDictionary<String, IValue> symbolTable4 = new Dictionary<>();
        IDictionary<StringValue, BufferedReader> fileTable4 = new FileTable<>();
        IList<IValue> out4 = new List<>();
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

        //Ref int v;new(v,20);Ref Ref int a; new(a,v); writeheap(v,30);print(rH(rH(a)))
        IStack<IStatement> exeStack6 = new MyStack<>();
        IDictionary<String, IValue> symbolTable6 = new Dictionary<>();
        IDictionary<StringValue, BufferedReader> fileTable6 = new FileTable<>();
        IList<IValue> out6 = new List<>();
        IHeapTable<Integer, IValue> heapTable6 = new HeapTable<>();

        IStatement program6 = new CompStatement(
                new VarDeclarationStatement("v", new ReferenceType(new IntType())),
                new CompStatement(
                        new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
                        new CompStatement(
                                new VarDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                                new CompStatement(
                                        new HeapAllocationStatement("a", new VarExpression("v")),
                                        new CompStatement(
                                            new HeapWritingStatement("v", new ValueExpression(new IntValue(30))),
                                                new PrintStatement(new HeapReadingExpression(
                                                        new HeapReadingExpression(new VarExpression("a")))))))));

        ProgramState programState6 = new ProgramState(exeStack6, symbolTable6, out6, program6, fileTable6, heapTable6);
        IRepository repository6 = new Repository("log6.txt");
        Controller controller6 = new Controller(repository6);
        controller6.addProgram(programState6);

        //int v; v=4; (while (v>0) print(v);v=v-1);print(v)
        IStack<IStatement> exeStack7 = new MyStack<>();
        IDictionary<String, IValue> symbolTable7 = new Dictionary<>();
        IDictionary<StringValue, BufferedReader> fileTable7 = new FileTable<>();
        IList<IValue> out7 = new List<>();
        IHeapTable<Integer, IValue> heapTable7 = new HeapTable<>();

        IStatement program7 = new CompStatement(
                new VarDeclarationStatement("v", new IntType()),
                new CompStatement(
                        new AssignStatement("v", new ValueExpression(new IntValue(4))),
                        new CompStatement(
                                new WhileStatement(
                                        new RelationalExpression(new VarExpression("v"), new ValueExpression(new IntValue(0)), ">"),
                                        new CompStatement(
                                                new PrintStatement(new VarExpression("v")),
                                                new AssignStatement("v",
                                                        new ArithmeticExpression(new VarExpression("v"), new ValueExpression(new IntValue(1)), '-'))
                                        )
                                ),
                                new PrintStatement(new VarExpression("v"))
                        )
                )

        );
        ProgramState programState7 = new ProgramState(exeStack7, symbolTable7, out7, program7, fileTable7, heapTable7);
        IRepository repository7 = new Repository("log7.txt");
        Controller controller7 = new Controller(repository7);
        controller7.addProgram(programState7);


        // int v; Ref int a; v=10;new(a,22); fork(wH(a,30);v=32;print(v);print(rH(a))); print(v);print(rH(a))
        IStack<IStatement> exeStack8 = new MyStack<>();
        IDictionary<String, IValue> symbolTable8 = new Dictionary<>();
        IDictionary<StringValue, BufferedReader> fileTable8 = new FileTable<>();
        IList<IValue> out8 = new List<>();
        IHeapTable<Integer, IValue> heapTable8 = new HeapTable<>();

        IStatement program8  = new CompStatement(
                new VarDeclarationStatement("v", new IntType()),
                new CompStatement(
                        new VarDeclarationStatement("a", new ReferenceType(new IntType())),
                        new CompStatement(
                                new AssignStatement("v", new ValueExpression(new IntValue(10))),
                                new CompStatement(
                                        new HeapAllocationStatement("a", new ValueExpression(new IntValue(22))),
                                        new CompStatement(
                                                new ForkStatement(
                                                        new CompStatement(
                                                                new HeapWritingStatement("a", new ValueExpression(new IntValue(30))),
                                                                new CompStatement(
                                                                        new AssignStatement("v", new ValueExpression(new IntValue(32))),
                                                                        new CompStatement(
                                                                                new PrintStatement(new VarExpression("v")),
                                                                                new PrintStatement(new HeapReadingExpression(new VarExpression("a")))
                                                                        )
                                                                )
                                                        )
                                                ),
                                                new CompStatement(
                                                        new PrintStatement(new VarExpression("v")),
                                                        new PrintStatement(new HeapReadingExpression(new VarExpression("a")))
                                                )
                                        )
                                )
                        )));
        ProgramState programState8 = new ProgramState(exeStack8, symbolTable8, out8, program8, fileTable8, heapTable8);
        IRepository repository8 = new Repository("log8.txt");
        Controller controller8 = new Controller(repository8);
        controller8.addProgram(programState8);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExampleCommand("1", program1.toString(), controller1));
        menu.addCommand(new RunExampleCommand("2", program2.toString(), controller2));
        menu.addCommand(new RunExampleCommand("3", program3.toString(), controller3));
        menu.addCommand(new RunExampleCommand("4", program4.toString(), controller4));
        menu.addCommand(new RunExampleCommand("5", program5.toString(), controller5));
        menu.addCommand(new RunExampleCommand("6", program6.toString(), controller6));
        menu.addCommand(new RunExampleCommand("7", program7.toString(), controller7));
        menu.addCommand(new RunExampleCommand("8", program8.toString(), controller8));
        menu.show();
    }
}
