package monitor;

public class MonitorService {

    public static void start() {

        int lastRate = WindowsAPI.getRefreshRate();

        System.out.println("Current Refresh Rate: " + lastRate + " Hz");

        while(true) {

            int currentRate = WindowsAPI.getRefreshRate();

            if(currentRate != lastRate) {

                System.out.println("Refresh Rate Changed: " + lastRate + " -> " + currentRate);

                lastRate = currentRate;

            }

            try{
                Thread.sleep(2000);
            }catch(Exception e){
                e.printStackTrace();
            }

        }

    }

}