public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(String input) throws TrackerException {
        try {
            this.taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        } catch (Exception e) {
            throw new TrackerException("Invalid delete command. Use: delete <task_number>");
        }
    }

    @Override
    public boolean execute(TaskList taskList, Ui ui, Storage storage) throws TrackerException {
        Task task = taskList.removeTask(taskIndex);
        ui.message("    Noted. I've removed this task:\n      " + task +
                "\n     Now you have " + taskList.size() + " tasks in the list.");

        try {
            storage.saveTasks(taskList.getTasks());
        } catch (Exception e) {
            ui.error("Failed to save tasks: " + e.getMessage());
        }

        return true;
    }
}
