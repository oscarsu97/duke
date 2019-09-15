package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task that needs to be done before a specific date/time.
 */
public class Deadline extends Task {
    private LocalDateTime localDateTime;
    private DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/YYYY HHmm");

    /**
     * Initialises Deadline task with description of task and the date and time to be completed.
     *
     * @param description   description of task
     * @param localDateTime date and time to be completed
     */
    public Deadline(String description, LocalDateTime localDateTime) {
        super(description);
        this.localDateTime = localDateTime;
    }

    /**
     * Gets the date and time of the task in string.
     *
     * @return date and time to be completed in string
     */
    public String getDateTime() {
        assert localDateTime != null : "dateTime is not initialised";
        return localDateTime.format(dateTimeFormat);
    }

    /**
     * String representation of Deadline task.
     *
     * @return string representation
     */
    @Override
    public String toString() {
        return String.format("[D][%s] %s(by: %s)", getStatusIcon(), description, localDateTime.format(dateTimeFormat));
    }
}
