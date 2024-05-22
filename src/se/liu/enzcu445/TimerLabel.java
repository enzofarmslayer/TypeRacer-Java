package se.liu.enzcu445;

import javax.swing.*;
import java.awt.*;

/**
 * TimerLabel is a custom JLabel that displays the elapsed time tracked by a {@link Timer}.
 * It updates the label text to show the current elapsed time in minutes, seconds, and hundredths of a second.
 *
 * <p>Responsibilities:</p>
 * <ul>
 *   <li>Initializes and configures the label with default text and styling.</li>
 *   <li>Updates the displayed time at regular intervals.</li>
 * </ul>
 *
 * @since 1.0
 */
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
	seconds %= 60;
	int hundredths = hundredthsElapsed % 100;
	setText(String.format("Time: %d:%02d.%02d", minutes, seconds, hundredths));
    }
}
