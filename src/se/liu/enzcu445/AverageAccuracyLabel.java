package se.liu.enzcu445;

import javax.swing.*;

public class AverageAccuracyLabel extends JLabel
{
    private TypingLogicHandler typingHandler;

    public AverageAccuracyLabel(TextPanelComponent textPanelComponent) {
	this.typingHandler = textPanelComponent.getTypingHandler();
	setText("Average Accuracy: --");
    }

    public void updateAccuracyLabel() {
	double averageAccuracy = typingHandler.calculateAccuracy();
	setText(String.format("Average Accuracy: %.2f%%", averageAccuracy));
    }
}
