package com.cgi.health.workbrake.service;

import com.cgi.health.workbrake.App;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class AlarmService implements Runnable {
    private Logger logger = Logger.getLogger(App.class.getName());

    @Override
    public void run() {
        logger.fine("Started alarm service.");
        logger.fine("Starting scheduled executor service...");

        // Run scheduled executor service
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();

        service.scheduleAtFixedRate(() -> {
            System.out.println("We reached scheduled executor");
        }, 0, 45, TimeUnit.MINUTES);


    }
}
