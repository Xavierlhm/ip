package tracker;

import java.util.stream.Collectors;

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
        if (!validateKeyword(parts)) {
            throw new TrackerException("Error: Find command must include a keyword. Use: find <keyword>");
        }
        this.keyword = parts[SECOND_PART].trim();
    }

    /**
     * @param parts The input.
     * @return If the input is valid.
     */
    private boolean validateKeyword(String[] parts) {
        boolean isMoreThanLimit = parts.length >= MAX_SIZE;
        boolean isKeywordEmpty = isMoreThanLimit && !parts[SECOND_PART].trim().isEmpty();
        return isMoreThanLimit && isKeywordEmpty;
    }

    /**
     * @param taskList The task list to search.
     * @return The matching tasks.
     */
    private String searchTasks(TaskList taskList) {
        String result = taskList.getTasks().stream()
                .filter(task -> task.description.contains(keyword))
                .map(task -> (taskList.getTasks().indexOf(task) + 1) + ". " + task)
                .collect(Collectors.joining("\n"));

        return result.isEmpty() ? "No matching tasks found." : "Here are the matching tasks in your list:\n" + result;
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
        return searchTasks(taskList);
    }
}
