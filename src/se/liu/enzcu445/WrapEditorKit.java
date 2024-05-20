package se.liu.enzcu445;

import javax.swing.text.*;

class WrapEditorKit extends StyledEditorKit {
    private ViewFactory defaultFactory = new WrapColumnFactory();

    @Override
    public ViewFactory getViewFactory() {
	return defaultFactory;
    }
}