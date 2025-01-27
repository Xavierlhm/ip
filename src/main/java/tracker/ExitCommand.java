package tracker;

/**
 * Handles the "bye" command to exit the tracker.
 */
public class ExitCommand extends Command {
    /**
     * Executes the command to display a goodbye message and terminate the program.
     *
     * @param taskList The task list (not used in this command).
     * @param ui       The user interface to display the goodbye message.
     * @param storage  The storage (not used in this command).
     * @return false to signal the program should stop running.
     */
    @Override
    public boolean execute(TaskList taskList, Ui ui, Storage storage) {
        ui.goodBye();
        return false; // Ends the program
    }
}
