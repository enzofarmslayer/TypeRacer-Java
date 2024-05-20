package se.liu.enzcu445;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.*;
import java.awt.*;
import java.util.logging.Logger;

public class TextPanelComponent extends JPanel {
    private static final Logger logger = LoggingConfig.getLogger();
    private JTextPane sentencePane;
    private SentenceGenerator sentenceGenerator;
    private TypingLogicHandler typingHandler;

    private int wordCount;
    private String excludeLetters;

    public TextPanelComponent(TypingEventListener typingEventListener, int wordCount, String excludeLetters) {
	this.wordCount = wordCount;
	this.excludeLetters = excludeLetters;

	sentencePane = new JTextPane();
	sentencePane.setFont(new Font("SansSerif", Font.BOLD, 16));
	sentencePane.setEditable(false);
	sentencePane.setPreferredSize(new Dimension(800, 200));  // Adjust size as needed
	sentencePane.setBackground(Color.WHITE);

	// Set the editor kit to enable wrapping
	sentencePane.setEditorKit(new WrapEditorKit());

	SimpleAttributeSet attrs = new SimpleAttributeSet();
	StyleConstants.setAlignment(attrs, StyleConstants.ALIGN_CENTER);
	StyledDocument doc = sentencePane.getStyledDocument();
	doc.setParagraphAttributes(0, doc.getLength(), attrs, false);

	this.setLayout(new BorderLayout());
	this.add(new JScrollPane(sentencePane), BorderLayout.CENTER);

	Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);
	this.setBorder(blackBorder);

	try {
	    sentenceGenerator = new SentenceGenerator("wordlist.json", wordCount, excludeLetters);
	    typingHandler = new TypingLogicHandler(sentencePane, generateSentence(), typingEventListener);
	} catch (SentenceGeneratorException | TypingLogicException e) {
	    throw new RuntimeException(e);
	}
    }

    public void setTypingCompletionHandler(TypingEventListener typingEventListener) {
	typingHandler.setTypingEventListener(typingEventListener);
    }

    public TypingLogicHandler getTypingHandler() {
	return typingHandler;
    }

    public SentenceGenerator getSentenceGenerator() {
	return sentenceGenerator;
    }

    public void resetDisplaySentence() {
	try {
	    typingHandler.setTargetSentence(generateSentence());
	    typingHandler.displaySentence();
	} catch (TypingLogicException e) {
	    throw new RuntimeException(e);
	}
    }

	public void updateSettings(int wordCount, String excludeLetters) {
	this.wordCount = wordCount;
	this.excludeLetters = excludeLetters;
	sentenceGenerator.setWordCount(wordCount);
	sentenceGenerator.setExcludeLetters(excludeLetters);

	String newSentence = generateSentence();
	typingHandler.updateSettings(newSentence);

	resetDisplaySentence();
	logger.info("Settings updated: wordCount=" + wordCount + ", excludeLetters=" + excludeLetters);
    }

    private String generateSentence() {
	return sentenceGenerator.generateSentence();
    }
}
