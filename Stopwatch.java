import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Stopwatch extends JFrame {

    private JLabel timeLabel;
    private JButton startButton, stopButton, resetButton;

    private int seconds = 0;
    private Timer timer;

    public Stopwatch() {

        // Frame settings
        setTitle("Stopwatch");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Time label
        timeLabel = new JLabel("00:00:00", JLabel.CENTER);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 40));
        add(timeLabel, BorderLayout.CENTER);

        // Buttons panel
        JPanel panel = new JPanel();
        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        resetButton = new JButton("Reset");

        panel.add(startButton);
        panel.add(stopButton);
        panel.add(resetButton);
        add(panel, BorderLayout.SOUTH);

        // Timer (updates every 1 second)
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                seconds++;
                updateTime();
            }
        });

        // Button actions
        startButton.addActionListener(e -> timer.start());

        stopButton.addActionListener(e -> timer.stop());

        resetButton.addActionListener(e -> {
            timer.stop();
            seconds = 0;
            updateTime();
        });
    }

    private void updateTime() {
        int hrs = seconds / 3600;
        int mins = (seconds % 3600) / 60;
        int secs = seconds % 60;

        timeLabel.setText(
                String.format("%02d:%02d:%02d", hrs, mins, secs)
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Stopwatch().setVisible(true);
        });
    }
}