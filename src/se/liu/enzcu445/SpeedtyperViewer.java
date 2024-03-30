package se.liu.enzcu445;

import javax.swing.*;
import java.awt.*;

public class SpeedtyperViewer extends JFrame
{
    JFrame frame = new JFrame("SpeedTyper");

    public SpeedtyperViewer() {
    }

    private void setupFrame(){
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        VisualKeyboard keyboardPanel = new VisualKeyboard();

        JPanel container = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;

        container.add(new VisualKeyboard(), gbc);

        frame.setContentPane(container);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }
    public void show(){
        setupFrame();
        frame.setVisible(true);
    }
}
