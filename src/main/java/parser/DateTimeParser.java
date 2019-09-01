package parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeParser {
    private static final String DATE_FORMATTER = "dd/MM/yyyy HH:mm";

    public static String formatDateTimeToString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
        return dateTime.format(formatter);
    }

    public static LocalDateTime formatStringToDateTime(String dateTime) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
        return LocalDateTime.parse(dateTime, formatter);
    }
}