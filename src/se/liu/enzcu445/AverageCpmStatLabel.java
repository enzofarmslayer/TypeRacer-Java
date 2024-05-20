package se.liu.enzcu445;

import java.awt.*;

public class AverageCpmStatLabel extends AbstractStatLabel
{
    private Timer timer;

    public AverageCpmStatLabel(TextPanelComponent textPanelComponent, Timer timer) {
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
	    double currentCpm = ((double) typedLength / secondsElapsed) * 60;
	    typingHandler.saveCurrentCpm(currentCpm);
	    double averageCpm = typingHandler.calculateAverageCpm();
	    updateLabel("Average CPM: %.2f", averageCpm);
	}
    }
}
