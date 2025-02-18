package tracker;

/**
 * Handles the addition of a new to-do task.
 * Parses the user input, validates the format, and adds the task to the task list.
 */
public class AddTodoCommand extends Command {
    static final int SPLIT_INDEX = 4;
    private String input;
    private String description;

    /**
     * Constructs an AddTodoCommand with the specified input.
     *
     * @param input The user input containing the to-do task details.
     */
    public AddTodoCommand(String input) throws TrackerException {
        assert input != null && !input.isEmpty() : "Input cannot be null or empty";
        this.input = input;
        this.description = extractDescription();
    }

    /**
     * Extracts and validates the task description from the user input.
     *
     * @return The trimmed task description.
     * @throws TrackerException If the description is missing or empty.
     */
    private String extractDescription() throws TrackerException {
        String description = input.substring(SPLIT_INDEX).trim();
        assert description != null : "Description cannot be null";

        if (description.isEmpty()) {
            throw new TrackerException("Error: Invalid todo format. Use: todo <description>");
        }
        return description;
    }

    /**
     * @param taskList The task list to add the task to.
     * @return The task.
     */
    private Task createTodoTask(TaskList taskList) {
        Task task = new Todo(description);
        assert task != null : "Failed to create Todo task";
        taskList.addTask(task);
        return task;
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
        assert taskList != null : "TaskList cannot be null";
        assert ui != null : "Ui cannot be null";
        assert storage != null : "Storage cannot be null";

        StringBuilder response = new StringBuilder();

        Task task = createTodoTask(taskList);

        response.append("Got it. I've added this task:\n")
                .append(task).append("\nNow you have ")
                .append(taskList.size()).append(" tasks in the list.");

        try {
            storage.saveTasks(taskList.getTasks());
        } catch (Exception e) {
            response.append("Error: Failed to save the tasks ").append(e.getMessage());
        }
        return response.toString();
    }
}
