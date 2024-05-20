package se.liu.enzcu445;

import javax.swing.*;
import java.awt.*;

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
