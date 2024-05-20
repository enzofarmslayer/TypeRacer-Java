package se.liu.enzcu445;

public class SessionAccuracyStatLabel extends AbstractStatLabel
{

    public SessionAccuracyStatLabel(TextPanelComponent textPanelComponent) {
	super(textPanelComponent, "Session Accuracy: --");
	update();
    }

    @Override
    protected void updateLabel(String format, double newValue) {
	setText(String.format(format, newValue));
    }

    @Override
    public void update() {
	double currentAccuracy = typingHandler.calculateAccuracy();
	updateLabel("Session Accuracy: %.2f%%", currentAccuracy);
    }
}
