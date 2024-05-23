package se.liu.enzcu445.statlabels;

import se.liu.enzcu445.sentencedisplaylogic.TextPanelComponent;
import se.liu.enzcu445.sentencedisplaylogic.TypingLogicHandler;

/**
 * SessionAccuracyStatLabel is a concrete implementation of {@link AbstractStatLabel} that displays the session accuracy.
 * It updates the label with the current session's accuracy value based on data from {@link TypingLogicHandler}.
 *
 * @since 1.0
 */
public class SessionAccuracyStatLabel extends AbstractStatLabel
{

    public SessionAccuracyStatLabel(TextPanelComponent textPanelComponent) {
	super(textPanelComponent, "Session Accuracy: --");
	update();
    }

    @Override
    protected void updateLabel(String format, double newValue) {
	setText(String.format(format, newValue));
    }

    @Override
    public void update() {
	double currentAccuracy = typingHandler.calculateAccuracy();
	updateLabel("Session Accuracy: %.2f%%", currentAccuracy);
    }
}
