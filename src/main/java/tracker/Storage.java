package tracker;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {
    private Path filePath;

    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    private void checkDirectoryExists() throws IOException {
        Path directory = filePath.getParent();
        if (!Files.exists(directory)) {
            Files.createDirectories(directory);
        }
    }

    public ArrayList<Task> loadTasks() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        if (!Files.exists(filePath)) {
            return tasks;
        }

        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(Task.loadFormat(line));
            }
        }

        return tasks;
    }

    public void saveTasks(ArrayList<Task> tasks) throws IOException {
        checkDirectoryExists();
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            for (Task task : tasks) {
                writer.write(task.saveFormat());
                writer.newLine();
            }
        }
    }
}
