package se.liu.enzcu445;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class SettingsFrame extends JFrame {
    private static final Logger logger = LoggingConfig.getLogger();
    private JTextField wordCountField;
    private JTextField excludeLettersField;
    private SettingsListener settingsListener;

    public SettingsFrame(SettingsListener settingsListener, int initialWordCount, String initialExcludeLetters) {
	this.settingsListener = settingsListener;
	setTitle("Settings");
	setSize(300, 200);
	setLayout(new GridLayout(3, 2));

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
	    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
		if (string != null && string.matches("[a-zA-Z]+")) {
		    super.insertString(fb, offset, string, attr);
		}
	    }

	    @Override
	    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
		if (text != null && text.matches("[a-zA-Z]+")) {
		    super.replace(fb, offset, length, text, attrs);
		}
	    }
	});

	// Spara-knapp
	JButton saveButton = new JButton("Save");
	saveButton.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		saveSettings();
	    }
	});
	add(saveButton);

	setLocationRelativeTo(null);
	setVisible(true);
    }

    private void saveSettings() {
	try {
	    int wordCount = Integer.parseInt(wordCountField.getText());
	    if (wordCount < 10 || wordCount > 200) {
		throw new NumberFormatException("Word count must be between 10 and 200.");
	    }

	    String excludeLetters = excludeLettersField.getText();
	    if (!excludeLetters.matches("[a-zA-Z]*")) {
		throw new IllegalArgumentException("Exclude letters field can only contain English letters.");
	    }

	    // Meddela lyssnaren om de nya inställningarna
	    settingsListener.onSettingsChanged(wordCount, excludeLetters);

	    // Stäng inställningsramen
	    dispose();
	} catch (NumberFormatException e) {
	    logger.warning("Invalid input for word count: " + e.getMessage());
	    JOptionPane.showMessageDialog(this, "Please enter a valid number of words between 10 and 200.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
	} catch (IllegalArgumentException e) {
	    logger.warning("Invalid input for exclude letters: " + e.getMessage());
	    JOptionPane.showMessageDialog(this, e.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
	}
    }
}
