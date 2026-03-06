package monitor;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class WindowsAPI {

    public static int getRefreshRate() {

        try {

            ProcessBuilder pb = new ProcessBuilder(
                    "powershell",
                    "-Command",
                    "(Get-CimInstance Win32_VideoController).CurrentRefreshRate"
            );

            Process process = pb.start();

            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(process.getInputStream())
                    );

            String line;

            while((line = reader.readLine()) != null) {

                line = line.trim();

                if(!line.isEmpty()) {
                    return Integer.parseInt(line);
                }

            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

}