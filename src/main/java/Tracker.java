import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;

public class Tracker {
    private static final String HORIZONTAL_LINE = "    ____________________________________________________________";

    public static void greet() {
        String message = "    Hello! I'm Tracker\n    What can I do for you?";
        System.out.println(HORIZONTAL_LINE);
        System.out.println(message);
        System.out.println(HORIZONTAL_LINE);
    }

    private static void handleList(ArrayList<Task> toDoList) {
        System.out.println(HORIZONTAL_LINE);
        if (toDoList.isEmpty()) {
            System.out.println("    Your to-do list is currently empty.");
        } else {
            System.out.println("    Here are the tasks in your list:");
            for (int i = 0; i < toDoList.size(); i++) {
                System.out.println("    " + (i + 1) + ". " + toDoList.get(i));
            }
        }
        System.out.println(HORIZONTAL_LINE);
    }

    private static void handleTodo(String input, ArrayList<Task> toDoList) throws TrackerException {
        String description = input.substring(4).trim();
        if (description.isEmpty()) {
            throw new TrackerException("The description of a todo cannot be empty.");
        }
        Task task = new Todo(description);
        toDoList.add(task);
        saveTasks(toDoList);
        printTaskAdded(task, toDoList.size());
    }

    private static void handleDeadline(String input, ArrayList<Task> toDoList) throws TrackerException {
        String[] parts = input.substring(8).split(" /by ");
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new TrackerException("Invalid deadline format. Use: deadline <description> /by <yyyy-MM-dd HHmm>");
        }

        try {
            Task task = new Deadline(parts[0].trim(), parts[1].trim());
            toDoList.add(task);
            saveTasks(toDoList);
            printTaskAdded(task, toDoList.size());
        } catch (Exception e) {
            throw new TrackerException("Invalid date format. Please use yyyy-MM-dd HHmm (e.g., 2019-12-02 1800).");
        }
    }

    private static void handleEvent(String input, ArrayList<Task> toDoList) throws TrackerException {
        String[] parts = input.substring(5).split(" /from ");
        if (parts.length < 2 || parts[0].trim().isEmpty()) {
            throw new TrackerException("Invalid event format. Use: event <description> /from <start> /to <end>");
        }
        String[] times = parts[1].split(" /to ");
        if (times.length < 2 || times[0].trim().isEmpty() || times[1].trim().isEmpty()) {
            throw new TrackerException("Invalid event format. Use: event <description> /from <start> /to <end>");
        }

        try {
            Task task = new Event(parts[0].trim(), times[0].trim(), times[1].trim());
            toDoList.add(task);
            saveTasks(toDoList);
            printTaskAdded(task, toDoList.size());
        } catch (Exception e) {
            throw new TrackerException("Invalid date format. Please use yyyy-MM-dd HHmm (e.g., 2019-12-02 1800).");
        }
    }

    private static void handleMark(String input, ArrayList<Task> toDoList) throws TrackerException {
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            if (index < 0 || index >= toDoList.size()) {
                throw new TrackerException("Task number out of range. Please check your list.");
            }
            Task task = toDoList.get(index);
            task.markAsDone();
            saveTasks(toDoList);
            System.out.println(HORIZONTAL_LINE);
            System.out.println("    Nice! I've marked this task as done:");
            System.out.println("      " + task);
            System.out.println(HORIZONTAL_LINE);
        } catch (Exception e) {
            throw new TrackerException("Invalid mark command. Use: mark <task_number>");
        }
    }

    private static void handleUnmark(String input, ArrayList<Task> toDoList) throws TrackerException {
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            if (index < 0 || index >= toDoList.size()) {
                throw new TrackerException("Task number out of range. Please check your list.");
            }
            Task task = toDoList.get(index);
            task.unmarkAsDone();
            saveTasks(toDoList);
            System.out.println(HORIZONTAL_LINE);
            System.out.println("    OK, I've marked this task as not done yet:");
            System.out.println("      " + task);
            System.out.println(HORIZONTAL_LINE);
        } catch (Exception e) {
            throw new TrackerException("Invalid unmark command. Use: unmark <task_number>");
        }
    }

    private static void handleDelete(String input, ArrayList<Task> toDoList) throws TrackerException {
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            if (index < 0 || index >= toDoList.size()) {
                throw new TrackerException("Task number out of range. Please check your list.");
            }
            Task task = toDoList.remove(index);
            saveTasks(toDoList);
            System.out.println(HORIZONTAL_LINE);
            System.out.println("    Noted. I've removed this task:");
            System.out.println("      " + task);
            System.out.println("     Now you have " + toDoList.size() + " tasks in the list.");
            System.out.println(HORIZONTAL_LINE);
        } catch (Exception e) {
            throw new TrackerException("Invalid delete command. Use: delete <task_number>");
        }
    }

    private static void printTaskAdded(Task task, int size) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + task);
        System.out.println("     Now you have " + size + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    private static void saveTasks(ArrayList<Task> toDoList) {
        try {
            TaskManager.saveTasks(toDoList);
        } catch (IOException e) {
            System.out.println(HORIZONTAL_LINE);
            System.out.println("    Error saving tasks: " + e.getMessage());
            System.out.println(HORIZONTAL_LINE);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> toDoList;

        try {
            toDoList = TaskManager.loadTasks();
        } catch (IOException e) {
            System.out.println(HORIZONTAL_LINE);
            System.out.println("    Error loading tasks. Starting with an empty list.");
            System.out.println(HORIZONTAL_LINE);
            toDoList = new ArrayList<>();
        }

        greet();

        String input;
        do {
            input = scanner.nextLine();
            try {
                if (input.equalsIgnoreCase("bye")) {
                    break;
                } else if (input.equalsIgnoreCase("list")) {
                    handleList(toDoList);
                } else if (input.startsWith("todo")) {
                    handleTodo(input, toDoList);
                } else if (input.startsWith("deadline")) {
                    handleDeadline(input, toDoList);
                } else if (input.startsWith("event")) {
                    handleEvent(input, toDoList);
                } else if (input.startsWith("mark")) {
                    handleMark(input, toDoList);
                } else if (input.startsWith("unmark")) {
                    handleUnmark(input, toDoList);
                } else if (input.startsWith("delete")) {
                    handleDelete(input, toDoList);
                } else {
                    throw new TrackerException("I'm sorry, but I don't know what that means. Please try again!");
                }
            } catch (TrackerException e) {
                System.out.println(HORIZONTAL_LINE);
                System.out.println("    OOPS!!! " + e.getMessage());
                System.out.println(HORIZONTAL_LINE);
            }
        } while (!input.equalsIgnoreCase("bye"));

        System.out.println(HORIZONTAL_LINE);
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }
}
