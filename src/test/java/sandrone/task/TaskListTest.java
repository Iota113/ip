package sandrone.task;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import sandrone.StorageStub;
import sandrone.exception.SandroneException;

public class TaskListTest {

    @Test
    public void add_validInput_addedToList() throws SandroneException {
        StorageStub testStorage = new StorageStub();
        TaskList taskList = new TaskList(testStorage);
        taskList.add(new Todo("Read book"));

        assertEquals(1, taskList.getCount());
        assertEquals("Read book", taskList.get(0).getTaskDescription());
    }

    @Test
    public void delete_validInput_removedFromList() throws SandroneException {
        StorageStub testStorage = new StorageStub();
        TaskList taskList = new TaskList(testStorage);
        taskList.add(new Todo("Read book"));
        taskList.add(new Deadline("MA2104 Tutorial", LocalDate.of(2026, 1, 30)));
        taskList.delete(0);
        assertEquals("[·][D][·] MA2104 Tutorial (by: 2026 Jan 30)", taskList.get(0).toString());
    }

}
