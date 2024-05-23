package se.liu.enzcu445;

import java.util.logging.Logger;

/**
 * PauseController manages the pause and resume functionality for the application.
 * It interacts with a {@link Timer} and a {@link TextPanelComponent} to control the state of the typing session.
 *
 * <p>Responsibilities:</p>
 * <ul>
 *   <li>Toggles the pause state and updates the timer and typing handler accordingly.</li>
 *   <li>Logs pause and resume actions.</li>
 * </ul>
 *
 * @since 1.0
 */
public class PauseController {

    private static final Logger LOGGER = LoggingConfig.getLogger();
    private boolean isPaused = false;
    private Timer timer;
    private TextPanelComponent textPanelComponent; // Keep reference to TypingLogicHandler

    public PauseController(Timer timer, TextPanelComponent textPanelComponent) {
	this.timer = timer;
	this.textPanelComponent = textPanelComponent;
    }

    public void togglePause() {
	isPaused = !isPaused;
	if (isPaused) {
	    timer.stop();
	    textPanelComponent.getTypingHandler().setFreeze(true); // Pause typing
	    LOGGER.info("Pause button pressed. Typing and timer paused.");
	} else {
	    timer.start();
	    textPanelComponent.getTypingHandler().setFreeze(false); // Resume typing
	    LOGGER.info("Pause button pressed again. Typing and timer resumed.");
	}
    }

    public boolean isPaused() {
	if (isPaused)
	    return true;
	else return false;
    }
}
