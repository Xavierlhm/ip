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
    public boolean execute(TaskList taskList, Ui ui, Storage storage) throws TrackerException {
        String description = input.substring(4).trim();

        if (description.isEmpty()) {
            throw new TrackerException("Description cannot be empty");
        }

        Task task = new Todo(description);
        taskList.addTask(task);
        ui.message("     Got it. I've added this task:\n       " + task +
                "\n     Now you have " + taskList.size() + " tasks in the list.");

        try {
            storage.saveTasks(taskList.getTasks());
        } catch (Exception e) {
            ui.error("Failed to save tasks: " + e.getMessage());
        }

        return true;
    }
}
