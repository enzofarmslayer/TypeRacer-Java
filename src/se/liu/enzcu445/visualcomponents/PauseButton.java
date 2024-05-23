package se.liu.enzcu445.visualcomponents;

import se.liu.enzcu445.LoggingConfig;

import javax.swing.*;
import java.util.logging.Logger;

/**
 * PauseButton is a custom JButton that controls the pause and resume functionality of the application.
 * It interacts with a {@link PauseController} to toggle the pause state and updates its label accordingly.
 *
 * <p>Responsibilities:</p>
 * <ul>
 *   <li>Initializes the button with a pause label and sets up action listeners.</li>
 *   <li>Toggles the pause state and updates the button label when clicked.</li>
 *   <li>Enables and disables the button with corresponding log messages.</li>
 * </ul>
 *
 * @since 1.0
 */
public class PauseButton extends JButton {
    private static final Logger LOGGER = LoggingConfig.getLogger();
    private PauseController pauseController;

    public PauseButton(PauseController pauseController) {
	super("Pause");
	this.pauseController = pauseController;

	setFocusable(false);
	this.addActionListener(e -> togglePause());
	disableButton();
    }

    private void togglePause() {
	if (pauseController != null) {
	    pauseController.togglePause();
	    updateButtonLabel();
	}
    }

    private void updateButtonLabel() {
	if (pauseController.isPaused()) {
	    setText("Resume");
	} else {
	    setText("Pause");
	}
    }

    public void enableButton() {
	setEnabled(true);
	LOGGER.info("Pause button enabled.");
    }

    public void disableButton() {
	setEnabled(false);
	LOGGER.info("Pause button disabled.");
    }
}
