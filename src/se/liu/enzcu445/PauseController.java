package se.liu.enzcu445;

import java.util.logging.Logger;

public class PauseController {
    private static final Logger logger = LoggingConfig.getLogger();
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
	    logger.info("Pause button pressed. Typing and timer paused.");
	} else {
	    timer.start();
	    textPanelComponent.getTypingHandler().setFreeze(false); // Resume typing
	    logger.info("Pause button pressed again. Typing and timer resumed.");
	}
    }

    public boolean isPaused() {
	return isPaused;
    }
}
