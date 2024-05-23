package se.liu.enzcu445.statlabels;

import se.liu.enzcu445.sentencedisplaylogic.TextPanelComponent;
import se.liu.enzcu445.sentencedisplaylogic.TypingLogicHandler;

/**
 * AverageAccuracyStatLabel is a concrete implementation of {@link AbstractStatLabel} that displays the average accuracy of typing.
 * It updates the label with the average accuracy value based on data from {@link TypingLogicHandler}.
 *
 * @since 1.0
 */
public class AverageAccuracyStatLabel extends AbstractStatLabel
{

    public AverageAccuracyStatLabel(TextPanelComponent textPanelComponent) {
	super(textPanelComponent, "Average Accuracy: --");
    }

    @Override
    public void update() {
	typingHandler.saveCurrentAccuracy();
	double averageAccuracy = typingHandler.calculateAverageAccuracy();
	updateLabel("Average Accuracy: %.2f%%", averageAccuracy);
    }
}
