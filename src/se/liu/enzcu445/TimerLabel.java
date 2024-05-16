package se.liu.enzcu445;

import javax.swing.*;
import java.awt.*;

public class TimerLabel {
    private JLabel displayLabel;
    private Timer timer;

    public TimerLabel(Timer timer) {
	this.timer = timer;
	displayLabel = new JLabel("Time: 0:00", SwingConstants.CENTER);
	displayLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	displayLabel.setOpaque(true);
	displayLabel.setBackground(Color.WHITE);
	setupTimer();
    }

    private void setupTimer() {
	// Assuming Timer has a way to attach listeners
	javax.swing.Timer swingTimer = new javax.swing.Timer(1000, e -> updateLabel());
	swingTimer.start();
    }

    private void updateLabel() {
	int seconds = timer.getSecondsElapsed();
	int minutes = seconds / 60;
	seconds %= 60;
	displayLabel.setText(String.format("Time: %d:%02d", minutes, seconds));
    }

    public JLabel getDisplayLabel() {
	return displayLabel;
    }
}
