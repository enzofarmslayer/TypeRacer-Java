package se.liu.enzcu445.listener;
/**
 * The TypingEventListener interface defines methods for responding to typing events.
 * Implementing classes should provide specific logic for handling typing start and completion events.
 *
 * @since 1.0
 */
public interface TypingEventListener {
    void onTypingStarted();
    void onTypingCompleted();
}
