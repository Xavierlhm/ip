package tracker;

public class Todo extends Task {
    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    @Override
    public String saveFormat() {
        return taskType.getTaskSymbol() + " | " + (isDone ? "1" : "0") + " | " + description;
    }
}
