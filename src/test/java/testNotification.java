import com.cgi.health.workbrake.ui.ShowSystemNotification;

import java.awt.*;

public class testNotification {

    public static void main(String[] a) {
        ShowSystemNotification systemNotification = new ShowSystemNotification();

        systemNotification.showNotification("Moikka", "Riku on äiä", TrayIcon.MessageType.INFO);
    }
}
