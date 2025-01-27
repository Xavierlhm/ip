package tracker;

/**
 * Represents the type of a task.
 */
public enum TaskType {
    TODO("T"),
    DEADLINE("D"),
    EVENT("E");

    private final String taskSymbol;

    /**
     * Constructs a TaskType with the specified symbol.
     *
     * @param taskSymbol The symbol representing the task type.
     */
    TaskType(String taskSymbol) {
        this.taskSymbol = taskSymbol;
    }

    /**
     * Retrieves the symbol for the task type.
     *
     * @return The symbol of the task type.
     */
    public String getTaskSymbol() {
        return taskSymbol;
    }

    /**
     * Retrieves the TaskType for the specified symbol.
     *
     * @param symbol The symbol representing the task type.
     * @return The TaskType corresponding to the symbol.
     * @throws IllegalArgumentException If the symbol is invalid.
     */
    public static TaskType symbolValue(String symbol) {
        for (TaskType type : values()) {
            if (type.taskSymbol.equals(symbol)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid tracker.TaskType symbol: " + symbol);
    }
}
