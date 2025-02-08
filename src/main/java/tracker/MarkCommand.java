package tracker;

/**
 * Handles the "mark" command to mark a task as done.
 */
public class MarkCommand extends Command {
    static final int SPLIT_INDEX = 1;
    static final int EMPTY_INDEX = 0;
    static final int ONE_INDEX = 1;
    private String input;
    private int taskIndex;

    /**
     * Constructs a MarkCommand with the specified input.
     *
     * @param input The user input containing the task index to mark.
     * @throws TrackerException If the input format is invalid.
     */
    public MarkCommand(String input) throws TrackerException {
        this.input = input;
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
    public String execute(TaskList taskList, Ui ui, Storage storage) throws TrackerException {
        StringBuilder response = new StringBuilder();
        try {
            this.taskIndex = Integer.parseInt(input.split(" ")[SPLIT_INDEX]);
        } catch (Exception e) {
            response.append("Error: Invalid mark command. Use: mark <task_number>");
            return response.toString();
        }
        boolean isWithinSize = taskIndex <= EMPTY_INDEX;
        boolean isMoreThanSize = taskIndex > taskList.size();
        boolean isValidIndex = isWithinSize || isMoreThanSize;
        if (isValidIndex) {
            response.append("Error: Invalid task index.");
        } else {
            Task task = taskList.getTask(taskIndex - ONE_INDEX);
            task.markAsDone();
            response.append("Nice! I've marked this task as done:\n").append(task);

            try {
                storage.saveTasks(taskList.getTasks());
            } catch (Exception e) {
                response.append("Error: Failed to save tasks: ").append(e.getMessage());
            }
        }
        return response.toString();
    }
}
