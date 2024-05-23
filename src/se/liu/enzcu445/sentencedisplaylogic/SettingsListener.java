package se.liu.enzcu445.sentencedisplaylogic;

/**
 * The SettingsListener interface defines a method for responding to changes in settings.
 * Implementing classes should provide specific logic for handling updates to word count and excluded letters.
 *
 * @since 1.0
 */
public interface SettingsListener
{
    void onSettingsChanged(int wordCount, String excludeLetters);
}
