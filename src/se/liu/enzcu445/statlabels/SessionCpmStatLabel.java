package se.liu.enzcu445.statlabels;

import se.liu.enzcu445.sentencedisplaylogic.TextPanelComponent;
import se.liu.enzcu445.sentencedisplaylogic.Timer;
import se.liu.enzcu445.sentencedisplaylogic.TypingLogicHandler;

/**
 * SessionCpmStatLabel is a concrete implementation of {@link AbstractStatLabel} that displays the session characters per minute (CPM).
 * It updates the label with the current session's CPM value based on data from {@link TypingLogicHandler} and a {@link Timer}.
 *
 * @since 1.0
 */

public class SessionCpmStatLabel extends AbstractStatLabel
{
    private Timer timer;
    private static final int SECONDS_PER_MINUTE = 60;
    private static final double HUNDREDS_PER_SECOND = 100;

    public SessionCpmStatLabel(TextPanelComponent textPanelComponent, Timer timer) {
	super(textPanelComponent, "Session CPM: --");
	this.timer = timer;
	update();
    }

    @Override
    protected void updateLabel(String format, double newValue) {
	setText(String.format(format, newValue));
    }

    @Override
    public void update() {
	int hundredthsElapsed = timer.getHundredthsElapsed();
	int typedLength = typingHandler.getTypedLength();

	if (hundredthsElapsed == 0) {
	    setText("Session CPM: --");
	} else {
	    double secondsElapsed = hundredthsElapsed / HUNDREDS_PER_SECOND;
	    double currentCpm = (typedLength / secondsElapsed) * SECONDS_PER_MINUTE;
	    updateLabel("Session CPM: %.2f", currentCpm);
	}
    }
}
