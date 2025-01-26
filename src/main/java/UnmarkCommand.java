public class UnmarkCommand extends Command {
    private int taskIndex;

    public UnmarkCommand(String input) throws TrackerException {
        try {
            this.taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        } catch (Exception e) {
            throw new TrackerException("Invalid unmark command. Use: unmark <task number>");
        }
    }

    @Override
    public boolean execute(TaskList taskList, Ui ui, Storage storage) throws TrackerException {
        Task task = taskList.getTask(taskIndex);
        task.unmarkAsDone();
        ui.message("    OK, I've marked this task as not done yet:\n      " + task);

        try {
            storage.saveTasks(taskList.getTasks());
        } catch (Exception e) {
            ui.error("Failed to save tasks: " + e.getMessage());
        }

        return true;
    }
}
