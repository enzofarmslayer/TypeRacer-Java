package se.liu.enzcu445;

import javax.swing.text.*;

class WrapLabelView extends LabelView
{
    public WrapLabelView(Element elem) {
	super(elem);
    }

    @Override
    public float getMinimumSpan(int axis) {
	switch (axis) {
	    case View.X_AXIS:
		return 0;
	    default:
		return super.getMinimumSpan(axis);
	}
    }
}