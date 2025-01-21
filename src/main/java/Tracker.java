import java.util.Scanner;
import java.util.ArrayList;

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
        ArrayList<Task> toDoList = new ArrayList<>();
        greet();

        String input;
        do {
            input = scanner.nextLine();
            if (!input.equalsIgnoreCase("bye")) {
                if (input.equalsIgnoreCase("list")) {
                    System.out.println(HORIZONTAL_LINE);
                    if (toDoList.isEmpty()) {
                        System.out.println("    To Do List is Empty.");
                    } else {
                        for (int i = 0; i < toDoList.size(); i++) {
                            System.out.println("    " + (i + 1) + ". " + toDoList.get(i));
                        }
                    }
                    System.out.println(HORIZONTAL_LINE);
                } else if (input.startsWith("todo")) {
                    String description = input.substring(5).trim();
                    Task task = new Todo(description);
                    toDoList.add(task);
                    System.out.println(HORIZONTAL_LINE);
                    System.out.println("     Got it. I've added this task:");
                    System.out.println("       " + task);
                    System.out.println("     Now you have " + toDoList.size() + " tasks in the list.");
                    System.out.println(HORIZONTAL_LINE);
                } else if (input.startsWith("deadline")) {
                    String[] parts = input.substring(9).split(" /by ");
                    if (parts.length == 2) {
                        Task task = new Deadline(parts[0].trim(), parts[1].trim());
                        toDoList.add(task);
                        System.out.println(HORIZONTAL_LINE);
                        System.out.println("     Got it. I've added this task:");
                        System.out.println("       " + task);
                        System.out.println("     Now you have " + toDoList.size() + " tasks in the list.");
                        System.out.println(HORIZONTAL_LINE);
                    } else {
                        System.out.println(HORIZONTAL_LINE);
                        System.out.println("    Invalid deadline format. Use: deadline <description> /by <time>");
                        System.out.println(HORIZONTAL_LINE);
                    }
                } else if (input.startsWith("event ")) {
                    String[] parts = input.substring(6).split(" /from ");
                    if (parts.length == 2) {
                        String[] times = parts[1].split(" /to ");
                        if (times.length == 2) {
                            Task task = new Event(parts[0].trim(), times[0].trim(), times[1].trim());
                            toDoList.add(task);
                            System.out.println(HORIZONTAL_LINE);
                            System.out.println("     Got it. I've added this task:");
                            System.out.println("       " + task);
                            System.out.println("     Now you have " + toDoList.size() + " tasks in the list.");
                            System.out.println(HORIZONTAL_LINE);
                        } else {
                            System.out.println(HORIZONTAL_LINE);
                            System.out.println("    Invalid event format. Use: event <description> /from <start> /to <end>");
                            System.out.println(HORIZONTAL_LINE);
                        }
                    } else {
                        System.out.println(HORIZONTAL_LINE);
                        System.out.println("    Invalid event format. Use: event <description> /from <start> /to <end>");
                        System.out.println(HORIZONTAL_LINE);
                    }
                } else if (input.startsWith("mark ")) {
                    try {
                        int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                        if (taskIndex >= 0 && taskIndex < toDoList.size()) {
                            Task task = toDoList.get(taskIndex);
                            task.markAsDone();
                            System.out.println(HORIZONTAL_LINE);
                            System.out.println("    Nice! I've marked this task as done:");
                            System.out.println("      " + task);
                            System.out.println(HORIZONTAL_LINE);
                        } else {
                            System.out.println(HORIZONTAL_LINE);
                            System.out.println("    Invalid task number.");
                            System.out.println(HORIZONTAL_LINE);
                        }
                    } catch (Exception e) {
                        System.out.println(HORIZONTAL_LINE);
                        System.out.println("    Please enter a valid task number to mark.");
                        System.out.println(HORIZONTAL_LINE);
                    }
                } else if (input.startsWith("unmark ")) {
                    try {
                        int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                        if (taskIndex >= 0 && taskIndex < toDoList.size()) {
                            Task task = toDoList.get(taskIndex);
                            task.unmarkAsDone();
                            System.out.println(HORIZONTAL_LINE);
                            System.out.println("    OK, I've marked this task as not done yet:");
                            System.out.println("      " + task);
                            System.out.println(HORIZONTAL_LINE);
                        } else {
                            System.out.println(HORIZONTAL_LINE);
                            System.out.println("    Invalid task number.");
                            System.out.println(HORIZONTAL_LINE);
                        }
                    } catch (Exception e) {
                        System.out.println(HORIZONTAL_LINE);
                        System.out.println("    Please enter a valid task number to unmark.");
                        System.out.println(HORIZONTAL_LINE);
                    }
                } else {
                    toDoList.add(new Task(input));
                    System.out.println(HORIZONTAL_LINE);
                    System.out.println("     added: " + input);
                    System.out.println(HORIZONTAL_LINE);
                }
            }
        } while (!input.equalsIgnoreCase("bye"));

        System.out.println(HORIZONTAL_LINE);
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }
}
