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
    private static final int SESSIONSTATSFRAME_WIDTH = 300;
    private static final int SESSIONSTATSFRAME_HEIGHT = 200;
    private static final int SESSIONSTATSFRAME_ROWS = 2;
    private static final int SESSIONSTATSFRAME_COLUMNS = 1;

    public SessionStatsFrame(AbstractStatLabel sessionAccuracyLabel, AbstractStatLabel sessionCpmLabel, FrameCloseListener listener) {
	setTitle("Session Stats");
	setSize(SESSIONSTATSFRAME_WIDTH, SESSIONSTATSFRAME_HEIGHT);
	setLocationRelativeTo(null);
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	JPanel panel = new JPanel();
	panel.setLayout(new GridLayout(SESSIONSTATSFRAME_ROWS, SESSIONSTATSFRAME_COLUMNS));

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
