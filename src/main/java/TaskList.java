import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task removeTask(int index) throws TrackerException {
        if (index < 0 || index >= tasks.size()) {
            throw new TrackerException("Task index out of range. Please check the list of tasks again.");
        }

        return tasks.remove(index);
    }

    public Task getTask(int index) throws TrackerException {
        if (index < 0 || index >= tasks.size()) {
            throw new TrackerException("Task index out of range. Please check the list of tasks again.");
        }

        return tasks.get(index);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int size() {
        return tasks.size();
    }
}
