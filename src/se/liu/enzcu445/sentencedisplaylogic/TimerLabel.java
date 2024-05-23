package se.liu.enzcu445.sentencedisplaylogic;

import javax.swing.*;
import java.awt.*;

/**
 * TimerLabel is a custom component that displays the elapsed time tracked by a {@link Timer}.
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
public class TimerLabel {
    private JLabel label;
    private Timer timer;
    private static final int LABEL_BORDER_THICKNESS = 2;
    private static final int HUNDREDTHS_PER_SECOND = 100;
    private static final int SECONDS_PER_MINUTE = 60;

    public TimerLabel(Timer timer) {
	label = new JLabel("Time: 0:00.00", SwingConstants.CENTER);  // Set initial text and alignment
	this.timer = timer;

	label.setBorder(BorderFactory.createLineBorder(Color.BLACK, LABEL_BORDER_THICKNESS));
	label.setOpaque(true);
	label.setBackground(Color.WHITE);
	initializeTimer();
    }

    private void initializeTimer() {
	timer.setTickObserver(this::updateLabel);
    }

    private void updateLabel() {
	int hundredthsElapsed = timer.getHundredthsElapsed();
	int seconds = hundredthsElapsed / HUNDREDTHS_PER_SECOND;
	int minutes = seconds / SECONDS_PER_MINUTE;
	seconds %= SECONDS_PER_MINUTE;
	int hundredths = hundredthsElapsed % HUNDREDTHS_PER_SECOND;
	label.setText(String.format("Time: %d:%02d.%02d", minutes, seconds, hundredths));
    }

    public JComponent getComponent() {
	return label;
    }
}

