import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Utils {
    public static boolean isDateValid(String date) {
        try {
            Event.getDateTimeFormatter().parse(date);
            return true;
        }catch (DateTimeParseException e){
            return false;
        }
    }

    public static String formatDateToString(LocalDate localDate){
        return Event.getDateTimeFormatter().format(localDate);
    }
}
