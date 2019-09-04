import duke.task.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void testGetTaskSize() {
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.getTaskListSize());
    }
}
