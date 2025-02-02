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
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            taskList = new TaskList(storage.loadTasks());
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
        try {
            Command command = Parser.parse(input);
            return command.execute(taskList, ui, storage);
        } catch (TrackerException e) {
            return e.getMessage();
        }
    }
}
