package se.liu.enzcu445.typinglogic;

import se.liu.enzcu445.LoggingConfig;

import java.util.logging.Logger;

/**
 * Timer is a custom timer class that tracks elapsed time in hundredths of a second.
 * It uses a Swing Timer to increment the elapsed time and can start, stop, and reset the timer.
 *
 * <p>Responsibilities:</p>
 * <ul>
 *   <li>Tracks elapsed time in hundredths of a second.</li>
 *   <li>Starts, stops, and resets the timer.</li>
 *   <li>Provides the elapsed time to other components.</li>
 * </ul>
 *
 * @since 1.0
 */
public class Timer {
    private static final Logger LOGGER = LoggingConfig.getLogger();
    private int hundredthsElapsed = 0;
    private javax.swing.Timer swingTimer;

    public Timer() {
	// Set timer interval to 10 milliseconds (0.01 seconds)
	swingTimer = new javax.swing.Timer(10, e -> {
	    hundredthsElapsed++;
	    tick();
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
	LOGGER.info("Timer reset.");
    }
}
