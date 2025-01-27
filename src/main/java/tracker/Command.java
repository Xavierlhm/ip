package tracker;

/**
 * Represents an abstract command to be executed by the tracker.
 * Subclasses implement the specific behavior for each type of command.
 */
public abstract class Command {
    /**
     * Executes the command using the provided task list, UI, and storage.
     *
     * @param taskList The list of tasks to be manipulated.
     * @param ui       The user interface to display messages.
     * @param storage  The storage to save or load tasks.
     * @return true if the program should continue running, false otherwise.
     * @throws TrackerException If an error occurs during execution.
     */
    public abstract boolean execute(TaskList taskList, Ui ui, Storage storage) throws TrackerException;
}
