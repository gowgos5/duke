package task;

import parser.DateTimeParser;

import java.time.LocalDateTime;

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

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + DateTimeParser.formatDateTimeToString(at) + ")";
    }

    @Override
    public LocalDateTime getDateTime() {
        return at;
    }
}