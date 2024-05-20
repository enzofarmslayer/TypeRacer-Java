package se.liu.enzcu445;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

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
