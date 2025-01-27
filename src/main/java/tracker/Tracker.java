package tracker;

/**
 * The main program that implements a task tracker application.
 */
public class Tracker {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

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
            ui.loadError();
            taskList = new TaskList();
        }
    }

    /**
     * Starts the task tracker application.
     */
    public void run() {
        ui.greet();
        boolean isRunning = true;

        while (isRunning) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                isRunning = command.execute(taskList, ui, storage);
            } catch (TrackerException e) {
                ui.error(e.getMessage());
            }
        }

        ui.goodBye();
    }

    /**
     * The main method to launch the tracker application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Tracker(System.getProperty("user.home") + "/Documents/tracker.Tracker.txt").run();
    }
}
