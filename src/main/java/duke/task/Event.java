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
     * @param dateTime    date and time to be completed
     */
    public Event(String description, String dateTime) {
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
        return String.format("[E][%s] %s(at: %s)", getStatusIcon(), description, localDateTime.format(dateTimeFormat));
    }
}