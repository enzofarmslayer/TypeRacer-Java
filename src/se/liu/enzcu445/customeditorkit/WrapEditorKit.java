package se.liu.enzcu445.customeditorkit;

import javax.swing.text.*;

/**
 * WrapEditorKit is a custom implementation of the StyledEditorKit that uses WrapColumnFactory for creating views.
 *
 * <p>This editor kit enables text wrapping in JTextPane components.</p>
 *
 * @since 1.0
 */
public class WrapEditorKit extends StyledEditorKit {
    private ViewFactory viewFactory = new WrapColumnFactory();

    @Override
    public ViewFactory getViewFactory() {
	return viewFactory;
    }
}