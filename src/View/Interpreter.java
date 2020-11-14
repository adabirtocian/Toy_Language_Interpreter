package View;

import Controller.Controller;
import Model.ADTs.*;
import Model.Expressions.RelationalExpressions;
import Model.Expressions.ValueExpression;
import Model.Expressions.VarExpression;
import Model.ProgramState;
import Model.Statements.*;
import Model.Types.IntType;
import Model.Types.StringType;
import Model.Values.IValue;
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

        IStatement program1 = new CompStatement(
                                 new VarDeclarationStatement("varf", new StringType()),
                                 new CompStatement(
                                     new AssignStatement("varf", new ValueExpression(new StringValue("C:\\Storage\\Facultate\\Semester 3\\Advanced programming methods\\Labs\\Assignments\\Assignment - Interpretor\\tests\\test.in"))),
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
        ProgramState programState1 = new ProgramState(exeStack1, symbolTable1, out1, program1, fileTable1);
        IRepository repository1 = new Repository("log1.txt");
        Controller controller1 = new Controller(repository1);
        controller1.addProgram(programState1);

        IStack<IStatement> exeStack2 = new MyStack<IStatement>();
        IDictionary<String, IValue> symbolTable2 = new Dictionary<String, IValue>();
        IDictionary<StringValue, BufferedReader> fileTable2 = new FileTable<>();
        IList<IValue> out2 = new List<IValue>();

        IStatement program2 = new CompStatement(
                new VarDeclarationStatement("varf", new StringType()),
                new CompStatement(
                        new AssignStatement("varf", new ValueExpression(new StringValue("C:\\Storage\\Facultate\\Semester 3\\Advanced programming methods\\Labs\\Assignments\\Assignment - Interpretor\\tests\\test.in"))),
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
                                                                                    new RelationalExpressions(
                                                                                            new VarExpression("a"),
                                                                                            new VarExpression("varc"),
                                                                                            ">"),
                                                                                    new PrintStatement(new VarExpression("a")),
                                                                                    new PrintStatement(new VarExpression("varc"))),
                                                                            new CloseRFileStatement(new VarExpression("varf")))))))))));

        ProgramState programState2 = new ProgramState(exeStack2, symbolTable2, out2, program2, fileTable2);
        IRepository repository2 = new Repository("log2.txt");
        Controller controller2 = new Controller(repository2);
        controller2.addProgram(programState2);

        IStack<IStatement> exeStack3 = new MyStack<IStatement>();
        IDictionary<String, IValue> symbolTable3 = new Dictionary<String, IValue>();
        IDictionary<StringValue, BufferedReader> fileTable3 = new FileTable<>();
        IList<IValue> out3 = new List<IValue>();

        IStatement program3 = new CompStatement(
                new VarDeclarationStatement("varf", new StringType()),
                new CompStatement(
                        new AssignStatement("varf", new ValueExpression(new StringValue("C:\\Storage\\Facultate\\Semester 3\\Advanced programming methods\\Labs\\Assignments\\Assignment - Interpretor\\tests\\test.in"))),
                        new CompStatement(
                                new OpenRFileStatement(new VarExpression("varf")),
                                new CloseRFileStatement(new VarExpression("varf")))));

        ProgramState programState3 = new ProgramState(exeStack3, symbolTable3, out3, program3, fileTable3);
        IRepository repository3 = new Repository("log3.txt");
        Controller controller3 = new Controller(repository3);
        controller3.addProgram(programState3);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExampleCommand("1", program1.toString(), controller1));
        menu.addCommand(new RunExampleCommand("2", program2.toString(), controller2));
        menu.addCommand(new RunExampleCommand("3", program3.toString(), controller3));
        menu.show();
    }
}
