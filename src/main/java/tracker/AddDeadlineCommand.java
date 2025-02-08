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
        StringBuilder response = new StringBuilder();
        String[] parts = input.substring(SPLIT_INDEX).split(" /by ");
        boolean isLessThanLimit = parts.length < MAX_SIZE;
        boolean isDescriptionEmpty = parts[FIRST_PART].trim().isEmpty();
        boolean isByEmpty = parts[SECOND_PART].trim().isEmpty();
        boolean isValidCode = isLessThanLimit || isDescriptionEmpty || isByEmpty;
        //assert parts.length >= 2 : "Deadline input must contain description and date";
        if (isValidCode) {
            response.append("Error: Invalid deadline format. Use: deadline <description> /by <yyyy-MM-dd HHmm>");
            return response.toString();
        }
        try {
            Task task = new Deadline(parts[FIRST_PART].trim(), parts[SECOND_PART].trim());
            //assert task != null : "Task creation failed";
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
