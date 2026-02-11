package sandrone.task;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import sandrone.StorageStub;
import sandrone.exception.SandroneException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void addTask_validInput_taskAddedToList() throws SandroneException {
        StorageStub testStorage = new StorageStub();
        TaskList taskList = new TaskList(testStorage);
        taskList.addTask(new Todo("Read book"));

        assertEquals(1, taskList.getTasksCount());
        assertEquals("Read book", taskList.getTask(0).getDescription());
    }

    @Test
    public void deleteTask_validInput_taskRemovedFromList() throws SandroneException {
        StorageStub testStorage = new StorageStub();
        TaskList taskList = new TaskList(testStorage);
        taskList.addTask(new Todo("Read book"));
        taskList.addTask(new Deadline("MA2104 Tutorial", LocalDate.of(2026, 1, 30)));
        taskList.deleteTask(0);
        assertEquals("MA2104 Tutorial (by: 2026-01-30)", taskList.getTask(0).getDescription());
    }

}
