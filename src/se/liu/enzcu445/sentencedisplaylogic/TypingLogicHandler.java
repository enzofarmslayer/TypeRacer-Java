package se.liu.enzcu445.sentencedisplaylogic;

import se.liu.enzcu445.customeditorkit.WrapEditorKit;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * TypingLogicHandler manages the logic for typing a given sentence within a JTextPane.
 * It tracks typing progress, accuracy, and typing speed, and updates the display accordingly.
 *
 * <p>Responsibilities:</p>
 * <ul>
 *   <li>Displays the target sentence in the JTextPane.</li>
 *   <li>Tracks user input and compares it against the target sentence.</li>
 *   <li>Handles errors and updates the display to reflect correct and incorrect inputs.</li>
 *   <li>Notifies listeners of typing events such as start, completion, and accuracy updates.</li>
 * </ul>
 *
 * @since 1.0
 */
public class TypingLogicHandler {
    private static final Logger LOGGER = Logger.getLogger(TypingLogicHandler.class.getName());

    // Fields used for typing
    private JTextPane textPane;
    private String targetSentence;
    private int typedLength = 0;
    private boolean freeze = false;
    private Set<Integer> errorPositions = new HashSet<>();
    private TypingEventListener typingEventListener;
    private boolean typingStarted = false;

    // Fields for accuracy and CPM calculations
    private double currentAccuracy;
    private double sumAccuracy;
    private int accuracyCount;
    private double totalCPM = 0.0;
    private int sessionCount = 0;

    public TypingLogicHandler(JTextPane textPane, String sentence, TypingEventListener typingEventListener) {
	this.textPane = textPane;
	this.targetSentence = sentence;
	this.typingEventListener = typingEventListener;
	this.currentAccuracy = 0.0;
	this.sumAccuracy = 0.0;
	this.accuracyCount = 0;

	// Set the editor kit to enable wrapping
	textPane.setEditorKit(new WrapEditorKit());

	if (this.targetSentence.equals("No words available for sentence generation.")) {
	    this.freeze = true; // Freeze the typing logic
	}

	displaySentence();

	textPane.addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyTyped(KeyEvent e) {
		if (!freeze) { // Check if typing is paused through PauseController
		    handleKeyTyped(e);
		}
	    }
	});

	// Initial caret position
	textPane.setCaretPosition(typedLength);
    }

    public void setTypingEventListener(TypingEventListener listener) {
	this.typingEventListener = listener;
    }

    public void setFreeze(boolean freezeCondition) {
	freeze = freezeCondition;
    }

    public void displaySentence() {
	try {
	    StyledDocument doc = textPane.getStyledDocument();
	    doc.remove(0, doc.getLength());

	    SimpleAttributeSet center = new SimpleAttributeSet();
	    StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
	    doc.setParagraphAttributes(0, doc.getLength(), center, false);

	    doc.insertString(0, targetSentence, null);
	} catch (BadLocationException e) {
	    LOGGER.log(Level.SEVERE, "Failed to display sentence", e);
	    System.exit(1);
	}
	updateCaretPosition();
    }

    public void resetVariables() {
	typedLength = 0;
	errorPositions.clear();
	typingStarted = false;
	freeze = false;
	updateCaretPosition();
    }

    public void setTargetSentence(String sentence) {
	this.targetSentence = sentence;
	if (this.targetSentence.equals("No words available for sentence generation.")) {
	    this.freeze = true; // Freeze the typing logic
	}
	displaySentence();
    }

    public void updateSettings(String newTargetSentence) {
	resetVariables();
	setTargetSentence(newTargetSentence);
    }

    private void handleKeyTyped(KeyEvent e) {
	if (!typingStarted) {
	    typingStarted = true;
	    if (typingEventListener != null) {
		typingEventListener.onTypingStarted();
	    }
	}

	char keyChar = e.getKeyChar();
	if (typedLength < targetSentence.length()) {
	    char targetChar = targetSentence.charAt(typedLength);
	    if ((keyChar == ' ' && targetChar == '•') ||keyChar == targetChar) {
		applyStyleToChar(typedLength, Color.GRAY);
		typedLength++;
		updateCaretPosition();
		if (typedLength == targetSentence.length()) {
		    if (typingEventListener != null) {
			typingEventListener.onTypingCompleted();
		    }
		}
	    } else {
		applyStyleToChar(typedLength, Color.RED);
		errorPositions.add(typedLength); // Register an error at this position
		updateCaretPosition();
	    }
	}
    }

    private void applyStyleToChar(int position, Color color) {
	SimpleAttributeSet simpleAttributeSet = new SimpleAttributeSet();
	StyleConstants.setForeground(simpleAttributeSet, color);
	StyledDocument doc = textPane.getStyledDocument();
	doc.setCharacterAttributes(position, 1, simpleAttributeSet, false);
    }

    private void updateCaretPosition() {
	SwingUtilities.invokeLater(() -> textPane.setCaretPosition(typedLength));
    }

    public int getTypedLength() {
	return typedLength;
    }

    public int getTypingErrorCount() {
	return errorPositions.size(); // Number of unique error positions
    }

    public double calculateAccuracy() {
	int totalKeyPresses = typedLength;
	if (totalKeyPresses == 0) {
	    return 100.0; // If nothing typed yet, accuracy is 100%
	}
	int correctKeyPresses = totalKeyPresses - getTypingErrorCount();
	currentAccuracy = ((double) correctKeyPresses / totalKeyPresses) * 100;
	return currentAccuracy;
    }

    public void saveCurrentAccuracy() {
	currentAccuracy = calculateAccuracy();
	sumAccuracy += currentAccuracy;
	accuracyCount++;
    }

    public double calculateAverageAccuracy() {
	if (accuracyCount == 0) {
	    return 100.0; // Default to 100% if no accuracy records
	}
	return sumAccuracy / accuracyCount;
    }

    public void saveCurrentCPM(double cpm) {
	totalCPM += cpm;
	sessionCount++;
    }

    public double calculateAverageCpm() {
	if (sessionCount == 0) {
	    return 0.0;
	}
	return totalCPM / sessionCount;
    }
}
