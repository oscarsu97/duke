import duke.task.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void testToString() {
        Event event = new Event("read book", "12/12/2019 1300");
        assertEquals("12/12/2019 1300", event.getDateTime());
    }
}
