import Duke.Task.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void testToString() {
        Event event = new Event("read book", "12/12/2019 1300");
        assertEquals("[E][\u2718] read book(at: 12/12/2019 1300)", event.toString());
    }
}
