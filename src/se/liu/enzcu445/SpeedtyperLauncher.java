package se.liu.enzcu445;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SpeedtyperLauncher {
    private static final Logger logger = Logger.getLogger(SpeedtyperLauncher.class.getName());

    public static void main(String[] args) {
	try {
	    LoggingConfig.setup();
	    logger.info("Program starts...");

	    SpeedtyperViewer viewer = new SpeedtyperViewer();
	    viewer.show();

	    logger.info("Program have started");

	} catch (IOException e) {
	    logger.log(Level.SEVERE, "Could not confiugre logging", e);
	} catch (RuntimeException e) {
	    logger.log(Level.SEVERE, "An unexpected error occured", e);
	}
    }
}
