package tracker;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Handles the saving and loading of tasks to and from a file.
 */
public class Storage {
    private Path filePath;

    /**
     * Constructs a Storage instance with the specified file path.
     *
     * @param filePath The path to the file where tasks will be stored.
     */
    public Storage(String filePath) {
        assert filePath != null && !filePath.isEmpty() : "File path cannot be null or empty";
        this.filePath = Paths.get(filePath);
    }

    /**
     * Ensures that the directory for the file exists, creating it if necessary / does not exist.
     *
     * @throws IOException If an error occurs while creating the directory.
     */
    private void checkDirectoryExists() throws IOException {
        Path directory = filePath.getParent();

        if (!Files.exists(directory)) {
            Files.createDirectories(directory);
        }
    }

    /**
     * Loads tasks from the file.
     *
     * @return A list of tasks loaded from the file.
     * @throws IOException If an error occurs while reading the file.
     */
    public ArrayList<Task> loadTasks() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();

        if (!Files.exists(filePath)) {
            return tasks;
        }
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;

            while ((line = reader.readLine()) != null) {
                assert line != null && !line.isEmpty() : "Empty or null line in file";
                tasks.add(Task.loadFormat(line));
            }
        }

        return tasks;
    }

    /**
     * Saves tasks to the file.
     *
     * @param tasks The list of tasks to be saved.
     * @throws IOException If an error occurs while writing to the file.
     */
    public void saveTasks(ArrayList<Task> tasks) throws IOException {
        assert tasks != null : "Tasks list cannot be null before saving";
        checkDirectoryExists();

        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            for (Task task : tasks) {
                assert task != null : "Task to be saved is null";
                writer.write(task.saveFormat());
                writer.newLine();
            }
        }
    }
}
