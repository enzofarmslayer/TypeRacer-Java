package se.liu.enzcu445;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;

public class SpeedtyperViewer extends JFrame {
    private static final Logger logger = LoggingConfig.getLogger();
    private JFrame frame = new JFrame("SpeedTyper");
    private TextPanelComponent textPanel;
    private TypingEventHandler typingEventHandler;

    public SpeedtyperViewer() {
    }

    private void setupFrame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        JPanel topPanel = new JPanel(new FlowLayout());

        Timer timer = new Timer();
        TimerLabel timerLabel = new TimerLabel(timer);
        topPanel.add(timerLabel);

        textPanel = new TextPanelComponent(null, 20, ""); // Initially pass null

        AverageAccuracyStatLabel averageAccuracyLabel = new AverageAccuracyStatLabel(textPanel);
        AverageCpmStatLabel averageCpmLabel = new AverageCpmStatLabel(textPanel, timer);

        SessionAccuracyStatLabel sessionAccuracyLabel = new SessionAccuracyStatLabel(textPanel);
        SessionCpmStatLabel sessionCpmLabel = new SessionCpmStatLabel(textPanel, timer);

        PauseController pauseController = new PauseController(timer, textPanel);
        PauseButton pauseButton = new PauseButton(pauseController);

        typingEventHandler = new TypingEventHandler(timer, averageAccuracyLabel, averageCpmLabel,
                                                    pauseButton, sessionAccuracyLabel, sessionCpmLabel, textPanel);

        textPanel.setTypingCompletionHandler(typingEventHandler); // Set the handler after creation
        textPanel.getTypingHandler().setTypingEventListener(typingEventHandler); // Set typing event listener

        topPanel.add(pauseButton);
        topPanel.add(averageCpmLabel);
        topPanel.add(averageAccuracyLabel);

        // Lägg till inställningsknappen som en egen klass
        SettingsButton settingsButton = new SettingsButton(typingEventHandler, textPanel.getSentenceGenerator().getWordCount(), textPanel.getSentenceGenerator().getExcludeLetters());
        topPanel.add(settingsButton);

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
        logger.info("Main frame of SpeedTyper shown.");
    }
}
