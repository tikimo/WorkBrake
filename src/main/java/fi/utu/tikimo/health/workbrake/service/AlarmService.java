package fi.utu.tikimo.health.workbrake.service;

import fi.utu.tikimo.health.workbrake.App;

import java.util.Timer;
import java.util.logging.Logger;

public class AlarmService implements Runnable {
    private Logger logger = Logger.getLogger(App.class.getName());

    @Override
    public void run() {
        logger.fine("Started alarm service.");
        logger.fine("Starting scheduled executor service...");

        Timer timer = new Timer();
        timer.schedule(new AlarmTimerTask(), 1000*60*60);   // Start task every hour

    }
}
