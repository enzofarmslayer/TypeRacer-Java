package se.liu.enzcu445;

import se.liu.enzcu445.visualcomponents.TypeRacerViewer;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * The TypeRacerLauncher class is the entry point for the Speedtyper application.
 * It initializes logging and starts the main user interface.
 *
 * <p>Tasks:</p>
 * <ul>
 *   <li>Sets up logging with {@link LoggingConfig}.</li>
 *   <li>Displays the user interface via {@link TypeRacerViewer}.</li>
 * </ul>
 *
 * <p>Relationships:</p>
 * <ul>
 *   <li>{@link LoggingConfig}: Configures logging settings.</li>
 *   <li>{@link TypeRacerViewer}: Manages the main UI.</li>
 * </ul>
 *
 * @since 1.0
 */
public class TypeRacerLauncher
{
    private static final Logger LOGGER = Logger.getLogger(TypeRacerLauncher.class.getName());

    public static void main(String[] args) {
	try {
	    LoggingConfig.setup();
	    LOGGER.info("Program starts...");

	    TypeRacerViewer viewer = new TypeRacerViewer();
	    viewer.show();

	    LOGGER.info("Program has started");
	} catch (IOException e) {
	    LOGGER.log(Level.SEVERE, "Could not configure logging", e);
	}
    }
}
