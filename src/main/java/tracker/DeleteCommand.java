package tracker;

/**
 * Handles the deletion of a task from the task list.
 * Parses the user input, validates the task index, and removes the task.
 */
public class DeleteCommand extends Command {
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
        StringBuilder response = new StringBuilder();
        try {
            this.taskIndex = Integer.parseInt(input.split(" ")[1]);
        } catch (Exception e) {
            response.append("Error: Invalid delete command. Use: delete <task_number>");
            return response.toString();
        }
        if (taskIndex <= 0 || taskIndex > taskList.size()) {
            response.append("Error: Invalid task index.");
        } else {
            Task task = taskList.removeTask(taskIndex - 1);
            response.append("Noted. I've removed this task:\n").append(task).append("\nNow you have ")
                    .append(taskList.size()).append(" tasks in the list.");

            try {
                storage.saveTasks(taskList.getTasks());
            } catch (Exception e) {
                response.append("Error: Failed to save tasks: ").append(e.getMessage());
            }
        }
        return response.toString();
    }
}
