package se.liu.enzcu445.settings;

import se.liu.enzcu445.AbstractTypingException;

/**
 * InvalidSettingsException is a custom exception that indicates invalid settings provided by the user.
 * This exception extends the AbstractTypingException and is used specifically for settings-related errors.
 *
 * @since 1.0
 */
public class InvalidSettingsException extends AbstractTypingException
{

    /**
     * Constructs a new InvalidSettingsException with the specified detail message.
     *
     * @param message The detail message.
     */
    public InvalidSettingsException(String message) {
	super(message, null);
    }
}
