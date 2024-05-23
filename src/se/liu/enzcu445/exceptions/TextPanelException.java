package se.liu.enzcu445.exceptions;

import se.liu.enzcu445.visualcomponents.AbstractTypingException;

/**
 * TextPanelException is a custom exception that represents errors specific to the sentence generator in the application.
 * It extends {@link AbstractTypingException}.
 *
 * @since 1.0
 */
public class TextPanelException extends AbstractTypingException
{
    public TextPanelException(final String message, final Throwable cause) {
	super(message, cause);
    }
}
