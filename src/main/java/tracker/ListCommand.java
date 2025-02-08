package tracker;

/**
 * Handles the "list" command to display all tasks in the tracker.
 */
public class ListCommand extends Command {
    static final int EMPTY_INDEX = 0;
    static final int ONE_INDEX = 1;
    /**
     * Executes the command to list all tasks in the task list.
     *
     * @param taskList The task list containing the tasks to be displayed.
     * @param ui       The user interface to display the tasks.
     * @param storage  The storage (not used in this command).
     * @return true to continue program execution.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {

        StringBuilder response = new StringBuilder();

        boolean isEmpty = taskList.size() == EMPTY_INDEX;
        if (isEmpty) {
            response.append("Your to-do list is currently empty");
        } else {
            response.append("Here are the tasks in your list:");

            for (int i = EMPTY_INDEX; i < taskList.size(); i++) {
                response.append("\n").append(i + ONE_INDEX).append(". ").append(taskList.getTasks().get(i));
            }
        }
        return response.toString();

    }
}
