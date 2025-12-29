package view;

import controller.Controller;
import controller.ProgramController;
import model.expression.*;
import model.myException.MyException;
import model.state.*;
import model.statement.*;
import model.type.*;
import model.value.*;
import repository.ProgramRepository;

public class Main {

    public static void main(String[] args) {
        ProgramView mainMenu = new ProgramView();
        ProgramView lab5Menu = new ProgramView();
        ProgramView lab7Menu = new ProgramView();
        ProgramView lab10Menu = new ProgramView();


        mainMenu.addCommand(new ExitCommand("0", "Exit"));

        addLab5Examples(lab5Menu);
        addLab7Examples(lab7Menu);
        addLab10Examples(lab10Menu);


        mainMenu.addCommand(new OpenMenuCommand("1", "Run Lab 5 examples", lab5Menu));
        mainMenu.addCommand(new OpenMenuCommand("2", "Run Lab 7 examples", lab7Menu));
        mainMenu.addCommand(new OpenMenuCommand("3", "Run Lab 10 examples", lab10Menu));

        mainMenu.show();

    }


    public static void addLab5Examples(ProgramView menu) {
        // Example 1: int v; v=2; print(v);
        Statement ex1 = new CompoundStatement(
                new VarDeclStatement(new IntType(), "v"),
                new CompoundStatement(
                        new AssignmentStatement(new ConstantExpression(new IntValue(2)), "v"),
                        new PrintStatement(new VariableExpression("v"))
                )
        );

        // Example 2: int a; int b; a=2+3*5; b=a+1; print(b);
        Statement ex2 = new CompoundStatement(
                new VarDeclStatement(new IntType(), "a"),
                new CompoundStatement(
                        new VarDeclStatement(new IntType(), "b"),
                        new CompoundStatement(
                                new AssignmentStatement(
                                        new ArithmeticExpression('+',
                                                new ConstantExpression(new IntValue(2)),
                                                new ArithmeticExpression('*',
                                                        new ConstantExpression(new IntValue(3)),
                                                        new ConstantExpression(new IntValue(5))
                                                )
                                        ),
                                        "a"
                                ),
                                new CompoundStatement(
                                        new AssignmentStatement(
                                                new ArithmeticExpression('+',
                                                        new VariableExpression("a"),
                                                        new ConstantExpression(new IntValue(1))
                                                ),
                                                "b"
                                        ),
                                        new PrintStatement(new VariableExpression("b"))
                                )
                        )
                )
        );

        // Example 3: bool a; int v; a=true; If a Then v=2 Else v=3; print(v);
        Statement ex3 = new CompoundStatement(
                new VarDeclStatement(new BoolType(), "a"),
                new CompoundStatement(
                        new VarDeclStatement(new IntType(), "v"),
                        new CompoundStatement(
                                new AssignmentStatement(new ConstantExpression(new BoolValue(true)), "a"),
                                new CompoundStatement(
                                        new IfStatement(
                                                new VariableExpression("a"),
                                                new AssignmentStatement(new ConstantExpression(new IntValue(2)), "v"),
                                                new AssignmentStatement(new ConstantExpression(new IntValue(3)), "v")
                                        ),
                                        new PrintStatement(new VariableExpression("v"))
                                )
                        )
                )
        );

        // Example 4: File operations
        Statement ex4 = new CompoundStatement(
                new VarDeclStatement(new StringType(), "varf"),
                new CompoundStatement(
                        new AssignmentStatement(new ConstantExpression(new StringValue("test.in")), "varf"),
                        new CompoundStatement(
                                new OpenRFileStatement(new VariableExpression("varf")),
                                new CompoundStatement(
                                        new VarDeclStatement(new IntType(), "varc"),
                                        new CompoundStatement(
                                                new ReadFileStatement(new VariableExpression("varf"), "varc"),
                                                new CompoundStatement(
                                                        new PrintStatement(new VariableExpression("varc")),
                                                        new CompoundStatement(
                                                                new ReadFileStatement(new VariableExpression("varf"), "varc"),
                                                                new CompoundStatement(
                                                                        new PrintStatement(new VariableExpression("varc")),
                                                                        new CloseRFileStatement(new VariableExpression("varf"))
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );
        // Example 5: if (a < b) then print(a) else print(b);
        Statement ex5 = new CompoundStatement(
                new VarDeclStatement(new IntType(), "a"),
                new CompoundStatement(
                        new VarDeclStatement(new IntType(), "b"),
                        new CompoundStatement(
                                new AssignmentStatement(new ConstantExpression(new IntValue(10)), "a"),
                                new CompoundStatement(
                                        new AssignmentStatement(new ConstantExpression(new IntValue(20)), "b"),
                                        new IfStatement(
                                                new RelationalExpression("<",
                                                        new VariableExpression("a"),
                                                        new VariableExpression("b")),
                                                new PrintStatement(new VariableExpression("a")),
                                                new PrintStatement(new VariableExpression("b"))
                                        )
                                )
                        )
                )
        );


        // Build ProgramStates, Repositories, and Controllers
        Controller ctrl1 = buildController(ex1, "log1.txt");
        Controller ctrl2 = buildController(ex2, "log2.txt");
        Controller ctrl3 = buildController(ex3, "log3.txt");
        Controller ctrl4 = buildController(ex4, "log4.txt");
        Controller ctrl5 = buildController(ex5, "log5.txt");



        menu.addCommand(new ExitCommand("0", "Exit"));
        menu.addCommand(new RunExample("1", "Description -> int v, v=2, print(v)", ex1, "log1"));
        menu.addCommand(new RunExample("2", "Description ->int a, int b, a=2+3*5,b=a+1, print(b),", ex2, "log2"));
        menu.addCommand(new RunExample("3", "Description ->bool a, int v, a=true, if a then v=2 else v=3,print(v)", ex3, "log3"));
        menu.addCommand(new RunExample("4", "Description ->File example with openRFile/readFile/closeRFile: string varf; varf = 'test.in', openRFile(varf), int varc, readFile(varf, varc), print(varc), readFile(varf, varc), print(varc), closeRFile(varf)", ex4, "log4"));
        menu.addCommand(new RunExample("5", "Description ->if (a < b), then print(a) else print(b) : int a, int b, a=10, b=20, if (a < b) then print(a) else print(b)", ex5, "log5"));

    }

    public static void addLab7Examples(ProgramView menu) {
        Statement ex6 = new CompoundStatement(
                new VarDeclStatement(new RefType(new IntType()), "v"),
                new CompoundStatement(
                        new NewStatement("v", new ConstantExpression(new IntValue(20))),
                        new CompoundStatement(
                                new VarDeclStatement(new RefType(new RefType(new IntType())), "a"),
                                new CompoundStatement(
                                        new NewStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(
                                                new PrintStatement(new VariableExpression("v")),
                                                new PrintStatement(new VariableExpression("a"))
                                        )
                                )
                        )
                )
        );
        Controller ctrl6 = buildController(ex6, "log7_1.txt");

        menu.addCommand(new RunExample("1",
                "Ref int v; new(v,20); Ref Ref int a; new(a,v); print(v); print(a)",
                ex6, "log7_1"));

        Statement ex7 = new CompoundStatement(
                new VarDeclStatement(new RefType(new IntType()), "v"),
                new CompoundStatement(
                        new NewStatement("v", new ConstantExpression(new IntValue(20))),
                        new CompoundStatement(
                                new VarDeclStatement(new RefType(new RefType(new IntType())), "a"),
                                new CompoundStatement(
                                        new NewStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(
                                                new PrintStatement(
                                                        new ReadHeapExpression(new VariableExpression("v"))
                                                ),
                                                new PrintStatement(
                                                        new ArithmeticExpression('+',
                                                                new ReadHeapExpression(
                                                                        new ReadHeapExpression(new VariableExpression("a"))
                                                                ),
                                                                new ConstantExpression(new IntValue(5))
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );
        Controller ctrl7 = buildController(ex7, "log7_2.txt");

        menu.addCommand(new RunExample("2",
                "Ref int v;new(v,20);Ref Ref int a; new(a,v);print(rH(v));print(rH(rH(a))+5)",
                ex7, "log7_2"));


        Statement ex8 = new CompoundStatement(
                new VarDeclStatement(new RefType(new IntType()), "v"),
                new CompoundStatement(
                        new NewStatement("v", new ConstantExpression(new IntValue(20))),
                        new CompoundStatement(
                                new PrintStatement(
                                        new ReadHeapExpression(new VariableExpression("v"))
                                ),
                                new CompoundStatement(
                                        new WriteHeapStatement("v",
                                                new ConstantExpression(new IntValue(30))
                                        ),
                                        new PrintStatement(
                                                new ArithmeticExpression('+',
                                                        new ReadHeapExpression(new VariableExpression("v")),
                                                        new ConstantExpression(new IntValue(5))
                                                )
                                        )
                                )
                        )
                )
        );
        Controller ctrl8 = buildController(ex8, "log7_3.txt");

        menu.addCommand(new RunExample("3",
                "Ref int v;new(v,20);print(rH(v)); wH(v,30);print(rH(v)+5);",
                ex8, "log7_3"));
        Statement ex9 = new CompoundStatement(
                new VarDeclStatement(new RefType(new IntType()), "v"),
                new CompoundStatement(
                        new NewStatement("v", new ConstantExpression(new IntValue(20))),
                        new CompoundStatement(
                                new VarDeclStatement(new RefType(new RefType(new IntType())), "a"),
                                new CompoundStatement(
                                        new NewStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(
                                                new NewStatement("v", new ConstantExpression(new IntValue(30))),
                                                new PrintStatement(
                                                        new ReadHeapExpression(
                                                                new ReadHeapExpression(new VariableExpression("a"))
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );
        Controller ctrl9 = buildController(ex9, "log7_4.txt");

        menu.addCommand(new RunExample("4",
                "Ref int v;new(v,20);Ref Ref int a; new(a,v); new(v,30);print(rH(rH(a)))",
                ex9, "log7_4"));
        Statement ex10 = new CompoundStatement(
                new VarDeclStatement(new IntType(), "v"),
                new CompoundStatement(
                        new AssignmentStatement(new ConstantExpression(new IntValue(4)), "v"),
                        new CompoundStatement(
                                new WhileStatement(
                                        new RelationalExpression(">",
                                                new VariableExpression("v"),
                                                new ConstantExpression(new IntValue(0))),
                                        new CompoundStatement(
                                                new PrintStatement(new VariableExpression("v")),
                                                new AssignmentStatement(
                                                        new ArithmeticExpression('-',
                                                                new VariableExpression("v"),
                                                                new ConstantExpression(new IntValue(1))),
                                                        "v")
                                        )
                                ),
                                new PrintStatement(new VariableExpression("v"))
                        )
                )
        );
        Controller ctrl10 = buildController(ex10, "log7_5.txt");
        menu.addCommand(new RunExample("5",
                "int v; v=4; (while (v>0) print(v);v=v-1);print(v)",
                ex10, "log7_5"));


    }

    public static void addLab10Examples(ProgramView menu) {
        // int v; v = true;

        menu.addCommand(new ExitCommand("0", "Exit"));

        Statement bad1 = new CompoundStatement(
                new VarDeclStatement(new IntType(), "v"),
                new AssignmentStatement(
                        new ConstantExpression(new BoolValue(true)),
                        "v"
                )
        );

        Controller ctrlBad1 = buildController(bad1, "log10_bad1.txt");
        menu.addCommand(new RunExample("1",
                "Typecheck error example: int v; v = true;",
                bad1, "log10_bad1" ));


        // int v; if (v) print(v) else print(0);
        Statement bad2 = new CompoundStatement(
                new VarDeclStatement(new IntType(), "v"),
                new IfStatement(
                        new VariableExpression("v"),
                        new PrintStatement(new VariableExpression("v")),
                        new PrintStatement(new ConstantExpression(new IntValue(0)))
                )
        );
        Controller ctrlBad2 = buildController(bad2, "log10_bad2.txt");
        menu.addCommand(new RunExample("2",
                "Typecheck error example: int v; if (v) print(v) else print(0);",
                bad2, "log10_bad2"));

    }

    private static Controller buildController(Statement program, String logFilePath) {
        try {
            program.typecheck(new MyDictionary<>());
        } catch (MyException e) {
            System.out.println("Typecheck error: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("Unexpected error during typecheck: " + e.getMessage());
            return null;
        }


        var stack = new StackExecutionStack();
        stack.push(program);

        ProgramState state = new ProgramState(
                stack,
                new MapSymbolTable(),
                new ListOut(),
                new MapFileTable(),
                new MapHeap()
        );

        ProgramRepository repo = new ProgramRepository(logFilePath);
        repo.setProgramState(state);
        return new ProgramController(repo);
    }

}
