public abstract class Command {
    public abstract boolean execute(TaskList taskList, Ui ui, Storage storage) throws TrackerException;
}
