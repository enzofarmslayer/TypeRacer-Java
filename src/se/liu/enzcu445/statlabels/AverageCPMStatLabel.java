package se.liu.enzcu445.statlabels;

import se.liu.enzcu445.sentencedisplaylogic.TextPanelComponent;
import se.liu.enzcu445.sentencedisplaylogic.Timer;
import se.liu.enzcu445.sentencedisplaylogic.TypingLogicHandler;

import java.awt.*;
/**
 * AverageCpmStatLabel is a concrete implementation of {@link AbstractStatLabel} that displays the average characters per minute (CPM).
 * It updates the label with the average CPM value based on data from {@link TypingLogicHandler} and a {@link Timer}.
 *
 * @since 1.0
 */
public class AverageCPMStatLabel extends AbstractStatLabel
{
    private Timer timer;
    private static final double HUNDREDS_PER_SECOND = 100.0;
    private static final int SECONDS_IN_MINUTE = 60;

    public AverageCPMStatLabel(TextPanelComponent textPanelComponent, Timer timer) {
	super(textPanelComponent, "Average CPM: --");
	this.timer = timer;
    }

    @Override
    public void update() {
	int hundredthsElapsed = timer.getHundredthsElapsed();
	int typedLength = typingHandler.getTypedLength();

	if (hundredthsElapsed == 0) {
	    setText("Average CPM: --");
	    setBackground(Color.WHITE);
	} else {
	    double secondsElapsed = hundredthsElapsed / HUNDREDS_PER_SECOND;
	    double currentCPM = (typedLength / secondsElapsed) * SECONDS_IN_MINUTE;
	    typingHandler.saveCurrentCPM(currentCPM);
	    double averageCPM = typingHandler.calculateAverageCpm();
	    updateLabel("Average CPM: %.2f", averageCPM);
	}
    }
}
