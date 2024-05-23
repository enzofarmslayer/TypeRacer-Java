package se.liu.enzcu445.sentencedisplaylogic;

import javax.swing.text.*;

/**
 * WrapColumnFactory is a custom implementation of the ViewFactory that creates different types of views based on the element type.
 *
 * <p>This factory is used to enable wrapping for text elements in JTextPane components.</p>
 *
 * @since 1.0
 */
public class WrapColumnFactory implements ViewFactory
{
    @Override
    public View create(Element elem) {
	String kind = elem.getName();
	if (kind != null) {
	    switch (kind) {
		case AbstractDocument.ContentElementName -> {
		    return new WrapLabelView(elem);
		}
		case AbstractDocument.ParagraphElementName -> {
		    return new ParagraphView(elem);
		}
		case AbstractDocument.SectionElementName -> {
		    return new BoxView(elem, View.Y_AXIS);
		}
		case StyleConstants.ComponentElementName -> {
		    return new ComponentView(elem);
		}
		case StyleConstants.IconElementName -> {
		    return new IconView(elem);
		}
	    }
	}
	return new LabelView(elem);
    }
}