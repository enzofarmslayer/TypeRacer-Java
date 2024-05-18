package se.liu.enzcu445;

import javax.swing.*;

public class TypingEventHandler implements TypingEventListener, FrameCloseListener {
    private Timer timer;
    private AverageAccuracyLabel averageAccuracyLabel;
    private AverageCpmLabel averageCpmLabel;
    private PauseButton pauseButton;
    private SessionAccuracyLabel sessionAccuracyLabel;
    private SessionCpmLabel sessionCpmLabel;
    private TextPanelComponent textPanel;

    public TypingEventHandler(Timer timer, AverageAccuracyLabel averageAccuracyLabel, AverageCpmLabel averageCpmLabel,
			      PauseButton pauseButton, SessionAccuracyLabel sessionAccuracyLabel, SessionCpmLabel sessionCpmLabel,
			      TextPanelComponent textPanel) {
	this.timer = timer;
	this.averageAccuracyLabel = averageAccuracyLabel;
	this.averageCpmLabel = averageCpmLabel;
	this.pauseButton = pauseButton;
	this.sessionAccuracyLabel = sessionAccuracyLabel;
	this.sessionCpmLabel = sessionCpmLabel;
	this.textPanel = textPanel;
    }

    @Override
    public void onTypingStarted() {
	timer.start();
    }

    @Override
    public void onTypingCompleted() {
	timer.stop();
	averageAccuracyLabel.updateAverageAccuracyLabel();
	averageCpmLabel.updateAverageCpmLabel();

	sessionAccuracyLabel.setSessionAccuracyLabel();
	sessionCpmLabel.setSessionCpmLabel();
	pauseButton.disableButton();

	// Create and display the session stats frame
	SessionStatsFrame statsFrame = new SessionStatsFrame(sessionAccuracyLabel, sessionCpmLabel, this);
	statsFrame.setVisible(true);
    }

    @Override
    public void onFrameClosed() {
	// Reset necessary variables
	TypingLogicHandler typingHandler = textPanel.getTypingHandler();
	typingHandler.resetVariables();

	// Generate a new sentence
	String newSentence = textPanel.getSentenceGenerator().generateSentence(2);
	typingHandler.setTargetSentence(newSentence);

	// Reset the display sentence in the text panel
	textPanel.resetDisplaySentence();

	timer.reset();

	// Enable the pause button
	pauseButton.enableButton();
    }
}
