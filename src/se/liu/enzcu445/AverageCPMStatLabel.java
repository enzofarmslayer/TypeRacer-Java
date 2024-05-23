package se.liu.enzcu445;

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
	    double secondsElapsed = hundredthsElapsed / 100.0;
	    double currentCPM = (typedLength / secondsElapsed) * 60;
	    typingHandler.saveCurrentCPM(currentCPM);
	    double averageCPM = typingHandler.calculateAverageCpm();
	    updateLabel("Average CPM: %.2f", averageCPM);
	}
    }
}
