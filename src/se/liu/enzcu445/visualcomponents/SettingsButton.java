package se.liu.enzcu445.visualcomponents;

import se.liu.enzcu445.listener.SettingsListener;

import javax.swing.*;


/**
 * SettingsButton is a custom JButton that opens a settings frame when clicked.
 * It interacts with a {@link SettingsListener} to pass initial settings for word count and excluded letters.
 *
 * <p>Responsibilities:</p>
 * <ul>
 *   <li>Initializes the button with a label and sets up an action listener.</li>
 *   <li>Opens the {@link SettingsFrame} when the button is clicked.</li>
 * </ul>
 *
 * @since 1.0
 */
public class SettingsButton extends JButton {

    public SettingsButton(SettingsListener settingsListener, int initialWordCount, String initialExcludeLetters) {
	super("Settings");
	setFocusable(false);

	addActionListener(e -> new SettingsFrame(settingsListener, initialWordCount, initialExcludeLetters));
    }
}
