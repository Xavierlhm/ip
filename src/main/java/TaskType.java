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
}
