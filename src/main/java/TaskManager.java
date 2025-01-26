import java.io.*;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;

public class TaskManager {
    private static final Path FILE_PATH = Paths.get(
            System.getProperty("user.home"), "Documents", "Tracker.txt"
    );

    private static void checkDirectoryExists() throws IOException {
        Path directory = FILE_PATH.getParent();
        
        if (!Files.exists(directory)) {
            Files.createDirectories(directory);
        }
    }

    private static String saveFormat(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append(task.taskType.getTaskSymbol()).append(" | ");
        sb.append(task.isDone ? "1" : "0").append(" | ");
        sb.append(task.description);

        if (task instanceof Deadline) {
            sb.append(" | ").append(((Deadline) task).by);
        } else if (task instanceof Event) {
            sb.append(" | ").append(((Event) task).from).append(" | ").append(((Event) task).to);
        }

        return sb.toString();
    }

    public static void saveTasks(ArrayList<Task> tasks) throws IOException {
        checkDirectoryExists();

        try (BufferedWriter writer = Files.newBufferedWriter(FILE_PATH)) {
            for (Task task : tasks) {
                writer.write(saveFormat(task));
                writer.newLine();
            }
        }
    }

    private static Task loadFormat(String line) {
        String[] parts = line.split(" \\| ");
        TaskType type = TaskType.symbolValue(parts[0]);
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (type) {
        case DEADLINE:
            Task deadlineTask = new Deadline(description, parts[3]);
            if (isDone) {deadlineTask.markAsDone();}
            return deadlineTask;
        case EVENT:
            Task eventTask = new Event(description, parts[3], parts[4]);
            if (isDone) {eventTask.markAsDone();}
            return eventTask;
        case TODO:
        default:
            Task todoTask = new Todo(description);
            if (isDone) {todoTask.markAsDone();}
            return todoTask;
        }
    }

    public static ArrayList<Task> loadTasks() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        if (!Files.exists(FILE_PATH)) {
            return tasks;
        }

        try (BufferedReader reader = Files.newBufferedReader(FILE_PATH)) {
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(loadFormat(line));
            }
        }

        return tasks;
    }
}
