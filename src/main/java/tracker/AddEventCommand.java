package tracker;

/**
 * Handles the addition of a new event task.
 * Parses the user input, validates the format, and adds the task to the task list.
 */
public class AddEventCommand extends Command {
    private String input;

    /**
     * Constructs an AddEventCommand with the specified input.
     *
     * @param input The user input containing the event task details.
     */
    public AddEventCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the command to add an event task.
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
        String[] parts = input.substring(5).split(" /from ");
        if (parts.length < 2 || parts[0].trim().isEmpty()) {
            response.append("Error: Invalid event format. Use: event <description> /from <start> /to <end>");
            return response.toString();
        }
        String[] times = parts[1].split(" /to ");
        if (times.length < 2 || times[0].trim().isEmpty() || times[1].trim().isEmpty()) {
            response.append("Error: Invalid event format. Use: event <description> /from <start> /to <end>");
            return response.toString();
        }
        try {
            Task task = new Event(parts[0].trim(), times[0].trim(), times[1].trim());
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
