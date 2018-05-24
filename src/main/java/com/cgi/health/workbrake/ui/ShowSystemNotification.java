package com.cgi.health.workbrake.ui;

import com.cgi.health.workbrake.App;

import java.awt.*;
import java.util.logging.Logger;

public class ShowSystemNotification {
    private final Logger logger = Logger.getLogger(App.class.getName());
    private boolean supportedOS = false;

    public ShowSystemNotification() {
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("windows") && osName.contains("10")) {
            logger.fine("This os is supported. Proceeding.");
            supportedOS = true;
        } else {
            logger.severe("This os is not supported yet. Exiting app");
            System.exit(-1);
        }
    }

    public void showNotification(String header, String message, TrayIcon.MessageType severity) {
        if (supportedOS && SystemTray.isSupported()) {
            displayTray(header, message, severity);
        }
    }

    private void displayTray(String header, String message, TrayIcon.MessageType severity) {
        // Obtain only one instance of SystemTray object
        SystemTray systemTray = SystemTray.getSystemTray();

        // If the icon is a resource
        Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("trayIcon.png"));

        TrayIcon trayIcon = new TrayIcon(image, "Work Brake");
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip("Work brake tooltip");

        try {
            systemTray.add(trayIcon);
        } catch (AWTException e) {
            logger.severe(e.getLocalizedMessage());
            e.printStackTrace();
        }

        trayIcon.displayMessage(header, message, severity);

    }

}





