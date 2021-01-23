package GUI;

import Model.ADTs.Dictionary.Dictionary;
import Model.ADTs.Dictionary.IDictionary;
import Model.ADTs.FileTable.FileTable;
import Model.ADTs.HeapTable.HeapTable;
import Model.ADTs.HeapTable.IHeapTable;
import Model.ADTs.List.IList;
import Model.ADTs.List.List;
import Model.ADTs.Stack.IStack;
import Model.ADTs.Stack.MyStack;
import Model.Expressions.*;
import Model.Statements.*;
import Model.Statements.Files.CloseRFileStatement;
import Model.Statements.Files.OpenRFileStatement;
import Model.Statements.Files.ReadFileStatement;
import Model.Statements.Heap.HeapWritingStatement;
import Model.Statements.Heap.NewStatement;
import Model.Statements.Loops.WhileStatement;
import Model.Types.IntType;
import Model.Types.ReferenceType;
import Model.Types.StringType;
import Model.Values.BoolValue;
import Model.Values.IValue;
import Model.Values.IntValue;
import Model.Values.StringValue;

import java.io.BufferedReader;
import java.util.ArrayList;

public class ProgramsGenerator {

    private ArrayList<IStatement> programs;

    public ProgramsGenerator() {
        this.programs= new ArrayList<>();

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


        // Ref int v;new(v,20);print(rH(v)); wH(v,30);print(rH(v)+5);
        IStatement program5 = new CompStatement(
                new VarDeclarationStatement("v", new ReferenceType(new IntType())),
                new CompStatement(
                        new NewStatement("v", new ValueExpression(new IntValue(20))),
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

        //Ref int v;new(v,20);Ref Ref int a; new(a,v); writeheap(v,30);print(rH(rH(a)))
        IStatement program6 = new CompStatement(
                new VarDeclarationStatement("v", new ReferenceType(new IntType())),
                new CompStatement(
                        new NewStatement("v", new ValueExpression(new IntValue(20))),
                        new CompStatement(
                                new VarDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                                new CompStatement(
                                        new NewStatement("a", new VarExpression("v")),
                                        new CompStatement(
                                                new HeapWritingStatement("v", new ValueExpression(new IntValue(30))),
                                                new PrintStatement(new HeapReadingExpression(
                                                        new HeapReadingExpression(new VarExpression("a")))))))));


        //int v; v=4; (while (v>0) print(v);v=v-1);print(v)
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

        // int v; Ref int a; v=10;new(a,22); fork(wH(a,30);v=32;print(v);print(rH(a))); print(v);print(rH(a))
        IStatement program8  = new CompStatement(
                new VarDeclarationStatement("v", new IntType()),
                new CompStatement(
                        new VarDeclarationStatement("a", new ReferenceType(new IntType())),
                        new CompStatement(
                                new AssignStatement("v", new ValueExpression(new IntValue(10))),
                                new CompStatement(
                                        new NewStatement("a", new ValueExpression(new IntValue(22))),
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

        // error in type checker
        IStatement program9  = new CompStatement(
                new VarDeclarationStatement("v", new IntType()),
                new CompStatement(
                        new VarDeclarationStatement("a", new ReferenceType(new IntType())),
                        new CompStatement(
                                new AssignStatement("v", new ValueExpression(new BoolValue(true))),
                                new CompStatement(
                                        new NewStatement("a", new ValueExpression(new IntValue(22))),
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


        this.programs.add(program1);
        this.programs.add(program2);
        this.programs.add(program3);
        this.programs.add(program4);
        this.programs.add(program5);
        this.programs.add(program6);
        this.programs.add(program7);
        this.programs.add(program8);
        this.programs.add(program9);
    }

    public ArrayList<IStatement> getPrograms() {
        return this.programs;
    }

}
