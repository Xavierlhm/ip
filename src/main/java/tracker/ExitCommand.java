package tracker;

/**
 * Filters the exit command and exits.
 */
public class ExitCommand extends Command {
    /**
     * @param taskList
     * @param ui
     * @param storage
     * @return
     */
    @Override
    public boolean execute(TaskList taskList, Ui ui, Storage storage) {
        ui.goodBye();
        return false; // Ends the program
    }
}
