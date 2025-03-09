package tracker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class AddTodoCommandTest {

    @Test
    public void execute_validInput_taskAdded() throws TrackerException {
        TaskList taskList = new TaskList(new ArrayList<>());
        Ui ui = new Ui();
        Storage storage = new Storage("testTasks.txt");

        Command command = new AddTodoCommand("todo Read book");
        command.execute(taskList, ui, storage);

        assertEquals(1, taskList.size());
        assertTrue(taskList.getTask(0) instanceof Todo);
        assertEquals("Read book", taskList.getTask(0).description);
    }

    @Test
    public void execute_emptyDescription_throwsException() throws TrackerException {
        TaskList taskList = new TaskList(new ArrayList<>());
        Ui ui = new Ui();
        Storage storage = new Storage("testTasks.txt");

        Command command = new AddTodoCommand("todo ");
        assertThrows(TrackerException.class, () -> command.execute(taskList, ui, storage));
    }
}
