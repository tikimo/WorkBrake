package fi.utu.tikimo.health.workbrake.ui;

import fi.utu.tikimo.health.workbrake.App;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;

public class ShowSystemNotification {
    private final Logger logger = Logger.getLogger(App.class.getName());
    private boolean supportedOS = false;

    public ShowSystemNotification() {
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("windows") && osName.contains("10")) {
            logger.info("This os is supported. Proceeding.");
            supportedOS = true;
        } else {
            logger.severe("This os is not supported yet. Exiting app");
            System.exit(-1);
        }
    }

    public void showNotification(String header, String message, TrayIcon.MessageType severity) {
        if (supportedOS && SystemTray.isSupported()) {
            displayTray(header, message, severity);
            playNotificationSound();
        }
    }

    public void playNotificationSound() {
        File soundFile = new File(
                Objects.requireNonNull(getClass().getClassLoader().getResource("notification.wav")).getFile());
        try {
            Clip sound = AudioSystem.getClip();
            sound.open(AudioSystem.getAudioInputStream(soundFile));
            sound.start();
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }


    }

    private void displayTray(String header, String message, TrayIcon.MessageType severity) {
        // Obtain only one instance of SystemTray object
        SystemTray systemTray = SystemTray.getSystemTray();

        // If the icon is a resource
        Image image = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("trayIcon.png"));

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





