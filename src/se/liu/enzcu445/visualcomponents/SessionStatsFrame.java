package se.liu.enzcu445.visualcomponents;

import se.liu.enzcu445.listener.FrameCloseListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * SessionStatsFrame is a JFrame that displays session statistics including accuracy and CPM.
 * It manages a window that shows the provided {@link AbstractStatLabel} components for session accuracy and CPM.
 *
 * @since 1.0
 */
public class SessionStatsFrame extends JFrame {
    public SessionStatsFrame(AbstractStatLabel sessionAccuracyLabel, AbstractStatLabel sessionCpmLabel, FrameCloseListener listener) {
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
