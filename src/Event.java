import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event {
    private LocalDate date;
    private String description;

    private static DateTimeFormatter dateTimeFormatter;
    static {
        dateTimeFormatter =
                DateTimeFormatter.ofPattern("yyyy.MM.dd");
    }

    public Event(LocalDate date, String description) {
        this.date = date;
        this.description = description;
    }

    public Event(String date, String description){
        this(LocalDate.parse(date, dateTimeFormatter), description);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static DateTimeFormatter getDateTimeFormatter() {
        return dateTimeFormatter;
    }

    public static void setDateTimeFormatter(DateTimeFormatter dateTimeFormatter) {
        Event.dateTimeFormatter = dateTimeFormatter;
    }

    @Override
    public String toString() {
        return description + " ---- " + Utils.formatDateToString(date);
    }
}
