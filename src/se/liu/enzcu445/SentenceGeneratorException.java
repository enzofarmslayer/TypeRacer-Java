package se.liu.enzcu445;

/**
 * SentenceGeneratorException is a custom exception that represents errors specific to the sentence generator in the application.
 * It extends {@link AbstractTypingException}.
 *
 * @since 1.0
 */
public class SentenceGeneratorException extends AbstractTypingException {

    public SentenceGeneratorException(String message, Throwable cause) {
	super(message, cause);
    }
}
