package monitor;

import java.awt.*;

public class TrayManager {

    public static void initTray() {

        if(!SystemTray.isSupported())
            return;

        try {

            SystemTray tray = SystemTray.getSystemTray();

            Image image = Toolkit.getDefaultToolkit().createImage(new byte[0]);

            PopupMenu menu = new PopupMenu();

            MenuItem exit = new MenuItem("Exit");

            exit.addActionListener(e -> System.exit(0));

            menu.add(exit);

            TrayIcon trayIcon = new TrayIcon(image, "Refresh Monitor", menu);

            trayIcon.setImageAutoSize(true);

            tray.add(trayIcon);

        } catch(Exception e) {
            e.printStackTrace();
        }

    }

}