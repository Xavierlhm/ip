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
        validateDescription(description);
        validateTaskType(taskType);
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    /**
     * Validates that the task description is not null or empty.
     *
     * @param description The description to validate.
     */
    private void validateDescription(String description) {
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Task description must not be null or empty");
        }
    }

    /**
     * Validates that the task type is not null.
     *
     * @param taskType The task type to validate.
     */
    private void validateTaskType(TaskType taskType) {
        if (taskType == null) {
            throw new IllegalArgumentException("TaskType must not be null");
        }
    }

    /**
     * Gets the status of the task as a string.
     *
     * @return "X" if the task is done, otherwise " ".
     */
    public String getStatus() {
        return isDone ? "X" : " ";
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
        validateLoadFormat(line);
        String[] parts = line.split(" \\| ");
        TaskType taskType = TaskType.symbolValue(parts[EMPTY_INDEX]);
        boolean isDone = parts[ONE_INDEX].equals("X");
        String description = parts[TWO_INDEX];

        return createTaskFromFormat(taskType, isDone, parts, description);
    }

    /**
     * Validates the format of the loaded task.
     *
     * @param line The string containing the task details.
     */
    private static void validateLoadFormat(String line) {
        if (line == null || line.isEmpty()) {
            throw new IllegalArgumentException("Saved task line must not be null or empty");
        }
    }

    /**
     * Creates a Task object from a given formatted string.
     *
     * @param taskType    The type of the task.
     * @param isDone      Whether the task is marked as done.
     * @param parts       The split parts of the formatted string.
     * @param description The task description.
     * @return A newly created Task object.
     */
    private static Task createTaskFromFormat(TaskType taskType, boolean isDone, String[] parts, String description) {
        Task task;
        switch (taskType) {
        case DEADLINE:
            validateDeadlineFormat(parts);
            task = new Deadline(description, extractDeadlineDate(parts));
            break;
        case EVENT:
            validateEventFormat(parts);
            task = new Event(description, extractEventStart(parts), extractEventEnd(parts));
            break;
        case TODO:
        default:
            task = new Todo(description);
        }

        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Ensures that a Deadline task format is correct.
     *
     * @param parts The parts of the task string.
     */
    private static void validateDeadlineFormat(String[] parts) {
        if (parts.length < FOUR_INDEX) {
            throw new IllegalArgumentException("Deadline task must have a deadline date");
        }
    }

    /**
     * Extracts the deadline date from the task format.
     *
     * @param parts The parts of the task string.
     * @return The formatted deadline date.
     */
    private static String extractDeadlineDate(String[] parts) {
        return parts[THREE_INDEX].substring(FOUR_INDEX);
    }

    /**
     * Ensures that an Event task format is correct.
     *
     * @param parts The parts of the task string.
     */
    private static void validateEventFormat(String[] parts) {
        if (parts.length < FIVE_INDEX) {
            throw new IllegalArgumentException("Event task must have a start and end date");
        }
    }

    /**
     * Extracts the event start time from the task format.
     *
     * @param parts The parts of the task string.
     * @return The formatted event start time.
     */
    private static String extractEventStart(String[] parts) {
        return parts[THREE_INDEX].substring(SIX_INDEX);
    }

    /**
     * Extracts the event end time from the task format.
     *
     * @param parts The parts of the task string.
     * @return The formatted event end time.
     */
    private static String extractEventEnd(String[] parts) {
        return parts[FOUR_INDEX].substring(FOUR_INDEX);
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
