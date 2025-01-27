package tracker;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    @Test
    public void addTask_taskAdded_success() {
        TaskList taskList = new TaskList();
        Task task = new Todo("Test Task");
        taskList.addTask(task);
        assertEquals(1, taskList.size());
        assertEquals(task, taskList.getTasks().get(0));
    }

    @Test
    public void removeTask_validIndex_taskRemoved() throws TrackerException {
        TaskList taskList = new TaskList();
        Task task = new Todo("Test Task");
        taskList.addTask(task);

        Task removedTask = taskList.removeTask(0);
        assertEquals(task, removedTask);
        assertEquals(0, taskList.size());
    }

    @Test
    public void removeTask_invalidIndex_throwsException() {
        TaskList taskList = new TaskList();
        assertThrows(TrackerException.class, () -> taskList.removeTask(0));
    }

    @Test
    public void getTask_validIndex_success() throws TrackerException {
        TaskList taskList = new TaskList();
        Task task = new Todo("Test Task");
        taskList.addTask(task);

        assertEquals(task, taskList.getTask(0));
    }

    @Test
    public void getTask_invalidIndex_throwsException() {
        TaskList taskList = new TaskList();
        assertThrows(TrackerException.class, () -> taskList.getTask(0));
    }
}
