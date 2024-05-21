package se.liu.enzcu445;

/**
 * TypingLogicException is a custom exception that represents errors specific to the typing logic in the application.
 * It extends {@link AbstractTypingException}.
 *
 * @since 1.0
 */
public class TypingLogicException extends AbstractTypingException {

    public TypingLogicException(String message, Throwable cause) {
	super(message, cause);
    }
}
