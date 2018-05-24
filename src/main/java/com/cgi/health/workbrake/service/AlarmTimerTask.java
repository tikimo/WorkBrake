package com.cgi.health.workbrake.service;

import com.cgi.health.workbrake.App;
import com.cgi.health.workbrake.ui.ShowSystemNotification;

import java.util.TimerTask;
import java.util.logging.Logger;

public class AlarmTimerTask extends TimerTask {
    Logger logger = Logger.getLogger(App.class.getName());
    ShowSystemNotification notifier = new ShowSystemNotification();

    @Override
    public void run() {


    }
}
