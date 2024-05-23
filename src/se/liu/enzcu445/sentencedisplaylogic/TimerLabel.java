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
    public TimerLabel(Timer timer) {
	super("Time: 0:00.00", SwingConstants.CENTER);  // Set initial text and alignment
	this.timer = timer;



	setBorder(BorderFactory.createLineBorder(Color.BLACK, LABEL_BORDER_THICKNESS));
	setOpaque(true);
	setBackground(Color.WHITE);
	setupTimer();
    }

    private void setupTimer() {
	timer.setTickObserver(this::updateLabel);
    }

    private void updateLabel() {
	final int hundredsInSeconds = 100;
	final int secondsInMinutes = 60;

	int hundredthsElapsed = timer.getHundredthsElapsed();
	int seconds = hundredthsElapsed / hundredsInSeconds;
	int minutes = seconds / secondsInMinutes;
	seconds %= secondsInMinutes;
	int hundredths = hundredthsElapsed % 100;
	setText(String.format("Time: %d:%02d.%02d", minutes, seconds, hundredths));
    }
}
