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
     * @param description description of task
     * @param dateTime    date and time to be completed
     */
    public Deadline(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
        String[] dateAndTime = dateTime.split(" ");
        String[] date = dateAndTime[0].split("/");
        int time = Integer.parseInt(dateAndTime[1]);
        int dayOfMonth = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int year = Integer.parseInt(date[2]);
        int hour = time / 100;
        int min = time % 100;

        localDateTime = LocalDateTime.of(year, month, dayOfMonth, hour, min);
    }

    /**
     * Get the date and time of the task.
     *
     * @return date and time to be completed
     */
    public String getDateTime() {
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
