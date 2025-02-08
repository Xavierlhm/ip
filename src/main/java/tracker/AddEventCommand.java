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
        StringBuilder response = new StringBuilder();
        String[] parts = input.substring(SPLIT_INDEX).split(" /from ");
        boolean isLessThanLimit = parts.length < MAX_SIZE;
        boolean isDescriptionEmpty = parts[FIRST_PART].trim().isEmpty();
        boolean isValidCode = isLessThanLimit || isDescriptionEmpty;
        if (isValidCode) {
            response.append("Error: Invalid event format. Use: event <description> /from <start> /to <end>");
            return response.toString();
        }
        String[] times = parts[SECOND_PART].split(" /to ");
        boolean isWithinLimit = times.length < MAX_SIZE;
        boolean isFromEmpty = times[FIRST_PART].trim().isEmpty();
        boolean isToEmpty = times[SECOND_PART].trim().isEmpty();
        boolean isValidTime = isWithinLimit || isFromEmpty || isToEmpty;
        if (isValidTime) {
            response.append("Error: Invalid event format. Use: event <description> /from <start> /to <end>");
            return response.toString();
        }
        try {
            Task task = new Event(parts[FIRST_PART].trim(), times[FIRST_PART].trim(), times[SECOND_PART].trim());
            taskList.addTask(task);
            response.append("Got it. I've added this task:\n").append(task).append("\nNow you have ")
                    .append(taskList.size()).append(" tasks in the list.");
            storage.saveTasks(taskList.getTasks());
        } catch (IllegalArgumentException e) {
            response.append("Error: ").append(e.getMessage());
        } catch (Exception e) {
            response.append("Error: Failed to save tasks ").append(e.getMessage());
        }
        return response.toString();
    }
}
