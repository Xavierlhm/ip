package tracker;

import java.util.Scanner;

/**
 * Handles user interaction for the tracker application.
 */
public class Ui {
    private static final String HORIZONTAL_LINE = "    ____________________________________________________________";
    private Scanner scanner;

    /**
     * Constructs a Ui instance for handling user input and output.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays a greeting message to the user.
     */
    public void greet() {
        String message = "    Hello! I'm tracker.Tracker\n    What can I do for you?";
        System.out.println(HORIZONTAL_LINE);
        System.out.println(message);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Reads a command from the user.
     *
     * @return The user's input as a string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays an error message for failed task loading.
     */
    public void loadError() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("    Error loading tasks. Starting with an empty list.");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message.
     */
    public void error(String message) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("    OOPS!!! " + message);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays a generic message to the user.
     *
     * @param message The message to display.
     */
    public void message(String message) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(message);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays a goodbye message when exiting the application.
     */
    public void goodBye() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }
}
