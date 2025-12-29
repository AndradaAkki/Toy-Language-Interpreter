package view;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ProgramView {
    private final Map<String, Command> commands = new HashMap<>();

    public void addCommand(Command command) {
        commands.put(command.getKey(), command);
    }

    private void printMenu() {
        System.out.println("\n-----> Menu <-----");
        for (Command cmd : commands.values()) {
            System.out.println(cmd.getKey() + "." + cmd.getDescription());
        }
        System.out.print("Choose option: ");
    }

    public void show() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            String key = scanner.nextLine();
            Command cmd = commands.get(key);
            if (cmd == null) {
                System.out.println("Invalid option!");
            } else {
                cmd.execute();
            }
        }
    }
}
