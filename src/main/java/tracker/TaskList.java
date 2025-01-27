package tracker;

import java.util.ArrayList;

/**
 * Stores the tasks into an arraylist for further actions.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     *
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * @param tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * @param task
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * @param index
     * @return
     * @throws TrackerException
     */
    public Task removeTask(int index) throws TrackerException {
        if (index < 0 || index >= tasks.size()) {
            throw new TrackerException("tracker.Task index out of range. Please check the list of tasks again.");
        }

        return tasks.remove(index);
    }

    /**
     * @param index
     * @return
     * @throws TrackerException
     */
    public Task getTask(int index) throws TrackerException {
        if (index < 0 || index >= tasks.size()) {
            throw new TrackerException("tracker.Task index out of range. Please check the list of tasks again.");
        }

        return tasks.get(index);
    }

    /**
     * @return tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * @return task size
     */
    public int size() {
        return tasks.size();
    }
}
