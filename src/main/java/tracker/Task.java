package tracker;

/**
 * Represents a generic task with a description and a completion status.
 */
public abstract class Task {
    static final int EMPTY_INDEX = 0;
    static final int ONE_INDEX = 1;
    static final int TWO_INDEX = 2;
    static final int THREE_INDEX = 3;
    static final int FOUR_INDEX = 4;
    static final int FIVE_INDEX = 5;
    static final int SIX_INDEX = 6;
    protected String description;
    protected boolean isDone;
    protected TaskType taskType;

    /**
     * Constructs a Task with the specified description and type.
     *
     * @param description The description of the task.
     * @param taskType    The type of the task.
     */
    public Task(String description, TaskType taskType) {
        assert description != null && !description.isEmpty() : "Task description must not be null or empty";
        assert taskType != null : "TaskType must not be null";
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    /**
     * Gets the status of the task as a string.
     *
     * @return "X" if the task is done, otherwise " ".
     */
    public String getStatus() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Returns a formatted string for saving the task to a file.
     *
     * @return The string representation of the task for storage.
     */
    public abstract String saveFormat();

    /**
     * Loads a task from a formatted string.
     *
     * @param line The formatted string representing a task.
     * @return The Task object created from the string.
     * @throws IllegalArgumentException If the string format is invalid.
     */
    public static Task loadFormat(String line) throws IllegalArgumentException {
        assert line != null && !line.isEmpty() : "Saved task line must not be null or empty";
        String[] parts = line.split(" \\| ");
        assert parts.length >= THREE_INDEX : "Invalid task format. Expected at least 3 parts";
        TaskType taskType = TaskType.symbolValue(parts[EMPTY_INDEX]);
        boolean isDone = parts[ONE_INDEX].equals("X");
        String description = parts[TWO_INDEX];

        switch (taskType) {
        case DEADLINE:
            assert parts.length >= FOUR_INDEX : "Deadline task must have a deadline date";
            Deadline deadlineTask = new Deadline(description, parts[THREE_INDEX].substring(FOUR_INDEX));

            if (isDone) {
                deadlineTask.markAsDone();
            }

            return deadlineTask;
        case EVENT:
            assert parts.length >= FIVE_INDEX : "Event task must have a start and end date";
            Event eventTask = new Event(description, parts[THREE_INDEX].substring(SIX_INDEX),
                    parts[FOUR_INDEX].substring(FOUR_INDEX));

            if (isDone) {
                eventTask.markAsDone();
            }

            return eventTask;
        case TODO:
        default:
            Todo todoTask = new Todo(description);

            if (isDone) {
                todoTask.markAsDone();
            }

            return todoTask;
        }
    }

    /**
     * Returns a string representation of the task.
     *
     * @return The formatted string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + taskType.getTaskSymbol() + "][" + getStatus() + "] " + description;
    }
}
