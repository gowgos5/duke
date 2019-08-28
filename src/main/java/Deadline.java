import java.time.LocalDateTime;

public class Deadline extends Task implements ITaskWithDateTime {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateTimeParser.formatDateTimeToString(by) + ")";
    }

    @Override
    public LocalDateTime getDateTime() {
        return by;
    }
}