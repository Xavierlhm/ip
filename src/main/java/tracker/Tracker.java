package tracker;

/**
 * The main program implements an application that helps the user keep track of tasks.
 */
public class Tracker {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    /**
     * @param filePath
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
     *
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
     * @param args
     */
    public static void main(String[] args) {
        new Tracker(System.getProperty("user.home") + "/Documents/tracker.Tracker.txt").run();
    }
}
