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
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        StringBuilder response = new StringBuilder();
        String[] parts = input.substring(8).split(" /by ");
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            response.append("Error: Invalid deadline format. Use: deadline <description> /by <yyyy-MM-dd HHmm>");
            return response.toString();
        }
        try {
            Task task = new Deadline(parts[0].trim(), parts[1].trim());
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
