package parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class responsible for parsing date-time.
 */
public class DateTimeParser {
    private static final String DATE_FORMATTER = "dd/MM/yyyy HH:mm";

    /**
     * Converts a LocalDateTime object into a formatted String object.
     *
     * @param dateTime LocalDateTime object with the intended date-time.
     * @return The formatted date-time string (dd/MM/yyyy HH:mm).
     */
    public static String formatDateTimeToString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
        return dateTime.format(formatter);
    }

    /**
     * Obtains an instance of LocalDateTime from a text string of format "dd/MM/yyyy HH:mm".
     *
     * @param dateTime The text to parse.
     * @return The parsed LocalDateTime object.
     * @throws DateTimeParseException If the format of the given text does not follow the above format.
     */
    public static LocalDateTime formatStringToDateTime(String dateTime) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
        return LocalDateTime.parse(dateTime, formatter);
    }
}