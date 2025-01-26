import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");

    public Event(String description, String from, String to) {
        super(description, TaskType.EVENT);
        try {
            this.from = LocalDateTime.parse(from, INPUT_FORMATTER);
            this.to = LocalDateTime.parse(to, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use yyyy-MM-dd HHmm (e.g., 2019-12-02 1400).");
        }
    }

    @Override
    public String saveFormat() {
        return super.toString() + " (from: " + from.format(OUTPUT_FORMATTER) + " to: " + to.format(OUTPUT_FORMATTER) + ")";
    }
}
