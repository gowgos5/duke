package task;

import parser.DateTimeParser;

import java.time.LocalDateTime;

/**
 * Class representing a specialised {@link task.Task}.
 * Event object has a date-time element associated with it.
 */
public class Event extends Task implements ITaskWithDateTime {
    private LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    public Event(String description, LocalDateTime at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * Gets full details of Event.
     *
     * @return Event's full details.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + DateTimeParser.formatDateTimeToString(at) + ")";
    }

    /**
     * Gets date-time of Event.
     *
     * @return Event's date-time.
     */
    @Override
    public LocalDateTime getDateTime() {
        return at;
    }
}