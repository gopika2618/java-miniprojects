import java.awt.Toolkit;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AlarmClock {

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {

            System.out.print("Enter alarm time (HH:mm): ");
            String inputTime = sc.nextLine();

            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("HH:mm");

            LocalTime alarmTime =
                    LocalTime.parse(inputTime, formatter);

            System.out.println("Alarm set for: " + alarmTime);
            System.out.println("Clock running...");

            while (true) {
                LocalTime now = LocalTime.now();
                String currentTime = now.format(formatter);

                System.out.println("Current Time: " + currentTime);

                if (currentTime.equals(alarmTime.format(formatter))) {
                    System.out.println("⏰ ALARM RINGING! WAKE UP!");
                    Toolkit.getDefaultToolkit().beep();
                    break;
                }

                Thread.sleep(1000); // this warning is OK
            }

        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }
    }
}