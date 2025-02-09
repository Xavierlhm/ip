package tracker;

/**
 * Filters tasks based on a search keyword and displays matching tasks.
 */
public class FindCommand extends Command {
    static final int SPLIT_INDEX = 2;
    static final int MAX_SIZE = 2;
    static final int SECOND_PART = 1;
    static final int EMPTY_INDEX = 0;
    static final int ONE_INDEX = 1;
    private String keyword;

    /**
     * @param input The user input containing the keyword.
     * @throws TrackerException If the input does not contain a keyword.
     */
    public FindCommand(String input) throws TrackerException {
        String[] parts = input.split(" ", SPLIT_INDEX);
        boolean isLessThanLimit = parts.length < MAX_SIZE;
        boolean isMoreThanLimit = parts.length >= MAX_SIZE;
        boolean isKeywordEmpty = isMoreThanLimit && parts[SECOND_PART].trim().isEmpty();
        boolean isValidCommand = isLessThanLimit || isKeywordEmpty;
        if (isValidCommand) {
            throw new TrackerException("Error: Find command must include a keyword. Use: find <keyword>");
        }
        this.keyword = parts[SECOND_PART].trim();
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
        String tasks = taskList.getTasks().stream()
                .filter(task -> task.description.contains(keyword))
                .map(task -> (taskList.getTasks().indexOf(task) + 1) + ". " + task)
                .reduce((a, b) -> a + "\n" + b)
                .orElse("No matching tasks found.");
        response.append("\n").append(tasks);
        return response.toString();
    }
}
