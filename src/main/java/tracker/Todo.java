package tracker;

/**
 * Represents a to-do task.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo task with the specified description.
     *
     * @param description The description of the to-do task.
     */
    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    /**
     * Returns a formatted string for saving the task to a file.
     *
     * @return The string representation of the task for storage.
     */
    @Override
    public String saveFormat() {
        return taskType.getTaskSymbol() + " | " + getStatus() + " | " + description;
    }

    /**
     * Returns a string representation of the to do task.
     *
     * @return The formatted string representation of the to do task.
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
