package se.liu.enzcu445.sentencedisplaylogic;

import se.liu.enzcu445.LoggingConfig;
import se.liu.enzcu445.keyboard.VisualKeyboardComponent;
import se.liu.enzcu445.settings.SettingsButton;
import se.liu.enzcu445.pause.PauseController;
import se.liu.enzcu445.statlabels.AverageAccuracyStatLabel;
import se.liu.enzcu445.statlabels.AverageCPMStatLabel;
import se.liu.enzcu445.pause.PauseButton;
import se.liu.enzcu445.statlabels.SessionAccuracyStatLabel;
import se.liu.enzcu445.statlabels.SessionCpmStatLabel;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;

/**
 * SpeedtyperViewer is the main frame for the SpeedTyper application.
 * It sets up the main user interface components and manages the application's main window.
 *
 * <p>Responsibilities:</p>
 * <ul>
 *   <li>Initializes and configures the main frame and its components.</li>
 *   <li>Sets up and adds the top panel, which includes the timer label, pause button, average accuracy label, and average CPM label.</li>
 *   <li>Manages the text panel and visual keyboard components.</li>
 *   <li>Handles displaying error dialogs for initialization failures.</li>
 * </ul>
 *
 * @since 1.0
 */
public class TypeRacerViewer extends JFrame {
    private static final Logger LOGGER = LoggingConfig.getLogger();
    private JFrame frame = new JFrame("SpeedTyper");

    public TypeRacerViewer() {
    }

    private void initializeFrame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        JPanel topPanel = new JPanel(new FlowLayout());

        Timer timer = new Timer();
        TimerLabel timerLabel = new TimerLabel(timer);
        topPanel.add(timerLabel);

	TextPanelComponent textPanel = null;

        try {
	    textPanel = new TextPanelComponent(null, 20, ""); // Initially pass null
        } catch (TextPanelException e) {
            LOGGER.severe("Failed to initialize TextPanelComponent: " + e.getMessage());
            showErrorDialog("Failed to initialize TextPanelComponent: " + e.getMessage());
            return;
        }

        AverageAccuracyStatLabel averageAccuracyLabel = new AverageAccuracyStatLabel(textPanel);
        AverageCPMStatLabel averageCpmLabel = new AverageCPMStatLabel(textPanel, timer);

        SessionAccuracyStatLabel sessionAccuracyLabel = new SessionAccuracyStatLabel(textPanel);
        SessionCpmStatLabel sessionCpmLabel = new SessionCpmStatLabel(textPanel, timer);

        PauseController pauseController = new PauseController(timer, textPanel);
        PauseButton pauseButton = new PauseButton(pauseController);

	TypingEventHandler typingEventHandler =
		new TypingEventHandler(timer, averageAccuracyLabel, averageCpmLabel, pauseButton, sessionAccuracyLabel, sessionCpmLabel,
				       textPanel);

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
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        container.add(textPanel, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        container.add(new VisualKeyboardComponent(), gridBagConstraints);

        frame.add(container, BorderLayout.CENTER);

        frame.setSize(835, 650);
        frame.setLocationRelativeTo(null);
    }

    public void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void show() {
        initializeFrame();
        frame.setVisible(true);
        LOGGER.info("Main frame of SpeedTyper shown.");
    }
}
