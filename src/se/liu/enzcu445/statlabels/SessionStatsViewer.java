package se.liu.enzcu445.statlabels;

import se.liu.enzcu445.sentencedisplaylogic.FrameCloseListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * SessionStatsViewer is a custom component that displays session statistics including accuracy and CPM.
 * It manages a window that shows the provided {@link AbstractStatLabel} components for session accuracy and CPM.
 *
 * @since 1.0
 */
public class SessionStatsViewer {
    private static final int SESSION_STATS_FRAME_WIDTH = 300;
    private static final int SESSION_STATS_FRAME_HEIGHT = 200;
    private static final int SESSION_STATS_FRAME_ROWS = 2;
    private static final int SESSION_STATS_FRAME_COLUMNS = 1;

    private AbstractStatLabel sessionAccuracyLabel = null;
    private AbstractStatLabel sessionCpmLabel = null;
    private FrameCloseListener listener= null;
    private JFrame frame = null;

    public SessionStatsViewer(AbstractStatLabel sessionAccuracyLabel, AbstractStatLabel sessionCpmLabel, FrameCloseListener listener) {
	this.sessionAccuracyLabel = sessionAccuracyLabel;
	this.sessionCpmLabel = sessionCpmLabel;
	this.listener = listener;
    }
    private void initializeFrame(){
	frame = new JFrame("Session Stats");
	frame.setSize(SESSION_STATS_FRAME_WIDTH, SESSION_STATS_FRAME_HEIGHT);
	frame.setLocationRelativeTo(null);
	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	JPanel panel = new JPanel();
	panel.setLayout(new GridLayout(SESSION_STATS_FRAME_ROWS, SESSION_STATS_FRAME_COLUMNS));

	panel.add(sessionCpmLabel.getComponent());
	panel.add(sessionAccuracyLabel.getComponent());

	frame.add(panel);

	frame.addWindowListener(new WindowAdapter() {
	    @Override
	    public void windowClosing(WindowEvent e) {
		if (listener != null) {
		    listener.onFrameClosed();
		}
	    }
	});
    }

    public void show() {
	initializeFrame();
	frame.setVisible(true);
    }

}
