package tracker;

/**
 * Filters the mark command and attempts to mark the task if there is no error.
 */
public class MarkCommand extends Command {
    private int taskIndex;

    /**
     * @param input
     * @throws TrackerException
     */
    public MarkCommand(String input) throws TrackerException {
        try {
            this.taskIndex = Integer.parseInt(input.split(" ")[1]) -1;
        } catch (Exception e) {
            throw new TrackerException("Invalid mark command. User mark <task number>");
        }
    }

    /**
     * @param taskList
     * @param ui
     * @param storage
     * @return
     * @throws TrackerException
     */
    @Override
    public boolean execute(TaskList taskList, Ui ui, Storage storage) throws TrackerException {
        Task task = taskList.getTask(taskIndex);
        task.markAsDone();
        ui.message("    Nice! I've marked this task as done:\n      " + task);

        try {
            storage.saveTasks(taskList.getTasks());
        } catch (Exception e) {
            ui.error("Failed to save tasks: " + e.getMessage());
        }

        return true;
    }
}
