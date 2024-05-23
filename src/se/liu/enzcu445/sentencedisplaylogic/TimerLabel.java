package se.liu.enzcu445.sentencedisplaylogic;

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
    private static final int LABEL_BORDER_THICKNESS = 2;
    private static final int HUNDREDS_PER_SECONDS = 100;
    private static final int SECONDS_PER_MINUTES = 60;

    public TimerLabel(Timer timer) {
	super("Time: 0:00.00", SwingConstants.CENTER);  // Set initial text and alignment
	this.timer = timer;

	setBorder(BorderFactory.createLineBorder(Color.BLACK, LABEL_BORDER_THICKNESS));
	setOpaque(true);
	setBackground(Color.WHITE);
	initializeTimer();
    }

    private void initializeTimer() {
	timer.setTickObserver(this::updateLabel);
    }

    private void updateLabel() {

	int hundredthsElapsed = timer.getHundredthsElapsed();
	int seconds = hundredthsElapsed / HUNDREDS_PER_SECONDS;
	int minutes = seconds / SECONDS_PER_MINUTES;
	seconds %= SECONDS_PER_MINUTES;
	int hundredths = hundredthsElapsed % HUNDREDS_PER_SECONDS;
	setText(String.format("Time: %d:%02d.%02d", minutes, seconds, hundredths));
    }
}
