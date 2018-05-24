package fi.utu.tikimo.health.workbrake.service;

import fi.utu.tikimo.health.workbrake.App;
import fi.utu.tikimo.health.workbrake.ui.ShowSystemNotification;

import java.awt.TrayIcon.MessageType;
import java.io.IOException;
import java.util.TimerTask;
import java.util.logging.Logger;

public class AlarmTimerTask extends TimerTask {
    Logger logger = Logger.getLogger(App.class.getName());
    private ShowSystemNotification notifier = new ShowSystemNotification();

    private String header = "Work Brake";

    @Override
    public void run() {
        // Notify at 45min that brake is soon
        notifier.showNotification(header, "We're having a 5 minute brake in 10 mins", MessageType.INFO);

        // Sleep for 9 minutes
        try {
            Thread.sleep(1000*60*9);    // 9 minutes
        } catch (InterruptedException e) {
            logger.warning(e.getMessage());
        }

        notifier.showNotification(header, "5 min brake is taking place in 1 minute!", MessageType.WARNING);

        try {
            Thread.sleep(1000*60); // 1 minute
        } catch (InterruptedException e) {
            logger.warning(e.getMessage());
        }

        notifier.showNotification(header, "Locking you out ...", MessageType.INFO);

        try {
            Thread.sleep(1000*60*5);    // break for 5 minutes
        } catch (InterruptedException e) {
            logger.warning(e.getMessage());
        }

        try {
            Runtime.getRuntime().exec("C:\\Windows\\System32\\rundll32.exe user32.dll,LockWorkStation");
        } catch (IOException e) {
            logger.warning(e.getMessage());
        }


    }
}
