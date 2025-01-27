package tracker;

/**
 * Handles the "list" command to display all tasks in the tracker.
 */
public class ListCommand extends Command {
    /**
     * Executes the command to list all tasks in the task list.
     *
     * @param taskList The task list containing the tasks to be displayed.
     * @param ui       The user interface to display the tasks.
     * @param storage  The storage (not used in this command).
     * @return true to continue program execution.
     */
    @Override
    public boolean execute(TaskList taskList, Ui ui, Storage storage) {
        if (taskList.size() == 0) {
            ui.message("    Your to-do list is currently empty.");
        } else {
            StringBuilder message = new StringBuilder("    Here are the tasks in your list:");

            for (int i = 0; i < taskList.size(); i++) {
                message.append("\n    ").append(i + 1).append(". ").append(taskList.getTasks().get(i));
            }
            ui.message(message.toString());
        }
        return true;
    }
}
