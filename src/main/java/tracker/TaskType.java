package tracker;

public enum TaskType {
    TODO("T"),
    DEADLINE("D"),
    EVENT("E");

    private final String taskSymbol;

    TaskType(String taskSymbol) {
        this.taskSymbol = taskSymbol;
    }

    public String getTaskSymbol() {
        return taskSymbol;
    }

    public static TaskType symbolValue(String symbol) {
        for (TaskType type : values()) {
            if (type.taskSymbol.equals(symbol)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid tracker.TaskType symbol: " + symbol);
    }
}
