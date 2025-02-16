package tracker;

/**
 * Handles the deletion of a task from the task list.
 * Parses the user input, validates the task index, and removes the task.
 */
public class DeleteCommand extends Command {
    static final int SPLIT_INDEX = 1;
    static final int EMPTY_INDEX = 0;
    static final int ONE_INDEX = 1;
    private String input;
    private int taskIndex;

    /**
     * Constructs a DeleteCommand with the specified input.
     *
     * @param input The user input containing the task index to delete.
     */
    public DeleteCommand(String input) {
        assert input != null && !input.isEmpty() : "Input cannot be null or empty";
        this.input = input;
    }

    /**
     * @return Task Index to delete.
     */
    private int parseTaskIndex() {
        try {
            return Integer.parseInt(input.split(" ")[SPLIT_INDEX]) - ONE_INDEX;
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * @param taskList The task list from which the task will be deleted.
     * @return If the task index input is valid.
     */
    private boolean isValidTaskIndex(TaskList taskList) {
        boolean isWithinSize = taskIndex >= EMPTY_INDEX;
        boolean isMoreThanSize = taskIndex < taskList.size();
        return isWithinSize && isMoreThanSize;
    }

    /**
     * @param taskList The task list from which the task will be deleted.
     * @param storage The storage to save the updated task list.
     * @return The response from the application.
     * @throws TrackerException If the task index is invalid.
     */
    private String deleteTask(TaskList taskList, Storage storage) throws TrackerException {
        StringBuilder response = new StringBuilder();
        Task task = taskList.removeTask(taskIndex);
        response.append("Noted. I've removed this task:\n").append(task)
                .append("\nNow you have ").append(taskList.size()).append(" tasks in the list.");

        try {
            storage.saveTasks(taskList.getTasks());
        } catch (Exception e) {
            response.append("Error: Failed to save tasks: ").append(e.getMessage());
        }
        return response.toString();
    }

    /**
     * Executes the command to delete a task.
     * Validates the task index and removes the task from the task list.
     *
     * @param taskList The task list from which the task will be deleted.
     * @param ui       The UI to display messages to the user.
     * @param storage  The storage to save the updated task list.
     * @return true to continue program execution.
     * @throws TrackerException If the task index is invalid.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws TrackerException {
        assert taskList != null : "TaskList cannot be null";
        assert ui != null : "Ui cannot be null";
        assert storage != null : "Storage cannot be null";

        taskIndex = parseTaskIndex();
        if (!isValidTaskIndex(taskList)) {
            return "Error: Invalid task index.";
        }

        return deleteTask(taskList, storage);
    }
}
