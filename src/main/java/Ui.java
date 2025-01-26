import java.util.Scanner;

public class Ui {
    private static final String HORIZONTAL_LINE = "    ____________________________________________________________";
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void greet() {
        String message = "    Hello! I'm Tracker\n    What can I do for you?";
        System.out.println(HORIZONTAL_LINE);
        System.out.println(message);
        System.out.println(HORIZONTAL_LINE);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void loadError() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("    Error loading tasks. Starting with an empty list.");
        System.out.println(HORIZONTAL_LINE);
    }

    public void error(String message) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("    OOPS!!! " + message);
        System.out.println(HORIZONTAL_LINE);
    }

    public void message(String message) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(message);
        System.out.println(HORIZONTAL_LINE);
    }

    public void goodBye() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }
}
