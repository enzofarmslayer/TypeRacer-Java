package se.liu.enzcu445.statlabels;

import se.liu.enzcu445.sentencedisplaylogic.FrameCloseListener;

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
    private static final int SESSION_STATS_FRAME_WIDTH = 300;
    private static final int SESSION_STATS_FRAME_HEIGHT = 200;
    private static final int SESSION_STATS_FRAME_ROWS = 2;
    private static final int SESSION_STATS_FRAME_COLUMNS = 1;

    public SessionStatsFrame(AbstractStatLabel sessionAccuracyLabel, AbstractStatLabel sessionCpmLabel, FrameCloseListener listener) {
	setTitle("Session Stats");
	setSize(SESSION_STATS_FRAME_WIDTH, SESSION_STATS_FRAME_HEIGHT);
	setLocationRelativeTo(null);
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	JPanel panel = new JPanel();
	panel.setLayout(new GridLayout(SESSION_STATS_FRAME_ROWS, SESSION_STATS_FRAME_COLUMNS));

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
