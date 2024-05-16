package se.liu.enzcu445;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

public class TypingLogicHandler {
    private JTextPane textPane;
    private String targetSentence;
    private int typedLength = 0;
    private boolean freeze = false;
    private Set<Integer> errorPositions = new HashSet<>();
    private boolean isFinished = false;
    private TypingEventListener typingEventListener;
    private boolean typingStarted = false;

    private double currentAccuracy;
    private double sumAccuracy;
    private int accuracyCount;

    public TypingLogicHandler(JTextPane textPane, String sentence, TypingEventListener typingEventListener) {
	this.textPane = textPane;
	this.targetSentence = sentence;
	this.typingEventListener = typingEventListener;
	this.currentAccuracy = 0.0;
	this.sumAccuracy = 0.0;
	this.accuracyCount = 0;

	displaySentence();

	textPane.addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyPressed(KeyEvent e) {
		if (!freeze) { // Check if typing is paused through PauseController
		    handleKeyPress(e);
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

    private void displaySentence() {
	try {
	    StyledDocument doc = textPane.getStyledDocument();
	    doc.remove(0, doc.getLength());
	    doc.insertString(0, targetSentence, null);
	} catch (BadLocationException e) {
	    e.printStackTrace();
	}
    }

    private void handleKeyPress(KeyEvent e) {
	if (!typingStarted) {
	    typingStarted = true;
	    if (typingEventListener != null) {
		typingEventListener.onTypingStarted();
	    }
	}

	char keyChar = e.getKeyChar();
	if (typedLength < targetSentence.length()) {
	    char targetChar = targetSentence.charAt(typedLength);
	    // Map spacebar press to "•"
	    if ((keyChar == ' ' && targetChar == '•') || keyChar == targetChar) {
		applyStyleToChar(typedLength, Color.GRAY);
		typedLength++;
		updateCaretPosition();
		if (typedLength == targetSentence.length()) {
		    isFinished = true;
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
	try {
	    SimpleAttributeSet attrs = new SimpleAttributeSet();
	    StyleConstants.setForeground(attrs, color);
	    StyledDocument doc = textPane.getStyledDocument();
	    doc.setCharacterAttributes(position, 1, attrs, false);
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
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
	this.currentAccuracy = calculateAccuracy();
	this.sumAccuracy += currentAccuracy;
	this.accuracyCount++;
    }

    public double getCurrentAccuracy() {
	return currentAccuracy;
    }

    public double getSumAccuracy() {
	return sumAccuracy;
    }

    public double calculateAverageAccuracy() {
	if (accuracyCount == 0) {
	    return 100.0; // Default to 100% if no accuracy records
	}
	return sumAccuracy / accuracyCount;
    }
}
