package tracker;

/**
 * Handles the "unmark" command to mark a task as not done.
 */
public class UnmarkCommand extends Command {
    static final int SPLIT_INDEX = 1;
    static final int EMPTY_INDEX = 0;
    static final int ONE_INDEX = 1;
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
        this.taskIndex = extractTaskIndex();
    }

    /**
     * Extracts and validates the task index from the user input.
     *
     * @return The validated task index.
     * @throws TrackerException If the index is invalid.
     */
    private int extractTaskIndex() throws TrackerException {
        try {
            int index = Integer.parseInt(input.split(" ")[SPLIT_INDEX]);
            boolean isWithinSize = index <= EMPTY_INDEX;
            if (isWithinSize) {
                throw new TrackerException("Error: Task index must be greater than zero.");
            }
            return index;
        } catch (Exception e) {
            throw new TrackerException("Error: Invalid unmark command. Use: unmark <task_number>");
        }
    }

    /**
     * Retrieves the task to be unmarked.
     *
     * @param taskList The task list containing the task.
     * @return The task to be unmarked.
     * @throws TrackerException If the index is out of bounds.
     */
    private Task retrieveTask(TaskList taskList) throws TrackerException {
        boolean isMoreThanSize = taskIndex > taskList.size();
        if (isMoreThanSize) {
            throw new TrackerException("Error: Task index is out of bounds.");
        }
        return taskList.getTask(taskIndex - ONE_INDEX);
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
        assert taskList != null : "TaskList cannot be null";
        assert ui != null : "Ui cannot be null";
        assert storage != null : "Storage cannot be null";

        StringBuilder response = new StringBuilder();
        Task task = retrieveTask(taskList);

        task.unmarkAsDone();
        response.append("OK, I've marked this task as not done yet:\n").append(task);

        try {
            storage.saveTasks(taskList.getTasks());
        } catch (Exception e) {
            response.append("Error: Failed to save tasks: ").append(e.getMessage());
        }

        return response.toString();
    }
}
