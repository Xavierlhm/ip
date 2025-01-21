import java.util.Scanner;

public class Tracker {
    private static final String HORIZONTAL_LINE = "    ____________________________________________________________";

    public static void greet() {
        String message = "    Hello! I'm Tracker\n    What can I do for you?";
        System.out.println(HORIZONTAL_LINE);
        System.out.println(message);
        System.out.println(HORIZONTAL_LINE);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        greet();

        String input;
        do {
            input = scanner.nextLine();
            if (!input.equalsIgnoreCase("bye")) {
                System.out.println(HORIZONTAL_LINE);
                System.out.println("    " + input);
                System.out.println(HORIZONTAL_LINE);
            }
        } while (!input.equalsIgnoreCase("bye"));

        System.out.println(HORIZONTAL_LINE);
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }
}