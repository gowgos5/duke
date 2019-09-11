package task;

import parser.DateTimeParser;

import java.time.LocalDateTime;

/**
 * Class representing a specialised {@link task.Task}.
 * Deadline object has a date-time element associated with it.
 */
public class Deadline extends Task implements ITaskWithDateTime {
    private LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Gets full details of Deadline.
     *
     * @return Deadline's full details.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateTimeParser.formatDateTimeToString(by) + ")";
    }

    /**
     * Gets date-time of Deadline.
     *
     * @return Deadline's date-time.
     */
    @Override
    public LocalDateTime getDateTime() {
        return by;
    }
}