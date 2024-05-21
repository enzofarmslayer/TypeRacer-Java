package se.liu.enzcu445;

import javax.swing.text.*;

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