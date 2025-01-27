package tracker;

/**
 * An abstract class for the three different task types.
 */
public abstract class Command {
    public abstract boolean execute(TaskList taskList, Ui ui, Storage storage) throws TrackerException;
}
