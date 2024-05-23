package se.liu.enzcu445.settings;

import javax.swing.*;

/**
 * SettingsButton is a custom JButton that opens a settings frame when clicked.
 * It interacts with a {@link SettingsListener} to pass initial settings for word count and excluded letters.
 *
 * <p>Responsibilities:</p>
 * <ul>
 *   <li>Initializes the button with a label and sets up an action listener.</li>
 *   <li>Opens the {@link SettingsViewer} when the button is clicked.</li>
 * </ul>
 *
 * @since 1.0
 */
public class SettingsButton extends JButton {

    private final SettingsListener settingsListener;
    private final int initialWordCount;
    private final String initialExcludeLetters;

    public SettingsButton(SettingsListener settingsListener, int initialWordCount, String initialExcludeLetters) {
	super("Settings");
	setFocusable(false);

	this.settingsListener = settingsListener;
	this.initialWordCount = initialWordCount;
	this.initialExcludeLetters = initialExcludeLetters;

	initializeButton();
    }

    private void initializeButton() {
	addActionListener(e -> new SettingsViewer(settingsListener, initialWordCount, initialExcludeLetters));
    }
}
