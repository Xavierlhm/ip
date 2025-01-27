package tracker;

/**
 * Enum for the different task types.
 */
public enum TaskType {
    TODO("T"),
    DEADLINE("D"),
    EVENT("E");

    private final String taskSymbol;

    /**
     * @param taskSymbol
     */
    TaskType(String taskSymbol) {
        this.taskSymbol = taskSymbol;
    }

    /**
     * @return task symbol
     */
    public String getTaskSymbol() {
        return taskSymbol;
    }

    /**
     * @param symbol
     * @return
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
