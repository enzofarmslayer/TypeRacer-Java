package se.liu.enzcu445;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * LoggingConfig is responsible for setting up the logging configuration for the application.
 * It configures a file handler to log messages to a file and provides a global logger instance.
 *
 * <p>Responsibilities:</p>
 * <ul>
 *   <li>Configures logging settings including file handler and log level.</li>
 *   <li>Provides a global logger for the application.</li>
 * </ul>
 *
 * @since 1.0
 */
public class LoggingConfig {
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static void setup() throws IOException {
	Logger rootLogger = Logger.getLogger("");
	FileHandler fileHandler = new FileHandler("app.log", true);
	fileHandler.setFormatter(new SimpleFormatter());
	rootLogger.addHandler(fileHandler);
	rootLogger.setLevel(Level.ALL);
    }

    public static Logger getLogger() {
	return logger;
    }
}
