import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");

    public Deadline(String description, String by) {
        super(description, TaskType.DEADLINE);
        this.by = LocalDateTime.parse(by, INPUT_FORMATTER);
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by.format(OUTPUT_FORMATTER) + ")";
    }
}