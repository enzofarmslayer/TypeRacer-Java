package se.liu.enzcu445;

import javax.swing.*;
import java.awt.*;

public class TimerLabel extends JLabel {
    private Timer timer;

    public TimerLabel(Timer timer) {
	super("Time: 0:00.00", SwingConstants.CENTER);  // Set initial text and alignment
	this.timer = timer;
	setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	setOpaque(true);
	setBackground(Color.WHITE);
	setupTimer();
    }

    private void setupTimer() {
	javax.swing.Timer swingTimer = new javax.swing.Timer(10, e -> updateLabel());
	swingTimer.start();
    }

    private void updateLabel() {
	int hundredthsElapsed = timer.getHundredthsElapsed();
	int seconds = hundredthsElapsed / 100;
	int minutes = seconds / 60;
	seconds = seconds % 60;
	int hundredths = hundredthsElapsed % 100;
	setText(String.format("Time: %d:%02d.%02d", minutes, seconds, hundredths));
    }
}
