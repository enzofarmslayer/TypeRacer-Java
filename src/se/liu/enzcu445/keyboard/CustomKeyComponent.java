package se.liu.enzcu445.keyboard;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * CustomKeyComponent represents a visual component for a keyboard key.
 * It highlights the key when pressed and returns to the default color when released.
 *
 * <p>Responsibilities:</p>
 * <ul>
 *   <li>Sets size and label of the key based on its type.</li>
 *   <li>Handles key press and release events to change the key's color.</li>
 *   <li>Renders the key with the appropriate color and label.</li>
 * </ul>
 *
 * @since 1.0
 */
public class CustomKeyComponent extends JComponent {
    private Color defaultColor = Color.LIGHT_GRAY;
    private Color highlightColor = Color.YELLOW;
    private Color currentColor = defaultColor;
    private String keyLabel;
    private static final int STRING_OFFSET = 10;

    public CustomKeyComponent(String keyLabel) {
	this.keyLabel = keyLabel;

	/** Conditionally set the size for the special keys **/
	if ("Space".equals(keyLabel)) {
	    setPreferredSize(new Dimension(270, 50)); // Longer width for the space key
	}else if ("Backspace".equals(keyLabel)){
	    setPreferredSize(new Dimension(100, 50)); // Longer width for the backspace key
	}
	else {
	    setPreferredSize(new Dimension(50, 50)); // Default size for other keys
	}
	initializeKeyBindings();
    }

    /**
     * Initializes the key bindings for the component.
     * This method binds actions to key press and release events to change the key's color.
     */
    private void initializeKeyBindings() {
	InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
	ActionMap actionMap = getActionMap();

	String keyStrokeString = getKeyStrokeString(keyLabel);

	/** Bind press action so the key lights up when pressed **/
	KeyStroke pressedKeyStroke = KeyStroke.getKeyStroke("pressed " + keyStrokeString);
	inputMap.put(pressedKeyStroke, "pressed " + keyLabel);
	actionMap.put("pressed " + keyLabel, new AbstractAction() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		currentColor = highlightColor;
		repaint();
	    }
	});

	/** Bind release action so the key returns to default color when released**/
	KeyStroke releasedKeyStroke = KeyStroke.getKeyStroke("released " + keyStrokeString);
	inputMap.put(releasedKeyStroke, "released " + keyLabel);
	actionMap.put("released " + keyLabel, new AbstractAction() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		currentColor = defaultColor;
		repaint();
	    }
	});
    }

    /**
     * Converts the key label to a corresponding KeyStroke string.
     * Special cases like "Backspace" are mapped to their KeyStroke constants.
     *
     * @param label The key label to convert.
     * @return The corresponding KeyStroke string.
     */
    private String getKeyStrokeString(String label) {
	if ("Backspace".equals(label)) {
	    return "BACK_SPACE";
	} else {
	    return label.toUpperCase();
	}
    }

    @Override
    protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	g.setColor(currentColor);
	g.fillRect(0, 0, getWidth(), getHeight()); /** Draw the key background **/
	g.setColor(Color.BLACK);
	g.drawString(keyLabel, STRING_OFFSET, getHeight() / 2); /** Adjust text positioning as needed **/
    }
}