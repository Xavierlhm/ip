package tracker;

/**
 * Responsible for task related actions.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType taskType;

    /**
     * @param description
     * @param taskType
     */
    public Task(String description, TaskType taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    /**
     * @return
     */
    public String getStatus() {
        return (isDone ? "X" : " ");
    }

    /**
     *
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     *
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * @return
     */
    public abstract String saveFormat();

    /**
     * @param line
     * @return
     * @throws IllegalArgumentException
     */
    public static Task loadFormat(String line) throws IllegalArgumentException {
        String[] parts = line.split(" \\| ");
        TaskType taskType = TaskType.symbolValue(parts[0]);
        boolean isDone = parts[1].equals("X");
        String description = parts[2];

        switch (taskType) {
        case DEADLINE:
            Deadline deadlineTask = new Deadline(description, parts[3]);

            if (isDone) {
                deadlineTask.markAsDone();
            }

            return deadlineTask;
        case EVENT:
            Event eventTask = new Event(description, parts[3], parts[4]);

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
     * @return string output
     */
    @Override
    public String toString() {
        return "[" + taskType.getTaskSymbol() + "][" + getStatus() + "] " + description;
    }
}
