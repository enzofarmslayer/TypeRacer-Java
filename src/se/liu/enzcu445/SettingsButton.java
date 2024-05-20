package se.liu.enzcu445;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsButton extends JButton {

    public SettingsButton(SettingsListener settingsListener, int initialWordCount, String initialExcludeLetters) {
	super("Settings");
	setFocusable(false);

	addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		new SettingsFrame(settingsListener, initialWordCount, initialExcludeLetters);
	    }
	});
    }
}
