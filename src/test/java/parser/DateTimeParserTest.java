package parser;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DateTimeParserTest {
    @Test
    void formatDateTimeToString_localDateTime_success() {
        LocalDateTime dateTime = LocalDateTime.of(2019, 9, 9, 8, 56);
        assertEquals(DateTimeParser.formatDateTimeToString(dateTime), "09/09/2019 08:56");
    }

    @Test
    void formatStringToDateTime_validFormat_success() {
        /* VALID DATE-TIME FORMAT */
        assertEquals(DateTimeParser.formatStringToDateTime("09/09/2019 08:56"),
                LocalDateTime.of(2019, 9, 9, 8, 56));
    }

    @Test
    void formatStringToDateTime_invalidFormat_exceptionThrown() {
        /* INVALID DATE-TIME FORMAT */
        assertThrows(DateTimeParseException.class, () -> DateTimeParser.formatStringToDateTime("090919 0856"));
        assertThrows(DateTimeParseException.class, () -> DateTimeParser.formatStringToDateTime("09-09-2019 08:56"));
        assertThrows(DateTimeParseException.class, () -> DateTimeParser.formatStringToDateTime("09/09/19 08:56"));
        assertThrows(DateTimeParseException.class, () -> DateTimeParser.formatStringToDateTime("09/09/2019 08:56 pm"));
        assertThrows(DateTimeParseException.class, () -> DateTimeParser.formatStringToDateTime("tomorrow"));
        assertThrows(DateTimeParseException.class, () -> DateTimeParser.formatStringToDateTime("next Monday"));
    }
}
