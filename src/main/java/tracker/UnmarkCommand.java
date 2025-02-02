package tracker;

/**
 * Handles the "unmark" command to mark a task as not done.
 */
public class UnmarkCommand extends Command {
    private String input;
    private int taskIndex;

    /**
     * Constructs an UnmarkCommand with the specified input.
     *
     * @param input The user input containing the task index to unmark.
     * @throws TrackerException If the input format is invalid.
     */
    public UnmarkCommand(String input) throws TrackerException {
        this.input = input;
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
    public String execute(TaskList taskList, Ui ui, Storage storage) throws TrackerException {
        StringBuilder response = new StringBuilder();
        try {
            this.taskIndex = Integer.parseInt(input.split(" ")[1]);
        } catch (Exception e) {
            response.append("Error: Invalid unmark command. Use: unmark <task_number>");
            return response.toString();
        }
        if (taskIndex <= 0 || taskIndex > taskList.size()) {
            response.append("Error: Invalid task index.");
        } else {
            Task task = taskList.getTask(taskIndex - 1);
            task.unmarkAsDone();
            response.append("OK, I've marked this task as not done yet:\n").append(task);

            try {
                storage.saveTasks(taskList.getTasks());
            } catch (Exception e) {
                response.append("Error: Failed to save tasks: ").append(e.getMessage());
            }
        }
        return response.toString();
    }
}
