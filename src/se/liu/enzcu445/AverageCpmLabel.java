package se.liu.enzcu445;

import javax.swing.*;
import java.awt.*;

public class AverageCpmLabel extends JLabel {
    private TypingLogicHandler typingHandler;
    private Timer timer;
    private double previousCpm = -1.0;  // Initialize to -1 to indicate no previous CPM value

    public AverageCpmLabel(TextPanelComponent textPanelComponent, Timer timer) {
	super("Average CPM: --", SwingConstants.CENTER);  // Set initial text and alignment
	this.typingHandler = textPanelComponent.getTypingHandler();
	this.timer = timer;
	setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	setOpaque(true);
	setBackground(Color.WHITE);
    }

    public void updateAverageCpmLabel() {
	int hundredthsElapsed = timer.getHundredthsElapsed();
	int typedLength = typingHandler.getTypedLength();

	if (hundredthsElapsed == 0) {
	    setText("Average CPM: --");
	    setBackground(Color.WHITE);
	} else {
	    double secondsElapsed = hundredthsElapsed / 100.0;
	    double currentCpm = ((double) typedLength / secondsElapsed) * 60;
	    typingHandler.saveCurrentCpm(currentCpm);
	    double averageCpm = typingHandler.calculateAverageCpm();

	    setText(String.format("Average CPM: %.2f", averageCpm));

	    // Change background color based on CPM comparison
	    if (previousCpm < 0) {
		// First time update, set to white
		setBackground(Color.WHITE);
	    } else if (currentCpm > previousCpm) {
		setBackground(Color.GREEN);
	    } else if (currentCpm < previousCpm) {
		setBackground(Color.RED);
	    } else {
		setBackground(Color.WHITE);
	    }

	    // Update previous CPM
	    previousCpm = currentCpm;
	}
    }
}
