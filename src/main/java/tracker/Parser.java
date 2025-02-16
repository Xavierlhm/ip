package tracker;

/**
 * Parses user input to determine the appropriate command to execute.
 */
public class Parser {
    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param input The user input.
     * @return The appropriate Command object for the input.
     * @throws TrackerException If the command type is invalid.
     */
    public static Command parse(String input) throws TrackerException {
        assert input != null && !input.isEmpty() : "Input command cannot be null or empty";
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
        } else if (input.startsWith("find")) {
            return new FindCommand(input);
        } else if (input.equalsIgnoreCase("help")) {
            return new HelpCommand();
        } else {
            throw new TrackerException("I'm sorry, but I don't know what that means.");
        }
    }
}
