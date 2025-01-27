package tracker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task that occurs within a specific time range.
 * Stores the description, start time, and end time of the event.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    private static final DateTimeFormatter FORMATTER_INPUT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter FORMATTER_OUTPUT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");

    /**
     * Constructs an Event task with the given description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from        The start time in the format "yyyy-MM-dd HHmm".
     * @param to          The end time in the format "yyyy-MM-dd HHmm".
     * @throws IllegalArgumentException If the date format is invalid.
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
     * Returns the string representation of the task for saving.
     *
     * @return A formatted string containing the task details.
     */
    @Override
    public String saveFormat() {
        return super.toString() + " (from: " + from.format(FORMATTER_OUTPUT) + " to: " + to.format(FORMATTER_OUTPUT) + ")";
    }
}
