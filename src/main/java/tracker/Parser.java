package tracker;

public class Parser {
    public static Command parse(String input) throws TrackerException {
        if (input.startsWith("todo")) {
            return new AddTodoCommand(input);
        } else if (input.startsWith("deadline")) {
            return new AddDeadlineCommand(input);
        } else if (input.startsWith("event")) {
            return new AddEventCommand(input);
        } else if (input.startsWith("mark")) {
            return new MarkCommand(input);
        } else if (input.startsWith("unmark")) {
            return new UnmarkCommand(input);
        } else if (input.startsWith("delete")) {
            return new DeleteCommand(input);
        } else if (input.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (input.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        } else {
            throw new TrackerException("I'm sorry, but I don't know what that means.");
        }
    }
}
