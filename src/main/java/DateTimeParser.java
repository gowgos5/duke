import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeParser {
    public static final String DATE_FORMATTER = "dd/MM/yyyy HH:mm";

    public static String formatDateTimeToString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
        return dateTime.format(formatter);
    }

    public static LocalDateTime formatStringToDateTime(String dateTime) throws DukeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
            return LocalDateTime.parse(dateTime, formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException(DukeException.EXCEPTION_UNKNOWN_DATETIME_FORMAT);
        }
    }
}
