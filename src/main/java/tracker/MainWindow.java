package tracker;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    static final double PAUSE_DURATION = 1;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Tracker tracker;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/duke.png"));

    /**
     * Initializes the value for the components of the application.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        assert userImage != null : "User image loading failed";
        assert dukeImage != null : "Duke image loading failed";
    }

    /** Injects the Tracker instance */
    public void setTracker(Tracker trackerInstance) {
        assert trackerInstance != null : "Tracker instance cannot be null";
        tracker = trackerInstance;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws TrackerException {
        String input = userInput.getText();
        assert input != null : "User input cannot be null";
        String response = tracker.getResponse(input);
        assert response != null : "Tracker response cannot be null";
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
        if (input.equalsIgnoreCase("bye")) {
            PauseTransition pause = new PauseTransition(Duration.seconds(PAUSE_DURATION));
            pause.setOnFinished(event -> Platform.exit());
            pause.play();
        }
    }
}
