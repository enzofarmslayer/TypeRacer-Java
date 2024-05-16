package se.liu.enzcu445;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class TextPanelComponent extends JPanel {
    private JTextPane sentencePane;
    private SentenceGenerator sentenceGenerator;
    private TypingLogicHandler typingHandler;  // Make sure this is private to encapsulate it properly

    public TextPanelComponent(TypingEventListener typingEventListener) {
	sentencePane = new JTextPane();
	sentencePane.setFont(new Font("SansSerif", Font.BOLD, 16));
	sentencePane.setEditable(false);

	SimpleAttributeSet attrs = new SimpleAttributeSet();
	StyleConstants.setAlignment(attrs, StyleConstants.ALIGN_CENTER);
	StyledDocument doc = sentencePane.getStyledDocument();
	doc.setParagraphAttributes(0, doc.getLength(), attrs, false);

	this.setLayout(new BorderLayout());
	this.add(new JScrollPane(sentencePane), BorderLayout.CENTER);

	Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);
	this.setBorder(blackBorder);

	sentenceGenerator = new SentenceGenerator("resources/wordlist.json");
	typingHandler = new TypingLogicHandler(sentencePane, sentenceGenerator.generateSentence(2), typingEventListener);
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
}
