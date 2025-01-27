package tracker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 * Stores the description and the due date of the task.
 */
public class Deadline extends Task {
    protected LocalDateTime by;
    private static final DateTimeFormatter FORMATTER_INPUT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter FORMATTER_OUTPUT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");

    /**
     * Constructs a Deadline task with the given description and due date.
     *
     * @param description The description of the task.
     * @param by          The due date in the format "yyyy-MM-dd HHmm".
     * @throws IllegalArgumentException If the date format is invalid.
     */
    public Deadline(String description, String by) {
        super(description, TaskType.DEADLINE);
        try {
            this.by = LocalDateTime.parse(by, FORMATTER_INPUT);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use yyyy-MM-dd HHmm (e.g., 2019-12-02 1800).");
        }
    }

    /**
     * Returns the string representation of the task for saving.
     *
     * @return A formatted string containing the task details.
     */
    @Override
    public String saveFormat() {
        return super.toString() + " (by: " + by.format(FORMATTER_OUTPUT) + ")";
    }
}
