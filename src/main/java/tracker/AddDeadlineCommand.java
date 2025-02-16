package tracker;

/**
 * Handles the addition of a new deadline task.
 * Parses the user input, validates the format, and adds the task to the task list.
 */
public class AddDeadlineCommand extends Command {
    static final int SPLIT_INDEX = 8;
    static final int MAX_SIZE = 2;
    static final int FIRST_PART = 0;
    static final int SECOND_PART = 1;
    private String input;
    private String[] parts;

    /**
     * Constructs an AddDeadlineCommand with the specified input.
     *
     * @param input The user input containing the deadline task details.
     */
    public AddDeadlineCommand(String input) {
        assert input != null : "Input cannot be null";
        this.input = input;
    }

    /**
     * @return The date.
     */
    private String[] parseInput() {
        return input.substring(SPLIT_INDEX).split(" /by ");
    }

    /**
     * @return If the input is conforming of format.
     */
    private boolean validateInput() {
        boolean isLessThanLimit = parts.length < MAX_SIZE;
        boolean isDescriptionEmpty = parts[FIRST_PART].trim().isEmpty();
        boolean isMoreThanLimit = parts.length >= MAX_SIZE;
        boolean isDateEmpty = isMoreThanLimit && parts[SECOND_PART].trim().isEmpty();
        boolean isValidCode = isLessThanLimit || isDescriptionEmpty || isDateEmpty;
        return !(isValidCode);
    }

    /**
     * @param taskList The task list to add the task to.
     * @param storage The storage to save the updated task list.
     * @return The response from the application.
     */
    private String addDeadlineTask(TaskList taskList, Storage storage) {
        StringBuilder response = new StringBuilder();
        try {
            Task task = new Deadline(parts[FIRST_PART].trim(), parts[SECOND_PART].trim());
            taskList.addTask(task);
            response.append("Got it. I've added this task:\n").append(task)
                    .append("\nNow you have ").append(taskList.size()).append(" tasks in the list.");
            storage.saveTasks(taskList.getTasks());
        } catch (IllegalArgumentException e) {
            response.append(e.getMessage());
        } catch (Exception e) {
            response.append("Error: Failed to save tasks ").append(e.getMessage());
        }
        return response.toString();
    }

    /**
     * Executes the command to add a deadline task.
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
        assert storage != null : "Storage cannot be null";
        assert ui != null : "UI cannot be null";

        parts = parseInput();

        if (!validateInput()) {
            return "Error: Invalid deadline format. Use: deadline <description> /by <yyyy-MM-dd HHmm>";
        }

        return addDeadlineTask(taskList, storage);
    }
}
