package se.liu.enzcu445;

import javax.swing.*;
import java.awt.*;

/**
 * AbstractStatLabel is an abstract class that extends JLabel and implements the {@link LabelUpdater} interface.
 * It is designed to display and update statistical information based on changes in typing performance.
 *
 * <p>Tasks:</p>
 * <ul>
 *   <li>Initializes with a TypingLogicHandler from a {@link TextPanelComponent}.</li>
 *   <li>Updates the label's text and background color based on new values.</li>
 * </ul>
 *
 * <p>Relationships:</p>
 * <ul>
 *   <li>{@link LabelUpdater}: Interface for updating the label.</li>
 *   <li>{@link TextPanelComponent}: Provides the TypingLogicHandler used for updates.</li>
 *   <li>{@link TypingLogicHandler}: Manages typing performance logic.</li>
 * </ul>
 *
 * @since 1.0
 */

public abstract class AbstractStatLabel extends JLabel implements LabelUpdater {
    protected TypingLogicHandler typingHandler;
    protected double previousValue = -1.0;

    protected AbstractStatLabel(TextPanelComponent textPanelComponent, String initialText) {
	super(initialText, SwingConstants.CENTER);
	this.typingHandler = textPanelComponent.getTypingHandler();
	setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	setOpaque(true);
	setBackground(Color.WHITE);
    }

    protected void updateLabel(String format, double newValue) {
	setText(String.format(format, newValue));

	if (previousValue < 0) {
	    setBackground(Color.WHITE);
	} else if (newValue > previousValue) {
	    setBackground(Color.GREEN);
	} else if (newValue < previousValue) {
	    setBackground(Color.RED);
	} else {
	    setBackground(Color.WHITE);
	}

	previousValue = newValue;
    }
}
