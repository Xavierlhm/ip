package tracker;

import java.util.Scanner;

/**
 * Responsible for the user interaction.
 */
public class Ui {
    private static final String HORIZONTAL_LINE = "    ____________________________________________________________";
    private Scanner scanner;

    /**
     * Takes in user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays greeting message.
     */
    public void greet() {
        String message = "    Hello! I'm tracker.Tracker\n    What can I do for you?";
        System.out.println(HORIZONTAL_LINE);
        System.out.println(message);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * @return
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    public void loadError() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("    Error loading tasks. Starting with an empty list.");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * @param message
     */
    public void error(String message) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("    OOPS!!! " + message);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * @param message
     */
    public void message(String message) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(message);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays exit message.
     */
    public void goodBye() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }
}
