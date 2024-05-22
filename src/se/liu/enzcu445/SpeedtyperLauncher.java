package se.liu.enzcu445;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * The SpeedtyperLauncher class is the entry point for the Speedtyper application.
 * It initializes logging and starts the main user interface.
 *
 * <p>Tasks:</p>
 * <ul>
 *   <li>Sets up logging with {@link LoggingConfig}.</li>
 *   <li>Displays the user interface via {@link SpeedtyperViewer}.</li>
 * </ul>
 *
 * <p>Relationships:</p>
 * <ul>
 *   <li>{@link LoggingConfig}: Configures logging settings.</li>
 *   <li>{@link SpeedtyperViewer}: Manages the main UI.</li>
 * </ul>
 *
 * @since 1.0
 */
public class SpeedtyperLauncher {
    private static final Logger LOGGER = Logger.getLogger(SpeedtyperLauncher.class.getName());

    public static void main(String[] args) {
	try {
	    LoggingConfig.setup();
	    LOGGER.info("Program starts...");

	    SpeedtyperViewer viewer = new SpeedtyperViewer();
	    viewer.show();

	    LOGGER.info("Program has started");
	} catch (IOException e) {
	    LOGGER.log(Level.SEVERE, "Could not configure logging", e);
	}
    }
}
