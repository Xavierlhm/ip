package tracker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Checks if the input is valid before saving the event task.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    private static final DateTimeFormatter FORMATTER_INPUT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter FORMATTER_OUTPUT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");

    /**
     * @param description
     * @param from
     * @param to
     */
    public Event(String description, String from, String to) {
        super(description, TaskType.EVENT);
        try {
            this.from = LocalDateTime.parse(from, FORMATTER_INPUT);
            this.to = LocalDateTime.parse(to, FORMATTER_INPUT);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use yyyy-MM-dd HHmm (e.g., 2019-12-02 1400).");
        }
    }

    /**
     * @return string output
     */
    @Override
    public String saveFormat() {
        return super.toString() + " (from: " + from.format(FORMATTER_OUTPUT) + " to: " + to.format(FORMATTER_OUTPUT) + ")";
    }
}
