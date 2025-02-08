package tracker;

/**
 * Handles the "help" command to display available commands and their usage.
 */
public class HelpCommand extends Command {
    /**
     * @param taskList The list of tasks to be manipulated.
     * @param ui       The user interface to display messages.
     * @param storage  The storage to save or load tasks.
     * @return The help message as a string.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        StringBuilder response = new StringBuilder("Here are the available commands:\n\n");

        response.append("1. **todo <description>** - Adds a to-do task.\n")
                .append("2. **deadline <description> /by <yyyy-MM-dd HHmm>** - Adds a deadline task.\n")
                .append("3. **event <description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>** - Adds an event.\n")
                .append("4. **list** - Displays all tasks.\n")
                .append("5. **mark <task_number>** - Marks a task as done.\n")
                .append("6. **unmark <task_number>** - Marks a task as not done.\n")
                .append("7. **delete <task_number>** - Deletes a task.\n")
                .append("8. **find <keyword>** - Finds tasks containing the keyword.\n")
                .append("9. **bye** - Exits the application.\n")
                .append("10. **help** - Displays this help page.\n");

        return response.toString();
    }
}
