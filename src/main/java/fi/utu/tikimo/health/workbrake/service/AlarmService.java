package fi.utu.tikimo.health.workbrake.service;

import fi.utu.tikimo.health.workbrake.App;
import fi.utu.tikimo.health.workbrake.ui.ShowSystemNotification;

import java.awt.*;
import java.util.Timer;
import java.util.logging.Logger;

public class AlarmService implements Runnable {
    private Logger logger = Logger.getLogger(App.class.getName());

    @Override
    public void run() {
        logger.info("Started alarm service.");
        logger.info("Starting scheduled executor service...");

        Timer timer = new Timer();
        AlarmTimerTask alarmTimerTask = new AlarmTimerTask();
        ShowSystemNotification notifier = new ShowSystemNotification();

        notifier.showNotification("WorkBrake", "Alarm service started. First brake in 55 minutes.", TrayIcon.MessageType.INFO);

        alarmTimerTask.setNotifier(notifier);
        timer.schedule(alarmTimerTask,1000*60*45, 1000*60*60);   // Start task every hour

    }
}
