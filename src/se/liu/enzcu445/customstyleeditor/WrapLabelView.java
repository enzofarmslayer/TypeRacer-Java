package se.liu.enzcu445.customstyleeditor;

import javax.swing.text.*;
/**
 * WrapLabelView is a custom implementation of the LabelView that supports wrapping.
 *
 * <p>This view is used to ensure that text labels can be wrapped within their container.</p>
 *
 * @since 1.0
 */
public class WrapLabelView extends LabelView
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