package tracker;

/**
 * Handles the deletion of a task from the task list.
 * Parses the user input, validates the task index, and removes the task.
 */
public class DeleteCommand extends Command {
    private int taskIndex;

    /**
     * Constructs a DeleteCommand with the specified input.
     *
     * @param input The user input containing the task index to delete.
     * @throws TrackerException If the input format is invalid.
     */
    public DeleteCommand(String input) throws TrackerException {
        try {
            this.taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        } catch (Exception e) {
            throw new TrackerException("Invalid delete command. Use: delete <task_number>");
        }
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
    public boolean execute(TaskList taskList, Ui ui, Storage storage) throws TrackerException {
        Task task = taskList.removeTask(taskIndex);
        ui.message("    Noted. I've removed this task:\n      "
                + task + "\n     Now you have " + taskList.size() + " tasks in the list.");

        try {
            storage.saveTasks(taskList.getTasks());
        } catch (Exception e) {
            ui.error("Failed to save tasks: " + e.getMessage());
        }

        return true;
    }
}
