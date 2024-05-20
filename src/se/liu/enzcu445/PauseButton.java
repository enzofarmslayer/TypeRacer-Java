package se.liu.enzcu445;

import javax.swing.*;

public class PauseButton extends JButton {
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
    }

    public void disableButton() {
	setEnabled(false);
    }
}
