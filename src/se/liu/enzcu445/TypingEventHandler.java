package se.liu.enzcu445;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TypingEventHandler implements TypingEventListener, FrameCloseListener, SettingsListener {
    private static final Logger logger = Logger.getLogger(TypingEventHandler.class.getName());
    private Timer timer;
    private AbstractStatLabel averageAccuracyLabel;
    private AbstractStatLabel averageCpmLabel;
    private PauseButton pauseButton;
    private AbstractStatLabel sessionAccuracyLabel;
    private AbstractStatLabel sessionCpmLabel;
    private TextPanelComponent textPanel;

    public TypingEventHandler(Timer timer, AbstractStatLabel averageAccuracyLabel, AbstractStatLabel averageCpmLabel,
			      PauseButton pauseButton, AbstractStatLabel sessionAccuracyLabel, AbstractStatLabel sessionCpmLabel,
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
	logger.info("User started writing");
	timer.start();
	pauseButton.enableButton();
    }

    @Override
    public void onTypingCompleted() {
	logger.info("User completed writing");
	timer.stop();
	averageAccuracyLabel.update();
	averageCpmLabel.update();
	sessionAccuracyLabel.update();
	sessionCpmLabel.update();
	pauseButton.disableButton();

	// Create and display the session stats frame
	SessionStatsFrame statsFrame = new SessionStatsFrame(sessionAccuracyLabel, sessionCpmLabel, this);
	statsFrame.setVisible(true);
	logger.info("SessionStatsFrame is visible");
    }

    @Override
    public void onFrameClosed() {
	logger.info("SessionStatsFrame has closed");
	// Reset necessary variables
	TypingLogicHandler typingHandler = textPanel.getTypingHandler();
	typingHandler.resetVariables();

	// Generate a new sentence
	String newSentence = textPanel.getSentenceGenerator().generateSentence();
	typingHandler.setTargetSentence(newSentence);

	// Reset the display sentence in the text panel
	textPanel.resetDisplaySentence();

	timer.reset();

	// Enable the pause button
	pauseButton.enableButton();
    }

    @Override
    public void onSettingsChanged(int wordCount, String excludeLetters) {
	logger.info("Settings have been changed");
	textPanel.updateSettings(wordCount, excludeLetters);
	timer.reset();
    }
}
