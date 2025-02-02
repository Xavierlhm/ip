package tracker;

/**
 * Filters tasks based on a search keyword and displays matching tasks.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * @param input The user input containing the keyword.
     * @throws TrackerException If the input does not contain a keyword.
     */
    public FindCommand(String input) throws TrackerException {
        String[] parts = input.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new TrackerException("Error: Find command must include a keyword. Use: find <keyword>");
        }
        this.keyword = parts[1].trim();
    }

    /**
     * Executes the find command to search for tasks containing the keyword.
     *
     * @param taskList The task list to search.
     * @param ui       The UI for user interaction.
     * @param storage  The storage object (not used in this command).
     * @return Always returns true to continue the program.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        StringBuilder response = new StringBuilder("Here are the matching tasks in your list:");
        boolean found = false;
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.getTasks().get(i);
            if (task.description.contains(keyword)) {
                response.append("\n").append(i + 1).append(". ").append(task);
                found = true;
            }
        }
        if (!found) {
            response.append("No matching tasks found.");
        }
        return response.toString();
    }
}
