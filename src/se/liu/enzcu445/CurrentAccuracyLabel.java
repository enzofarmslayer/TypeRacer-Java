package se.liu.enzcu445;

import javax.swing.*;

public class CurrentAccuracyLabel extends JLabel
{
    private TypingLogicHandler typingHandler;

    public CurrentAccuracyLabel(TextPanelComponent textPanelComponent) {
	this.typingHandler = textPanelComponent.getTypingHandler();
	setAccuracyLabel();
    }

    private void setAccuracyLabel() {
	double currentAccuracy = typingHandler.calculateAccuracy();
	setText(String.format("Current Accuracy: %.2f%%", currentAccuracy));
    }
}
