package tracker;

/**
 * Handles the addition of a new deadline task.
 * Parses the user input, validates the format, and adds the task to the task list.
 */
public class AddDeadlineCommand extends Command {
    private String input;

    /**
     * Constructs an AddDeadlineCommand with the specified input.
     *
     * @param input The user input containing the deadline task details.
     */
    public AddDeadlineCommand(String input) {
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
    public boolean execute(TaskList taskList, Ui ui, Storage storage) {
        String[] parts = input.substring(8).split(" /by ");
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            ui.error("Invalid deadline format. Use: deadline <description> /by <yyyy-MM-dd HHmm>");
            return true;
        }

        try {
            Task task = new Deadline(parts[0].trim(), parts[1].trim());
            taskList.addTask(task);
            ui.message("     Got it. I've added this task:\n       "
                    + task + "\n     Now you have " + taskList.size() + " tasks in the list.");
            storage.saveTasks(taskList.getTasks());
        } catch (IllegalArgumentException e) {
            ui.error(e.getMessage());
        } catch (Exception e) {
            ui.error("Failed to save tasks: " + e.getMessage());
        }

        return true;
    }
}
