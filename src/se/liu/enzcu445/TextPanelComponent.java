package se.liu.enzcu445;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.*;
import java.awt.*;
import java.util.logging.Logger;

/**
 * TextPanelComponent is a custom JPanel that displays and manages the text panel component for the SpeedTyper application.
 * It handles the generation of sentences and the logic for typing events.
 *
 * <p>Responsibilities:</p>
 * <ul>
 *   <li>Initializes and configures the text panel with a sentence generator and typing handler.</li>
 *   <li>Generates sentences based on the provided settings and updates the display.</li>
 *   <li>Handles typing completion events and updates the typing handler settings.</li>
 * </ul>
 *
 * @since 1.0
 */
public class TextPanelComponent extends JPanel {
    private static final Logger LOGGER = LoggingConfig.getLogger();
    private SentenceGenerator sentenceGenerator;
    private TypingLogicHandler typingHandler;

    public TextPanelComponent(TypingEventListener typingEventListener, int wordCount, String excludedLetters) throws TextPanelException {

	JTextPane sentencePane = new JTextPane();
	sentencePane.setFont(new Font("SansSerif", Font.BOLD, 16));
	sentencePane.setEditable(false);
	sentencePane.setPreferredSize(new Dimension(800, 200));  // Adjust size as needed
	sentencePane.setBackground(Color.WHITE);

	// Set the editor kit to enable wrapping
	sentencePane.setEditorKit(new WrapEditorKit());

	SimpleAttributeSet attributeSet = new SimpleAttributeSet();
	StyleConstants.setAlignment(attributeSet, StyleConstants.ALIGN_CENTER);
	StyledDocument doc = sentencePane.getStyledDocument();
	doc.setParagraphAttributes(0, doc.getLength(), attributeSet, false);

	this.setLayout(new BorderLayout());
	this.add(new JScrollPane(sentencePane), BorderLayout.CENTER);

	Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);
	this.setBorder(blackBorder);

	try {
	    sentenceGenerator = new SentenceGenerator("wordlist.json", wordCount, excludedLetters);
	    typingHandler = new TypingLogicHandler(sentencePane, generateSentence(), typingEventListener);
	} catch (SentenceGeneratorException | TypingLogicException e) {
	    LOGGER.severe("Failed to create inner components: " + e.getMessage());
	    throw new TextPanelException("Failed to create inner components",e);
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
	typingHandler.setTargetSentence(generateSentence());
	typingHandler.displaySentence();
    }

    public void updateSettings(int wordCount, String excludeLetters) {
	sentenceGenerator.setWordCount(wordCount);
	sentenceGenerator.setExcludeLetters(excludeLetters);

	String newSentence = generateSentence();
	typingHandler.updateSettings(newSentence);

	resetDisplaySentence();
	LOGGER.info("Settings updated: wordCount=" + wordCount + ", excludeLetters=" + excludeLetters);
    }

    private String generateSentence() {
	return sentenceGenerator.generateSentence();
    }
}
