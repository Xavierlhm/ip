package tracker;

/**
 * The main program that implements a task tracker application.
 */
public class Tracker {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private String commandType;

    /**
     * Constructs a Tracker instance with the specified file path for storage.
     *
     * @param filePath The file path where tasks will be stored.
     */
    public Tracker(String filePath) {
        assert filePath != null && !filePath.isEmpty() : "File path cannot be null or empty";
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            taskList = new TaskList(storage.loadTasks());
            assert taskList != null : "TaskList failed to load";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ui.loadError();
            taskList = new TaskList();
        }
    }

    /**
     * Retrieves the command type.
     *
     * @return The command type.
     */
    public String getCommandType() {
        return commandType;
    }
    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) throws TrackerException {
        assert input != null && !input.isEmpty() : "Input cannot be null or empty";
        try {
            Command command = Parser.parse(input);
            assert command != null : "Parsed command should not be null";
            return command.execute(taskList, ui, storage);
        } catch (TrackerException e) {
            return e.getMessage();
        }
    }
}
