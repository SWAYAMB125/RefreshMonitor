import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.awt.*;
import java.awt.TrayIcon.MessageType;

public class RefreshRateMonitor {

    static TrayIcon trayIcon;

    static int getRefreshRate() {
        try {
            ProcessBuilder pb = new ProcessBuilder(
                    "powershell",
                    "-Command",
                    "(Get-CimInstance Win32_VideoController).CurrentRefreshRate"
            );

            Process process = pb.start();

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;

            while ((line = reader.readLine()) != null) {

                line = line.trim();

                if (!line.isEmpty()) {
                    return Integer.parseInt(line);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    static void setupTray() {

        if (!SystemTray.isSupported()) {
            System.out.println("System tray not supported");
            return;
        }

        try {

            SystemTray tray = SystemTray.getSystemTray();

            Image image = Toolkit.getDefaultToolkit().getImage(
                    "https://upload.wikimedia.org/wikipedia/commons/3/3b/Refresh_icon.svg"
            );

            trayIcon = new TrayIcon(image, "Refresh Rate Monitor");

            trayIcon.setImageAutoSize(true);

            tray.add(trayIcon);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void notifyChange(String message) {

        if (trayIcon != null) {
            trayIcon.displayMessage(
                    "Refresh Rate Changed",
                    message,
                    MessageType.INFO
            );
        }

    }

    public static void main(String[] args) throws Exception {

        setupTray();

        int lastRate = getRefreshRate();

        System.out.println("Current Refresh Rate: " + lastRate + " Hz");

        while (true) {

            int currentRate = getRefreshRate();

            if (currentRate != lastRate && currentRate != -1) {

                String message =
                        "Refresh rate changed: "
                                + lastRate
                                + "Hz → "
                                + currentRate
                                + "Hz";

                System.out.println(message);

                notifyChange(message);

                lastRate = currentRate;
            }

            Thread.sleep(2000);
        }
    }
}