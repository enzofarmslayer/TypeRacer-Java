package se.liu.enzcu445;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

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

    // Method to get a key component by its label
    public CustomKeyComponent getKeyComponent(String keyLabel) {
	return keyComponents.get(keyLabel);
    }
}
