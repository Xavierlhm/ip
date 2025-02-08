package tracker;

import java.util.ArrayList;

/**
 * Manages a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the specified list of tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        assert tasks != null : "Task list cannot be null";
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        assert task != null : "Task to add cannot be null";
        tasks.add(task);
    }

    /**
     * Removes a task from the list by index.
     *
     * @param index The index of the task to be removed.
     * @return The removed task.
     * @throws TrackerException If the index is out of range.
     */
    public Task removeTask(int index) throws TrackerException {
        /*
        if (index < 0 || index >= tasks.size()) {
            throw new TrackerException("Error: Task index out of range. Please check the list of tasks again.");
        }
         */

        assert index >= 0 && index < tasks.size() : "Index out of range when removing task";
        return tasks.remove(index);
    }

    /**
     * Retrieves a task from the list by index.
     *
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index.
     * @throws TrackerException If the index is out of range.
     */
    public Task getTask(int index) throws TrackerException {
        /*
        if (index < 0 || index >= tasks.size()) {
            throw new TrackerException("Error: Task index out of range. Please check the list of tasks again.");
        }
         */

        assert index >= 0 && index < tasks.size() : "Index out of range when retrieving task";
        return tasks.get(index);
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Retrieves the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }
}
