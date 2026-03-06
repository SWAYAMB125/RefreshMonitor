package monitor;

public class StartupManager {

    public static void enableStartup() {

        try {

            Runtime.getRuntime().exec(
                    "reg add HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Run "
                            + "/v RefreshMonitor "
                            + "/t REG_SZ "
                            + "/d \"C:\\RefreshMonitor\\RefreshMonitor.exe\" "
                            + "/f"
            );

        } catch(Exception e) {
            e.printStackTrace();
        }

    }

}