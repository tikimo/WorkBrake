import fi.utu.tikimo.health.workbrake.ui.ShowSystemNotification;

import java.awt.*;
import java.io.IOException;

public class testNotification {

    public static void main(String[] a) throws IOException, InterruptedException {
        ShowSystemNotification systemNotification = new ShowSystemNotification();

        systemNotification.showNotification("Moikka", "Riku on äiä", TrayIcon.MessageType.INFO);

        systemNotification.playNotificationSound();

        // Runtime.getRuntime().exec("C:\\Windows\\System32\\rundll32.exe user32.dll,LockWorkStation");

        Thread.sleep(14000);
        // System.exit(0);
        systemNotification.showNotification("Test", "another one", TrayIcon.MessageType.INFO);

    }
}
