package tracker;

public class AddTodoCommand extends Command {
    private String input;

    public AddTodoCommand(String input) {
        this.input = input;
    }

    @Override
    public boolean execute(TaskList taskList, Ui ui, Storage storage) throws TrackerException {
        String description = input.substring(4).trim();

        if (description.isEmpty()) {
            throw new TrackerException("Description cannot be empty");
        }

        Task task = new Todo(description);
        taskList.addTask(task);
        ui.message("     Got it. I've added this task:\n       " + task +
                "\n     Now you have " + taskList.size() + " tasks in the list.");

        try {
            storage.saveTasks(taskList.getTasks());
        } catch (Exception e) {
            ui.error("Failed to save tasks: " + e.getMessage());
        }

        return true;
    }
}
