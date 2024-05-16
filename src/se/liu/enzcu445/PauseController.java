package se.liu.enzcu445;

public class PauseController {
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
	} else {
	    timer.start();
	    textPanelComponent.getTypingHandler().setFreeze(false); // Resume typing
	}
    }

    public boolean isPaused() {
	return isPaused;
    }
}
