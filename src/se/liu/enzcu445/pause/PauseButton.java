package se.liu.enzcu445.pause;

import se.liu.enzcu445.LoggingConfig;

import javax.swing.*;
import java.util.logging.Logger;
import java.awt.event.ActionListener;

/**
 * PauseButton is a custom component that controls the pause and resume functionality of the application.
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
public class PauseButton {
    private static final Logger LOGGER = LoggingConfig.getLogger();
    private JButton button;
    private PauseController pauseController;

    public PauseButton(PauseController pauseController) {
	this.pauseController = pauseController;
	button = new JButton("Pause");

	button.setFocusable(false);
	button.addActionListener(e -> togglePause());
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
	    button.setText("Resume");
	} else {
	    button.setText("Pause");
	}
    }

    public void enableButton() {
	button.setEnabled(true);
	LOGGER.info("Pause button enabled.");
    }

    public void disableButton() {
	button.setEnabled(false);
	LOGGER.info("Pause button disabled.");
    }

    public JButton getButton() {
	return button;
    }
}
