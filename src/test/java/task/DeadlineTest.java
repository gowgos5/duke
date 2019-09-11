package task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import parser.DateTimeParser;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlineTest {
    private LocalDateTime dateTime;
    private Deadline deadlineOne;
    private Deadline deadlineTwo;
    private Deadline deadlineThree;

    @BeforeEach
    void initialise() {
        dateTime = LocalDateTime.now();
        deadlineOne = new Deadline("Assignment 1", dateTime);
        deadlineTwo = new Deadline("Assignment 2", dateTime, false);
        deadlineThree = new Deadline("Assignment 3", dateTime, true);
    }

    @Test
    void testToString() {
        assertEquals(deadlineOne.toString(), "[D][\u2718] Assignment 1 (by: "
                + DateTimeParser.formatDateTimeToString(dateTime) + ")");
        assertEquals(deadlineTwo.toString(), "[D][\u2718] Assignment 2 (by: "
                + DateTimeParser.formatDateTimeToString(dateTime) + ")");
        assertEquals(deadlineThree.toString(), "[D][\u2713] Assignment 3 (by: "
                + DateTimeParser.formatDateTimeToString(dateTime) + ")");
    }

    @Test
    void testGetDateTime() {
        assertEquals(deadlineOne.getDateTime(), dateTime);
        assertEquals(deadlineTwo.getDateTime(), dateTime);
        assertEquals(deadlineThree.getDateTime(), dateTime);
    }
}
