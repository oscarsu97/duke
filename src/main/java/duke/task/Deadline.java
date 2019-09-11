package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represent a deadline task that need to be done before a specific date/time.
 */
public class Deadline extends Task {
    private LocalDateTime localDateTime;
    private DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/YYYY HHmm");
    private String dateTime;

    /**
     * Initialise Deadline task with description of task and the date and time to be completed.
     *
     * @param description   description of task
     * @param localDateTime date and time to be completed
     */
    public Deadline(String description, LocalDateTime localDateTime, String dateTime) {
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
        return String.format("[D][%s] %s(by: %s)", getStatusIcon(), description, localDateTime.format(dateTimeFormat));
    }
}
