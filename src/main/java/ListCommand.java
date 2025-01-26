public class ListCommand extends Command {
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
