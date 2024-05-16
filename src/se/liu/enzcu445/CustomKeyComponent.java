package se.liu.enzcu445;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

class CustomKeyComponent extends JComponent {
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
	setupKeyBindings();
    }

    private void setupKeyBindings() {
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

    //fel klass?
    private String getKeyStrokeStringForLabel(String label) {
	// This is a simplified mapping, you should expand it based on your needs
	switch (label) {
	    case "Backspace":
		return "BACK_SPACE";
	    case "Space":
		// For these keys, their Java KeyEvent.VK_* constants match their common name
		return label.toUpperCase();
	    case "Shift":
		// Example of using a direct keycode, replace 192 with the actual keycode if different
		return KeyEvent.getKeyText(16); // You need to know the correct key code
	    // Add specific cases for other special keys and international characters as needed
	    default:
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