package se.liu.enzcu445.visualcomponents;

import se.liu.enzcu445.sentencedisplaylogic.TextPanelComponent;
import se.liu.enzcu445.typinglogic.Timer;
import se.liu.enzcu445.typinglogic.TypingLogicHandler;

/**
 * SessionCpmStatLabel is a concrete implementation of {@link AbstractStatLabel} that displays the session characters per minute (CPM).
 * It updates the label with the current session's CPM value based on data from {@link TypingLogicHandler} and a {@link Timer}.
 *
 * @since 1.0
 */

public class SessionCpmStatLabel extends AbstractStatLabel
{
    private Timer timer;

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
	    double secondsElapsed = hundredthsElapsed / 100.0;
	    double currentCpm = (typedLength / secondsElapsed) * 60;
	    updateLabel("Session CPM: %.2f", currentCpm);
	}
    }
}
