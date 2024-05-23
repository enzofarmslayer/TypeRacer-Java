package se.liu.enzcu445.statlabels;

import se.liu.enzcu445.sentencedisplaylogic.TextPanelComponent;
import se.liu.enzcu445.sentencedisplaylogic.TypingLogicHandler;

import javax.swing.*;
import java.awt.*;

/**
 * AbstractStatLabel is an abstract class that provides a JLabel for displaying and updating statistical information based on changes in typing performance.
 * It implements the {@link LabelUpdater} interface.
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
public abstract class AbstractStatLabel implements LabelUpdater {
    private JLabel label;
    protected TypingLogicHandler typingHandler;
    protected double previousValue = -1.0;
    private static final int LABEL_THICKNESS = 2;

    protected AbstractStatLabel(TextPanelComponent textPanelComponent, String initialText) {
	label = new JLabel(initialText, SwingConstants.CENTER);
	this.typingHandler = textPanelComponent.getTypingHandler();
	label.setBorder(BorderFactory.createLineBorder(Color.BLACK, LABEL_THICKNESS));
	label.setOpaque(true);
	label.setBackground(Color.WHITE);
    }

    protected void updateLabel(String format, double newValue) {
	label.setText(String.format(format, newValue));

	if (previousValue < 0) {
	    label.setBackground(Color.WHITE);
	} else if (newValue > previousValue) {
	    label.setBackground(Color.GREEN);
	} else if (newValue < previousValue) {
	    label.setBackground(Color.RED);
	} else {
	    label.setBackground(Color.WHITE);
	}

	previousValue = newValue;
    }

    public JComponent getComponent() {
	return label;
    }

    // Delegate methods to the JLabel if needed
    public void setText(String text) {
	label.setText(text);
    }

    public void setBackground(Color color) {
	label.setBackground(color);
    }
}
