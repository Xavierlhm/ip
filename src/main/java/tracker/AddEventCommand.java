package tracker;

/**
 * Handles the addition of a new event task.
 * Parses the user input, validates the format, and adds the task to the task list.
 */
public class AddEventCommand extends Command {
    static final int SPLIT_INDEX = 5;
    static final int MAX_SIZE = 2;
    static final int FIRST_PART = 0;
    static final int SECOND_PART = 1;
    private String input;

    /**
     * Constructs an AddEventCommand with the specified input.
     *
     * @param input The user input containing the event task details.
     */
    public AddEventCommand(String input) {
        assert input != null && !input.isEmpty() : "Input cannot be null or empty";
        this.input = input;
    }

    /**
     * Parses the input string to extract event details.
     *
     * @param input The user input string.
     * @return A string array containing event description, start, and end times.
     */
    private String[] parseEventInput(String input) {
        return input.substring(SPLIT_INDEX).split(" /from ");
    }

    /**
     * Validates the extracted event details.
     *
     * @param parts The event details array.
     * @return If validation fails.
     */
    private boolean validateCommand(String[] parts) {
        boolean isLessThanLimit = parts.length < MAX_SIZE;
        boolean isDescriptionEmpty = parts[FIRST_PART].trim().isEmpty();
        return isLessThanLimit || isDescriptionEmpty;
    }

    /**
     * Validates the extracted event details.
     *
     * @param times The event details array.
     * @return If validation fails.
     */
    private boolean validateDate(String[] times) {
        boolean isWithinLimit = times.length < MAX_SIZE;
        boolean isFromEmpty = times[FIRST_PART].trim().isEmpty();
        boolean isToEmpty = times.length > SECOND_PART && times[SECOND_PART].trim().isEmpty();
        return isWithinLimit || isFromEmpty || isToEmpty;
    }

    /**
     * Creates an Event task from the parsed details.
     *
     * @param parts The event details array.
     * @return A new Event task.
     */
    private Task createEventTask(String[] parts) {
        String[] times = parts[SECOND_PART].split(" /to ");
        return new Event(parts[FIRST_PART].trim(), times[FIRST_PART].trim(), times[SECOND_PART].trim());
    }

    /**
     * Adds the task to the task list.
     *
     * @param taskList The task list.
     * @param task     The event task.
     */
    private void addTaskToTaskList(TaskList taskList, Task task) {
        taskList.addTask(task);
    }

    /**
     * Formats the success message.
     *
     * @param task      The added event task.
     * @param taskCount The updated task count.
     * @return A formatted success message.
     */
    private String formatSuccessMessage(Task task, int taskCount) {
        return String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.", task, taskCount);
    }

    /**
     * Executes the command to add an event task.
     * Validates the input format and adds the task to the task list.
     *
     * @param taskList The task list to add the task to.
     * @param ui       The UI to display messages to the user.
     * @param storage  The storage to save the updated task list.
     * @return true to continue program execution.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        assert taskList != null : "TaskList cannot be null";
        assert ui != null : "Ui cannot be null";
        assert storage != null : "Storage cannot be null";
        String[] eventDetails = parseEventInput(input);

        if (validateCommand(eventDetails)) {
            return "Error: Invalid event format. Use: event <description> /from <start> /to <end>";
        }

        String[] times = eventDetails[SECOND_PART].split(" /to ");

        if (validateCommand(times)) {
            return "Error: Invalid event format. Use: event <description> /from <start> /to <end>";
        }

        try {
            Task eventTask = createEventTask(eventDetails);
            addTaskToTaskList(taskList, eventTask);
            storage.saveTasks(taskList.getTasks());
            return formatSuccessMessage(eventTask, taskList.size());
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
