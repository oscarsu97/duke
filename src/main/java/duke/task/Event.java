package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Event class that start at a specific time and ends at a specific time.
 */
public class Event extends Task {
    private LocalDateTime localDateTime;
    private DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/YYYY HHmm");
    private String dateTime;

    /**
     * Initialise Event task with description of task and the date and time to be completed.
     *
     * @param description description of task
     * @param localDateTime local date and time to be completed
     */
    public Event(String description, LocalDateTime localDateTime, String dateTime) {
        super(description);
        this.localDateTime = localDateTime;
        this.dateTime = dateTime;
    }

    /**
     * Get the date and time of the task.
     *
     * @return date and time to be completed
     */
    public String getDateTime() {
        assert dateTime != null : "dateTime is not initialised";
        return dateTime;
    }

    /**
     * String representation of Deadline task.
     *
     * @return string representation
     */
    @Override
    public String toString() {
        return String.format("[E][%s] %s(at: %s)", getStatusIcon(), description, localDateTime.format(dateTimeFormat));
    }
}