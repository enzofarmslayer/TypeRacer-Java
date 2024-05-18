package se.liu.enzcu445;

import javax.swing.*;
import java.awt.*;

public class SessionCpmLabel extends JLabel {
    private TypingLogicHandler typingHandler;
    private Timer timer;
    private double currentCpm = 0.0;

    public SessionCpmLabel(TextPanelComponent textPanelComponent, Timer timer) {
	super("Session CPM: --", SwingConstants.CENTER);
	this.typingHandler = textPanelComponent.getTypingHandler();
	this.timer = timer;
	setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	setOpaque(true);
	setBackground(Color.WHITE);
	setSessionCpmLabel();
    }

    public void setSessionCpmLabel() {
	int hundredthsElapsed = timer.getHundredthsElapsed();
	int typedLength = typingHandler.getTypedLength();

	if (hundredthsElapsed == 0) {
	    setText("Session CPM: --");
	} else {
	    double secondsElapsed = hundredthsElapsed / 100.0;
	    currentCpm = ((double) typedLength / secondsElapsed) * 60;
	    setText(String.format("Session CPM: %.2f", currentCpm));
	}
    }

    public double getSessionCpm() {
	return currentCpm;
    }
}
