package tracker;

/**
 * Handles the addition of a new to-do task.
 * Parses the user input, validates the format, and adds the task to the task list.
 */
public class AddTodoCommand extends Command {
    private String input;

    /**
     * Constructs an AddTodoCommand with the specified input.
     *
     * @param input The user input containing the to-do task details.
     */
    public AddTodoCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the command to add a to-do task.
     * Validates the input format and adds the task to the task list.
     *
     * @param taskList The task list to add the task to.
     * @param ui       The UI to display messages to the user.
     * @param storage  The storage to save the updated task list.
     * @return true to continue program execution.
     * @throws TrackerException If the input format is invalid.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws TrackerException {
        StringBuilder response = new StringBuilder();
        String description = input.substring(4).trim();

        if (description.isEmpty()) {
            throw new TrackerException("Error: Invalid todo format. Use: todo <description>");
        }

        Task task = new Todo(description);
        taskList.addTask(task);

        response.append("Got it. I've added this task:\n").append(task).append("\nNow you have ")
                .append(taskList.size()).append(" tasks in the list.");

        try {
            storage.saveTasks(taskList.getTasks());
        } catch (Exception e) {
            response.append("Error: Failed to save the tasks ").append(e.getMessage());
        }
        return response.toString();
    }
}
