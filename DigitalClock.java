import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

public class DigitalClock extends JFrame {

    JLabel timeLabel;
    SimpleDateFormat timeFormat;
    String time;
    Timer timer;

    DigitalClock() {
        // Frame settings
        this.setTitle("Digital Clock");
        this.setSize(400, 150);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setLocationRelativeTo(null);

        // Time format
        timeFormat = new SimpleDateFormat("HH:mm:ss");

        // Label settings
        timeLabel = new JLabel();
        timeLabel.setFont(new Font("Verdana", Font.BOLD, 50));
        timeLabel.setForeground(Color.BLUE);

        this.add(timeLabel);
        this.setVisible(true);

        // Timer to update time every second
        timer = new Timer(1000, e -> setTime());
        timer.start();
    }

    public void setTime() {
        time = timeFormat.format(new Date());
        timeLabel.setText(time);
    }

    public static void main(String[] args) {
        new DigitalClock();
    }
}