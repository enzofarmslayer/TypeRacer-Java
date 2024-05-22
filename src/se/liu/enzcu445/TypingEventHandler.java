package se.liu.enzcu445;

import java.util.logging.Logger;

/**
 * TypingEventHandler handles typing events and updates various components in the application.
 * It implements {@link TypingEventListener}, {@link FrameCloseListener}, and {@link SettingsListener}.
 *
 * <p>Responsibilities:</p>
 * <ul>
 *   <li>Starts and stops a timer when typing starts and completes.</li>
 *   <li>Updates statistical labels upon typing completion.</li>
 *   <li>Manages the session stats frame.</li>
 *   <li>Resets variables and updates settings when the frame is closed or settings are changed.</li>
 * </ul>
 *
 * @since 1.0
 */
public class TypingEventHandler implements TypingEventListener, FrameCloseListener, SettingsListener {
    private static final Logger LOGGER = Logger.getLogger(TypingEventHandler.class.getName());
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
	LOGGER.info("User started writing");
	timer.start();
	pauseButton.enableButton();
    }

    @Override
    public void onTypingCompleted() {
	LOGGER.info("User completed writing");
	timer.stop();
	averageAccuracyLabel.update();
	averageCpmLabel.update();
	sessionAccuracyLabel.update();
	sessionCpmLabel.update();
	pauseButton.disableButton();

	// Create and display the session stats frame
	SessionStatsFrame statsFrame = new SessionStatsFrame(sessionAccuracyLabel, sessionCpmLabel, this);
	statsFrame.setVisible(true);
	LOGGER.info("SessionStatsFrame is visible");
    }

    @Override
    public void onFrameClosed() {
	LOGGER.info("SessionStatsFrame has closed");
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
	LOGGER.info("Settings have been changed");
	textPanel.updateSettings(wordCount, excludeLetters);
	timer.reset();
    }
}
