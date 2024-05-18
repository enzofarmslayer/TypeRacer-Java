package se.liu.enzcu445;

import javax.swing.*;
import java.awt.*;

public class AverageAccuracyLabel extends JLabel {
    private TypingLogicHandler typingHandler;
    private double previousAccuracy = -1.0;  // Initialize to -1 to indicate no previous accuracy value

    public AverageAccuracyLabel(TextPanelComponent textPanelComponent) {
	super("Average Accuracy: --", SwingConstants.CENTER);  // Set initial text and alignment
	this.typingHandler = textPanelComponent.getTypingHandler();
	setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	setOpaque(true);
	setBackground(Color.WHITE);
    }

    public void updateAverageAccuracyLabel() {
	typingHandler.saveCurrentAccuracy();
	double averageAccuracy = typingHandler.calculateAverageAccuracy();
	setText(String.format("Average Accuracy: %.2f%%", averageAccuracy));

	// Change background color based on accuracy comparison
	if (previousAccuracy < 0) {
	    // First time update, set to white
	    setBackground(Color.WHITE);
	} else if (averageAccuracy > previousAccuracy) {
	    setBackground(Color.GREEN);
	} else if (averageAccuracy < previousAccuracy) {
	    setBackground(Color.RED);
	} else {
	    setBackground(Color.WHITE);
	}

	// Update previous accuracy
	previousAccuracy = averageAccuracy;
    }
}
