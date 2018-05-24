package com.cgi.health.workbrake;

import com.cgi.health.workbrake.service.AlarmService;

import java.util.logging.Logger;

public class App {
    private static Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {

        logger.fine("Starting alarm service... ");
        AlarmService alarmService = new AlarmService();
        alarmService.run();


    }
}
