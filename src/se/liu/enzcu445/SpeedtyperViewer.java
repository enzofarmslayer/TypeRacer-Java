package se.liu.enzcu445;

import javax.swing.*;
import java.awt.*;

public class SpeedtyperViewer extends JFrame {
    JFrame frame = new JFrame("SpeedTyper");

    public SpeedtyperViewer() {
    }

    private void setupFrame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel topPanel = new JPanel(new FlowLayout());

        Timer timer = new Timer();
        TimerLabel timerLabel = new TimerLabel(timer);
        topPanel.add(timerLabel);

        TextPanelComponent textPanel = new TextPanelComponent(null); // Initially pass null

        AverageAccuracyLabel averageAccuracyLabel = new AverageAccuracyLabel(textPanel);
        AverageCpmLabel averageCpmLabel = new AverageCpmLabel(textPanel,timer);

        SessionAccuracyLabel sessionAccuracyLabel = new SessionAccuracyLabel(textPanel);
        SessionCpmLabel sessionCpmLabel = new SessionCpmLabel(textPanel, timer);

        PauseController pauseController = new PauseController(timer, textPanel);
        PauseButton pauseButton = new PauseButton(pauseController);

        TypingEventHandler typingEventHandler = new TypingEventHandler(timer, averageAccuracyLabel, averageCpmLabel,
                                                                       pauseButton, sessionAccuracyLabel, sessionCpmLabel, textPanel);

        textPanel.setTypingCompletionHandler(typingEventHandler); // Set the handler after creation
        textPanel.getTypingHandler().setTypingEventListener(typingEventHandler); // Set typing event listener

        topPanel.add(pauseButton);
        topPanel.add(averageCpmLabel);
        topPanel.add(averageAccuracyLabel);


        topPanel.setPreferredSize(new Dimension(frame.getWidth(), 100));
        frame.add(topPanel, BorderLayout.NORTH);

        JPanel container = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.2;
        gbc.fill = GridBagConstraints.BOTH;
        container.add(textPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;
        gbc.fill = GridBagConstraints.BOTH;
        container.add(new VisualKeyboardComponent(), gbc);

        frame.add(container, BorderLayout.CENTER);

        frame.setSize(835, 650);
        frame.setLocationRelativeTo(null);
    }

    public void show() {
        setupFrame();
        frame.setVisible(true);
    }

}
