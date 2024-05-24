package se.liu.enzcu445.settings;

import se.liu.enzcu445.LoggingConfig;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.util.logging.Logger;

/**
 * SettingsFrame is a custom component that allows users to adjust settings such as the word count and excluded letters.
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
public class SettingsViewer
{
    private static final Logger LOGGER = LoggingConfig.getLogger();
    private final int initialWordCount;
    private final String initialExcludeLetters;
    private JTextField wordCountField = null;
    private JTextField excludeLettersField = null;
    private SettingsListener settingsListener = null;
    private JFrame frame = null;
    private static final int SETTINGS_FRAME_WIDTH = 300;
    private static final int SETTINGS_FRAME_HEIGHT = 200;
    private static final int SETTINGS_FRAME_ROWS = 3;
    private static final int SETTINGS_FRAME_COLUMNS = 2;
    private static final int LOWEST_WORD_COUNT = 10;
    private static final int HIGHEST_WORD_COUNT = 200;

    public SettingsViewer(SettingsListener settingsListener, int initialWordCount, String initialExcludeLetters) {
	this.settingsListener = settingsListener;
	this.initialWordCount = initialWordCount;
	this.initialExcludeLetters = initialExcludeLetters;
    }

    private void initializeFrame(){
	frame = new JFrame("Settings");
	frame.setSize(SETTINGS_FRAME_WIDTH, SETTINGS_FRAME_HEIGHT);
	frame.setLayout(new GridLayout(SETTINGS_FRAME_ROWS, SETTINGS_FRAME_COLUMNS));

	/** Number of words settings **/
	frame.add(new JLabel("Number of words:"));
	wordCountField = new JTextField(String.valueOf(initialWordCount));
	frame.add(wordCountField);

	/** Exclude letters settings **/
	frame.add(new JLabel("Exclude letters:"));
	excludeLettersField = new JTextField(initialExcludeLetters);
	frame.add(excludeLettersField);

	/**
	 * Adds a DocumentFilter to restrict input to English letters only.
	 * This filter ensures that only alphabetic characters (a-z, A-Z) are allowed in the excludeLettersField.
	 */
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

	/** Save Settings Button **/
	JButton saveButton = new JButton("Save");
	saveButton.addActionListener(e -> saveSettings());
	frame.add(saveButton);

	frame.setLocationRelativeTo(null);
    }

    /**
     * Saves the settings entered by the user.
     * This method validates the word count and excluded letters, notifies the listener of the new settings,
     * and closes the settings frame.
     */
    private void saveSettings() {
	try {
	    int wordCount = validateWordCount(wordCountField.getText());
	    String excludeLetters = validateExcludeLetters(excludeLettersField.getText());

	    settingsListener.onSettingsChanged(wordCount, excludeLetters);

	    frame.dispose();
	} catch (InvalidSettingsException e) {
	    LOGGER.warning("Invalid settings: " + e.getMessage());
	    JOptionPane.showMessageDialog(frame, e.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
	}
    }

    /**
     * Validates the word count entered by the user.
     * Ensures that the word count is within the allowed range.
     *
     * @param text The word count as a string.
     * @return The validated word count as an integer.
     * @throws InvalidSettingsException if the word count is not within the allowed range.
     */
    private int validateWordCount(String text) throws InvalidSettingsException {
	int wordCount = Integer.parseInt(text);
	if (wordCount < LOWEST_WORD_COUNT || wordCount > HIGHEST_WORD_COUNT) {
	    throw new InvalidSettingsException("Word count must be between 10 and 200.");
	}
	return wordCount;
    }

    /**
     * Validates the excluded letters entered by the user.
     * Ensures that the excluded letters field contains only English letters.
     *
     * @param text The excluded letters as a string.
     * @return The validated excluded letters string.
     * @throws InvalidSettingsException if the excluded letters contain non-alphabetic characters.
     */
    private String validateExcludeLetters(String text) throws InvalidSettingsException {
	if (text.matches("[a-zA-Z]*")) {
	    return text;
	} else {
	    throw new InvalidSettingsException("Exclude letters field can only contain English letters.");
	}
    }

    public void showSettingsFrame() {
	initializeFrame();
	frame.setVisible(true);
    }
}
