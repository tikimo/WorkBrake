package com.cgi.health.workbrake.service;

import com.cgi.health.workbrake.App;
import com.cgi.health.workbrake.ui.ShowSystemNotification;

import java.awt.TrayIcon.MessageType;
import java.io.IOException;
import java.util.TimerTask;
import java.util.logging.Logger;

public class AlarmTimerTask extends TimerTask {
    Logger logger = Logger.getLogger(App.class.getName());
    ShowSystemNotification notifier = new ShowSystemNotification();

    String header = "Work Brake";

    @Override
    public void run() {
        // Notify at 45min that brake is soon
        notifier.showNotification(header, "We're having a brake in 10 mins", MessageType.INFO);

        // Sleep for 9 minutes
        try {
            Thread.sleep(1000*60*9);    // 9 minutes
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        notifier.showNotification(header, "Brake is taking place in 1 minute!", MessageType.WARNING);

        try {
            Thread.sleep(1000*55); // 55 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        notifier.showNotification(header, "Locking you out...", MessageType.INFO);

        try {
            Thread.sleep(1000*5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            Runtime.getRuntime().exec("C:\\Windows\\System32\\rundll32.exe user32.dll,LockWorkStation");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
