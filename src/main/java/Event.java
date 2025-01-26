import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");

    public Event(String description, String from, String to) {
        super(description, TaskType.EVENT);
        this.from = LocalDateTime.parse(from, INPUT_FORMATTER);
        this.to = LocalDateTime.parse(to, INPUT_FORMATTER);
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + from.format(OUTPUT_FORMATTER) + " to: " + to.format(OUTPUT_FORMATTER) + ")";
    }
}