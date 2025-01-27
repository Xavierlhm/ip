package tracker;

/**
 * Handles the "mark" command to mark a task as done.
 */
public class MarkCommand extends Command {
    private int taskIndex;

    /**
     * Constructs a MarkCommand with the specified input.
     *
     * @param input The user input containing the task index to mark.
     * @throws TrackerException If the input format is invalid.
     */
    public MarkCommand(String input) throws TrackerException {
        try {
            this.taskIndex = Integer.parseInt(input.split(" ")[1]) -1;
        } catch (Exception e) {
            throw new TrackerException("Invalid mark command. User mark <task number>");
        }
    }

    /**
     * Executes the command to mark a task as done.
     *
     * @param taskList The task list containing the task to be marked.
     * @param ui       The user interface to display messages.
     * @param storage  The storage to save the updated task list.
     * @return true to continue program execution.
     * @throws TrackerException If the task index is invalid.
     */
    @Override
    public boolean execute(TaskList taskList, Ui ui, Storage storage) throws TrackerException {
        Task task = taskList.getTask(taskIndex);
        task.markAsDone();
        ui.message("    Nice! I've marked this task as done:\n      " + task);

        try {
            storage.saveTasks(taskList.getTasks());
        } catch (Exception e) {
            ui.error("Failed to save tasks: " + e.getMessage());
        }

        return true;
    }
}
