package se.liu.enzcu445;

/**
 * AbstractTypingException is an abstract class that serves as a base for custom exceptions in the typing application.
 * It provides constructors to initialize the exception with a message and a cause.
 *
 * @since 1.0
 */
public abstract class AbstractTypingException extends Exception {

    protected AbstractTypingException(String message, Throwable cause) {
	super(message, cause);
    }

}