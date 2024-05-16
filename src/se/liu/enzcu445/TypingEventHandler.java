package se.liu.enzcu445;

import javax.swing.*;

public class TypingEventHandler implements TypingEventListener {
    private Timer timer;
    private AverageAccuracyLabel averageAccuracyLabel;

    public TypingEventHandler(Timer timer, AverageAccuracyLabel averageAccuracyLabel) {
	this.timer = timer;
	this.averageAccuracyLabel = averageAccuracyLabel;
    }

    @Override
    public void onTypingStarted() {
	timer.start();
    }

    @Override
    public void onTypingCompleted() {
	timer.stop();
	averageAccuracyLabel.updateAccuracyLabel();
    }
}
