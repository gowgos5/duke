package task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import parser.DateTimeParser;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventTest {
    private LocalDateTime dateTime;
    private Event eventOne;
    private Event eventTwo;
    private Event eventThree;

    @BeforeEach
    void initialise() {
        dateTime = LocalDateTime.now();
        eventOne = new Event("John's birthday party", dateTime);
        eventTwo = new Event("May's birthday party", dateTime, false);
        eventThree = new Event("Mary's birthday party", dateTime, true);
    }

    @Test
    void testToString() {
        assertEquals(eventOne.toString(), "[E][\u2718] John's birthday party (at: "
                + DateTimeParser.formatDateTimeToString(dateTime) + ")");
        assertEquals(eventTwo.toString(), "[E][\u2718] May's birthday party (at: "
                + DateTimeParser.formatDateTimeToString(dateTime) + ")");
        assertEquals(eventThree.toString(), "[E][\u2713] Mary's birthday party (at: "
                + DateTimeParser.formatDateTimeToString(dateTime) + ")");
    }

    @Test
    void testGetDateTime() {
        assertEquals(eventOne.getDateTime(), dateTime);
        assertEquals(eventTwo.getDateTime(), dateTime);
        assertEquals(eventThree.getDateTime(), dateTime);
    }
}