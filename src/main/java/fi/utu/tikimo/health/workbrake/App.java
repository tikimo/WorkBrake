package fi.utu.tikimo.health.workbrake;

import fi.utu.tikimo.health.workbrake.service.AlarmService;

import java.util.logging.Logger;

public class App {
    private static Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {

        logger.info("Starting alarm service... ");
        AlarmService alarmService = new AlarmService();
        alarmService.run();


    }
}
