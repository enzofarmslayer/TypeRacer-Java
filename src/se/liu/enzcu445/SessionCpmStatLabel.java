package se.liu.enzcu445;

public class SessionCpmStatLabel extends AbstractStatLabel
{
    private Timer timer;

    public SessionCpmStatLabel(TextPanelComponent textPanelComponent, Timer timer) {
	super(textPanelComponent, "Session CPM: --");
	this.timer = timer;
	update();
    }

    @Override
    protected void updateLabel(String format, double newValue) {
	setText(String.format(format, newValue));
    }

    @Override
    public void update() {
	int hundredthsElapsed = timer.getHundredthsElapsed();
	int typedLength = typingHandler.getTypedLength();

	if (hundredthsElapsed == 0) {
	    setText("Session CPM: --");
	} else {
	    double secondsElapsed = hundredthsElapsed / 100.0;
	    double currentCpm = ((double) typedLength / secondsElapsed) * 60;
	    updateLabel("Session CPM: %.2f", currentCpm);
	}
    }
}
