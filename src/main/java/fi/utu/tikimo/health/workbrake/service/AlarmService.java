package fi.utu.tikimo.health.workbrake.service;

import fi.utu.tikimo.health.workbrake.App;
import fi.utu.tikimo.health.workbrake.data.local.PopupMenuFactory;
import fi.utu.tikimo.health.workbrake.ui.ShowSystemNotification;

import java.awt.*;
import java.util.Timer;
import java.util.logging.Logger;

public class AlarmService implements Runnable {
    private Logger logger = Logger.getLogger(App.class.getName());
    private static Timer timer;


    @Override
    public void run() {
        logger.info("Started alarm service.");
        logger.info("Starting scheduled executor service...");

        timer = new Timer();
        AlarmTimerTask alarmTimerTask = new AlarmTimerTask();
        ShowSystemNotification notifier = new ShowSystemNotification();

        PopupMenuFactory popupMenuFactory = new PopupMenuFactory(alarmTimerTask);
        notifier.setPopupMenu(popupMenuFactory.getDefaultMenu());
        notifier.initTrayIcon();

        popupMenuFactory.setNotifier(notifier);

        notifier.showNotification("WorkBrake", "Alarm service started. First brake in 55 minutes.", TrayIcon.MessageType.INFO);

        alarmTimerTask.setNotifier(notifier);
        timer.schedule(alarmTimerTask, 1000*60*45);   // Start task every hour
    }

}
