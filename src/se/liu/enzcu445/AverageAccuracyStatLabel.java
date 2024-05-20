package se.liu.enzcu445;

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
