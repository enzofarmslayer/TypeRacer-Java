package se.liu.enzcu445.settings;

import se.liu.enzcu445.LoggingConfig;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.util.logging.Logger;

/**
 * SettingsFrame is a JFrame that allows users to adjust settings such as the word count and excluded letters.
 * It interacts with a {@link SettingsListener} to notify about changes in settings.
 *
 * <p>Responsibilities:</p>
 * <ul>
 *   <li>Displays fields for word count and excluded letters.</li>
 *   <li>Restricts input to valid values using a DocumentFilter.</li>
 *   <li>Saves settings and notifies the listener when the save button is clicked.</li>
 * </ul>
 *
 * @since 1.0
 */
public class SettingsViewer extends JFrame {
    private static final Logger LOGGER = LoggingConfig.getLogger();
    private JTextField wordCountField;
    private JTextField excludeLettersField;
    private SettingsListener settingsListener;
    private static final int SETTINGS_FRAME_WIDTH = 300;
    private static final int SETTINGS_FRAME_HEIGHT = 200;
    private static final int SETTINGS_FRAME_ROWS = 3;
    private static final int SETTINGS_FRAME_COLUMNS = 2;
    private static final int LOWEST_WORD_COUNT = 10;
    private static final int HIGHEST_WORD_COUNT = 200;



    public SettingsViewer(SettingsListener settingsListener, int initialWordCount, String initialExcludeLetters) {
	this.settingsListener = settingsListener;
	setTitle("Settings");
	setSize(SETTINGS_FRAME_WIDTH, SETTINGS_FRAME_HEIGHT);
	setLayout(new GridLayout(SETTINGS_FRAME_ROWS, SETTINGS_FRAME_COLUMNS));

	// Antal ord inställningar
	add(new JLabel("Number of words:"));
	wordCountField = new JTextField(String.valueOf(initialWordCount));
	add(wordCountField);

	// Uteslut bokstäver inställningar
	add(new JLabel("Exclude letters:"));
	excludeLettersField = new JTextField(initialExcludeLetters);
	add(excludeLettersField);

	// Add DocumentFilter to restrict input to English letters only
	((AbstractDocument) excludeLettersField.getDocument()).setDocumentFilter(new DocumentFilter() {
	    @Override
	    public void insertString(FilterBypass fb, int offset, String excludedLetters, AttributeSet attribute) throws BadLocationException {
		if (excludedLetters != null && excludedLetters.matches("[a-zA-Z]+")) {
		    super.insertString(fb, offset, excludedLetters, attribute);
		}
	    }

	    @Override
	    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attribute) throws BadLocationException {
		if (text != null && text.matches("[a-zA-Z]+")) {
		    super.replace(fb, offset, length, text, attribute);
		}
	    }
	});

	// Spara-knapp
	JButton saveButton = new JButton("Save");
	saveButton.addActionListener(e -> saveSettings());
	add(saveButton);

	setLocationRelativeTo(null);
	setVisible(true);
    }

    private void saveSettings() {
	try {
	    int wordCount = validateWordCount(wordCountField.getText());
	    String excludeLetters = validateExcludeLetters(excludeLettersField.getText());

	    // Meddela lyssnaren om de nya inställningarna
	    settingsListener.onSettingsChanged(wordCount, excludeLetters);

	    // Stäng inställningsramen
	    dispose();
	} catch (InvalidSettingsException e) {
	    LOGGER.warning("Invalid settings: " + e.getMessage());
	    JOptionPane.showMessageDialog(this, e.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
	}
    }

    private int validateWordCount(String text) throws InvalidSettingsException {
	    int wordCount = Integer.parseInt(text);
	    if (wordCount < LOWEST_WORD_COUNT || wordCount > HIGHEST_WORD_COUNT) {
		throw new InvalidSettingsException("Word count must be between 10 and 200.");
	    }
	    return wordCount;
    }

    private String validateExcludeLetters(String text) throws InvalidSettingsException {
	if (text.matches("[a-zA-Z]*")) {
	    return text;
	} else {
	    throw new InvalidSettingsException("Exclude letters field can only contain English letters.");
	}
    }
}
