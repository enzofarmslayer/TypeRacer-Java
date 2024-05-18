package se.liu.enzcu445;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Timer {
    private int hundredthsElapsed = 0;
    private javax.swing.Timer swingTimer;

    public Timer() {
	// Set timer interval to 10 milliseconds (0.01 seconds)
	swingTimer = new javax.swing.Timer(10, new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		hundredthsElapsed++;
		tick();
	    }
	});
    }

    protected void tick() {
	// Notify observers (if any) each 0.01 second
    }

    public void start() {
	swingTimer.start();
    }

    public void stop() {
	swingTimer.stop();
    }

    public int getHundredthsElapsed() {
	return hundredthsElapsed;
    }

    public void reset() {
	hundredthsElapsed = 0;
	tick();  // Update immediately on reset
    }
}
