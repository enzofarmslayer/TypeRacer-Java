package se.liu.enzcu445.keyboard;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * VisualKeyboardComponent represents a visual keyboard composed of {@link CustomKeyComponent}s.
 * It organizes keys in a grid layout and provides methods to interact with individual keys.
 *
 * <p>Responsibilities:</p>
 * <ul>
 *   <li>Initializes and arranges key components in a visual layout.</li>
 *   <li>Provides access to individual key components by their labels.</li>
 * </ul>
 *
 * @since 1.0
 */
public class VisualKeyboardComponent extends JPanel {
    private Map<String, CustomKeyComponent> keyComponents;

    public VisualKeyboardComponent() {
	setLayout(new GridLayout(0, 1)); // Rows, 1 column to stack row panels

	// Initialize the keyComponents map
	keyComponents = new HashMap<>();

	//keyboard layout
	String[][] keyRows = {
		{"§", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "+", "´", "Backspace"},
		{"Tab", "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "Å", "^", "*"},
		{"Caps", "A", "S", "D", "F", "G", "H", "J", "K", "L", "Ö", "Ä", "Enter"},
		{"Shift", "<", "Z", "X", "C", "V", "B", "N", "M", ",", ".", "-", "Shift"},
		{"Ctrl", "Fn", "Win", "Alt", "Space", "Alt Gr", "Ctrl"}
	};

	for (String[] row : keyRows) {
	    JPanel rowPanel = new JPanel();
	    rowPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
	    for (String keyLabel : row) {
		CustomKeyComponent keyComponent = new CustomKeyComponent(keyLabel);
		keyComponents.put(keyLabel, keyComponent);
		rowPanel.add(keyComponent);
	    }
	    add(rowPanel);
	}
    }

    public CustomKeyComponent getKeyComponent(String keyLabel) {
	return keyComponents.get(keyLabel);
    }
}
