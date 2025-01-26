public class AddDeadlineCommand extends Command {
    private String input;

    public AddDeadlineCommand(String input) {
        this.input = input;
    }

    @Override
    public boolean execute(TaskList taskList, Ui ui, Storage storage) {
        String[] parts = input.substring(8).split(" /by ");
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            ui.error("Invalid deadline format. Use: deadline <description> /by <yyyy-MM-dd HHmm>");
            return true;
        }

        try {
            Task task = new Deadline(parts[0].trim(), parts[1].trim());
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
