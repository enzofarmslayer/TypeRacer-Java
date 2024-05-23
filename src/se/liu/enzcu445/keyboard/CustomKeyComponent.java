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

    public CustomKeyComponent(String keyLabel) {
	this.keyLabel = keyLabel;
	// Conditionally set the size for the space key
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

    private void initializeKeyBindings() {
	InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
	ActionMap actionMap = getActionMap();

	String keyStrokeString = getKeyStrokeStringForLabel(keyLabel);

	// Bind press action
	KeyStroke pressedKeyStroke = KeyStroke.getKeyStroke("pressed " + keyStrokeString);
	inputMap.put(pressedKeyStroke, "pressed " + keyLabel);
	actionMap.put("pressed " + keyLabel, new AbstractAction() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		currentColor = highlightColor;
		repaint();
	    }
	});

	// Bind release action
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

    private String getKeyStrokeStringForLabel(String label) {

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
	g.fillRect(0, 0, getWidth(), getHeight()); // Draw the key background
	g.setColor(Color.BLACK);
	g.drawString(keyLabel, 10, getHeight() / 2); // Adjust text positioning as needed
    }
}