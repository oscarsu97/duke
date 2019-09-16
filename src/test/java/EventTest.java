import duke.task.Event;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void testToString() {
        Event event = new Event("read book",
                LocalDateTime.of(2019, 12, 12, 13, 00));
        assertEquals("12/12/2019 1300", event.getDateTime());
    }
}
