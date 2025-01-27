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
    public boolean execute(TaskList taskList, Ui ui, Storage storage) {
        String[] parts = input.substring(5).split(" /from ");
        if (parts.length < 2 || parts[0].trim().isEmpty()) {
            ui.error("Invalid event format. Use: event <description> /from <start> /to <end>");
            return true;
        }

        String[] times = parts[1].split(" /to ");
        if (times.length < 2 || times[0].trim().isEmpty() || times[1].trim().isEmpty()) {
            ui.error("Invalid event format. Use: event <description> /from <start> /to <end>");
            return true;
        }

        try {
            Task task = new Event(parts[0].trim(), times[0].trim(), times[1].trim());
            taskList.addTask(task);
            ui.message("     Got it. I've added this task:\n       " + task +
                    "\n     Now you have " + taskList.size() + " tasks in the list.");
            storage.saveTasks(taskList.getTasks());
        } catch (IllegalArgumentException e) {
            ui.error(e.getMessage());
        } catch (Exception e) {
            ui.error("Failed to save tasks: " + e.getMessage());
        }

        return true;
    }
}
