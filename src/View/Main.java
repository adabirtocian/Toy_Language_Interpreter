//package View;
//
//import Controller.Controller;
//import Model.ADTs.*;
//import Model.Exceptions.MyException;
//import Model.Expressions.ArithmeticExpression;
//import Model.Expressions.ValueExpression;
//import Model.Expressions.VarExpression;
//import Model.ProgramState;
//import Model.Statements.*;
//import Model.Types.BoolType;
//import Model.Types.IntType;
//import Model.Values.BoolValue;
//import Model.Values.IValue;
//import Model.Values.IntValue;
//import Repository.Repository;
//
//import java.util.ArrayList;
//import java.util.Scanner;
//
//
//public class Main {
//
//    static Repository repository = new Repository();
//    static Controller controller = new Controller(repository);
//
//    public static void main(String[] args) throws CloneNotSupportedException {
//        ArrayList<IStatement> allPrograms = new ArrayList<>(10);
//
//        IStack<IStatement> exeStack = new MyStack<IStatement>();
//        IDictionary<String, IValue> symbolTable = new Dictionary<String, IValue>();
//        IList<IValue> out = new List<IValue>();
//
//
//        IStatement program1 = new CompStatement(
//                                new VarDeclarationStatement("v", new IntType()),
//                                new CompStatement(
//                                        new AssignStatement("v", new ValueExpression(new IntValue(2))),
//                                        new PrintStatement(new VarExpression("v"))));
//
//
//        IStatement program2 = new CompStatement(
//                                new VarDeclarationStatement("a", new IntType()),
//                                new CompStatement(
//                                        new VarDeclarationStatement("b", new IntType()),
//                                        new CompStatement(
//                                                new AssignStatement("a",
//                                                        new ArithmeticExpression(
//                                                                new ValueExpression(new IntValue(2)),
//                                                                new ArithmeticExpression(
//                                                                        new ValueExpression(new IntValue(3)),
//                                                                        new ValueExpression(new IntValue(5)),'*'), '+')),
//                                                new CompStatement(
//                                                        new AssignStatement("b",
//                                                                new ArithmeticExpression(
//                                                                        new VarExpression("a"),
//                                                                        new ValueExpression(new IntValue(1)), '+')),
//                                                        new PrintStatement(new VarExpression("b"))))));
//
//
//        IStatement program3 = new CompStatement(
//                                new VarDeclarationStatement("a", new BoolType()),
//                                new CompStatement(
//                                        new VarDeclarationStatement("v", new IntType()),
//                                        new CompStatement(
//                                                new AssignStatement("a", new ValueExpression(new BoolValue(true))),
//                                                new CompStatement(
//                                                        new IfStatement(
//                                                                new VarExpression("a"),
//                                                                new AssignStatement("v", new ValueExpression(new IntValue(2))),
//                                                                new AssignStatement("v", new ValueExpression(new IntValue(3)))),
//                                                        new PrintStatement(new VarExpression("v"))))));
//
//        allPrograms.add(program1);
//        allPrograms.add(program2);
//        allPrograms.add(program3);
//
//        IStatement cloned = (IStatement) program1.deepCopy();
//
//
//        System.out.println("Choose from the already loaded programs.\nInput a number between 0 and 2: ");
//        Scanner scanner = new Scanner(System.in);
//        int userChoiceProgramNumber = scanner.nextInt();
//
//        while (userChoiceProgramNumber < 0 || userChoiceProgramNumber > 2) {
//            System.out.println("Choose from the already loaded programs.\nType a number between 0 and 2: ");
//            scanner = new Scanner(System.in);
//            userChoiceProgramNumber = scanner.nextInt();
//        }
//
//        ProgramState programState = new ProgramState(exeStack, symbolTable, out, allPrograms.get(userChoiceProgramNumber));
//        controller.addProgram(programState);
//
//        System.out.println("Want to show all the steps ? [Y or N] ");
//        scanner = new Scanner(System.in);
//        String userInput = scanner.nextLine();
//
//        try {
//            System.out.println("Original program:\n" + allPrograms.get(userChoiceProgramNumber).toString() + "\n");
//            controller.allSteps();
//
//            if(userInput.equalsIgnoreCase("y")) System.out.println(controller.getAllSteps());
//            else System.out.println(controller.getOutput());
//
//        } catch (MyException exception) {
//            System.out.println(exception.getMessage());
//        }
//
//    }
//}
