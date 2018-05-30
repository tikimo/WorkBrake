package fi.utu.tikimo.health.workbrake.service;

import fi.utu.tikimo.health.workbrake.App;
import fi.utu.tikimo.health.workbrake.ui.ShowSystemNotification;

import java.awt.TrayIcon.MessageType;
import java.io.IOException;
import java.util.TimerTask;
import java.util.logging.Logger;

public class AlarmTimerTask extends TimerTask {
    Logger logger = Logger.getLogger(App.class.getName());
    private Thread executionThread = executeNotifier();

    public void restartExecutionThread(boolean ns) {
        executionThread.interrupt();
        System.out.println("Execution thread interrupted");
    }

    void setNotifier(ShowSystemNotification notifier) {
        this.notifier = notifier;
    }

    private ShowSystemNotification notifier;

    private String header = "Work Brake";

    @Override
    public void run() {
        System.out.println("Thread started!!");
        executionThread.run();
    }

    private Thread executeNotifier() {
        return new Thread(() -> {
            try {
                // Notify at 45min that brake is soon
                notifier.showNotification(header, "We're having a 5 minute brake in 10 mins", MessageType.INFO);

                // Sleep for 9 minutes
                Thread.sleep(1000 * 60 * 9);    // 9 minutes


                notifier.showNotification(header, "5 min brake is taking place in 1 minute!", MessageType.WARNING);
                System.out.println(Thread.currentThread().isInterrupted());

                Thread.sleep(1000 * 60); // 1 minute

                notifier.showNotification(header, "Locking you out ...", MessageType.INFO);

                try {
                    Runtime.getRuntime().exec("C:\\Windows\\System32\\rundll32.exe user32.dll,LockWorkStation");
                } catch (IOException e) {
                    logger.warning(e.getMessage());
                }

                Thread.sleep(1000 * 60 * 5);    // break for 5 minutes

                notifier.showNotification(header, "You can resume your work now :)", MessageType.INFO);
            } catch (InterruptedException ie) {
                System.out.println("We've been interrupted.");
            }
        });
    }

}
