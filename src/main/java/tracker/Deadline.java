package tracker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Checks if the input is valid before saving the deadline task.
 */
public class Deadline extends Task {
    protected LocalDateTime by;
    private static final DateTimeFormatter FORMATTER_INPUT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter FORMATTER_OUTPUT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");

    /**
     * @param description
     * @param by
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
     * @return string output
     */
    @Override
    public String saveFormat() {
        return super.toString() + " (by: " + by.format(FORMATTER_OUTPUT) + ")";
    }
}
