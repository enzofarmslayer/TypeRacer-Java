package se.liu.enzcu445;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Timer {
    private int secondsElapsed = 0;
    private javax.swing.Timer swingTimer;

    public Timer() {
	swingTimer = new javax.swing.Timer(1000, new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		secondsElapsed++;
		tick();
	    }
	});
	//start();
    }

    protected void tick() {
	// Notify observers each second
    }

    public void start() {
	swingTimer.start();
    }

    public void stop() {
	swingTimer.stop();
    }

    public int getSecondsElapsed() {
	return secondsElapsed;
    }

    public void reset() {
	secondsElapsed = 0;
	tick();  // Update immediately on reset
    }
}
