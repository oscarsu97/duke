import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime localDateTime;
    private DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/YYYY HHmm");

    public Deadline(String description, String dateTime) {
        super(description);
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

    @Override
    public String toString() {
        return String.format("[D][%s] %s(by: %s)", getStatusIcon(), description, localDateTime.format(dateTimeFormat));
    }
}
