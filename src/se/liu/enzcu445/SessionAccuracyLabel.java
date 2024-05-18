package se.liu.enzcu445;

import javax.swing.*;
import java.awt.*;

public class SessionAccuracyLabel extends JLabel {
    private TypingLogicHandler typingHandler;
    private double currentAccuracy = 0.0;

    public SessionAccuracyLabel(TextPanelComponent textPanelComponent) {
	super("Session Accuracy: --", SwingConstants.CENTER);
	this.typingHandler = textPanelComponent.getTypingHandler();
	setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	setOpaque(true);
	setBackground(Color.WHITE);
	setSessionAccuracyLabel();
    }

    public void setSessionAccuracyLabel() {
	currentAccuracy = typingHandler.calculateAccuracy();
	setText(String.format("Session Accuracy: %.2f%%", currentAccuracy));
    }

    public double getCurrentAccuracy() {
	return currentAccuracy;
    }
}
