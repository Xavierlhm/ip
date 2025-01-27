package tracker;

/**
 * Represents an exception specific to the Tracker application.
 */
public class TrackerException extends Exception {
    /**
     * Constructs a TrackerException with the specified error message.
     *
     * @param message The error message.
     */
    public TrackerException(String message) {
        super(message);
    }
}
