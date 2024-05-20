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

    // Fields for accuracy and CPM calculations
    private double currentAccuracy;
    private double sumAccuracy;
    private int accuracyCount;
    private double totalCpm = 0.0;
    private int cpmCount = 0;

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

    void displaySentence() {
	try {
	    StyledDocument doc = textPane.getStyledDocument();
	    doc.remove(0, doc.getLength());

	    SimpleAttributeSet center = new SimpleAttributeSet();
	    StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
	    doc.setParagraphAttributes(0, doc.getLength(), center, false);

	    doc.insertString(0, targetSentence, null);
	} catch (BadLocationException e) {
	    e.printStackTrace();
	}
	updateCaretPosition();
    }

    public void resetVariables() {
	typedLength = 0;
	errorPositions.clear();
	isFinished = false;
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

    public void saveCurrentCpm(double cpm) {
	totalCpm += cpm;
	cpmCount++;
    }

    public double calculateAverageCpm() {
	if (cpmCount == 0) {
	    return 0.0;
	}
	return totalCpm / cpmCount;
    }
}
