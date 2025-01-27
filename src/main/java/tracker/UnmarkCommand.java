package tracker;

/**
 * Handles the "unmark" command to mark a task as not done.
 */
public class UnmarkCommand extends Command {
    private int taskIndex;

    /**
     * Constructs an UnmarkCommand with the specified input.
     *
     * @param input The user input containing the task index to unmark.
     * @throws TrackerException If the input format is invalid.
     */
    public UnmarkCommand(String input) throws TrackerException {
        try {
            this.taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        } catch (Exception e) {
            throw new TrackerException("Invalid unmark command. Use: unmark <task number>");
        }
    }

    /**
     * Executes the command to mark a task as not done.
     *
     * @param taskList The task list containing the task to be unmarked.
     * @param ui       The user interface to display messages.
     * @param storage  The storage to save the updated task list.
     * @return true to continue program execution.
     * @throws TrackerException If the task index is invalid.
     */
    @Override
    public boolean execute(TaskList taskList, Ui ui, Storage storage) throws TrackerException {
        Task task = taskList.getTask(taskIndex);
        task.unmarkAsDone();
        ui.message("    OK, I've marked this task as not done yet:\n      " + task);

        try {
            storage.saveTasks(taskList.getTasks());
        } catch (Exception e) {
            ui.error("Failed to save tasks: " + e.getMessage());
        }

        return true;
    }
}
