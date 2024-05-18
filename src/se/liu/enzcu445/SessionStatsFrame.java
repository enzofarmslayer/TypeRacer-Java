package se.liu.enzcu445;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SessionStatsFrame extends JFrame {
    public SessionStatsFrame(SessionAccuracyLabel sessionAccuracyLabel, SessionCpmLabel sessionCpmLabel, FrameCloseListener listener) {
	setTitle("Session Stats");
	setSize(300, 200);
	setLocationRelativeTo(null);
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	JPanel panel = new JPanel();
	panel.setLayout(new GridLayout(2, 1));

	panel.add(sessionCpmLabel);
	panel.add(sessionAccuracyLabel);

	add(panel);

	addWindowListener(new WindowAdapter() {
	    @Override
	    public void windowClosing(WindowEvent e) {
		if (listener != null) {
		    listener.onFrameClosed();
		}
	    }
	});
    }
}
