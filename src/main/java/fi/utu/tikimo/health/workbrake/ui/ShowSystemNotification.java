package fi.utu.tikimo.health.workbrake.ui;

import fi.utu.tikimo.health.workbrake.App;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Logger;

public class ShowSystemNotification {
    private final Logger logger = Logger.getLogger(App.class.getName());
    private SystemTray systemTray = SystemTray.getSystemTray();
    private boolean nextAlarmActive = true;
    private boolean supportedOS = false;
    private TrayIcon trayIcon;

    public ShowSystemNotification() {
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("windows") && osName.contains("10")) {
            logger.info("This os is supported. Proceeding.");
            supportedOS = true;
            initTrayIcon();
        } else {
            logger.severe("This os is not supported yet. Exiting app");
            System.exit(-1);
        }
    }

    public void showNotification(String header, String message, TrayIcon.MessageType severity) {
        if (supportedOS && SystemTray.isSupported() && nextAlarmActive) {
            displayTray(header, message, severity);
            logger.info("Playing notification sound");
            playNotificationSound();

        } else {
            nextAlarmActive = true;
            logger.info("This alarm was suppressed.");
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

    private void initTrayIcon() {
        PopupMenu popupMenu = new PopupMenu();
        ArrayList<MenuItem> menuItems = getMenuItems();

        for (MenuItem menuItem : menuItems) {
            popupMenu.add(menuItem);
        }

        // If the icon is a resource
        trayIcon = new TrayIcon(
                Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("trayIcon.png")),
                "Work Brake",
                popupMenu);
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip("Work brake tooltip");


        try {
            systemTray.add(trayIcon);
        } catch (AWTException e) {
            logger.severe(e.getLocalizedMessage());
            e.printStackTrace();
        }

    }

    private ArrayList<MenuItem> getMenuItems() {
        return new ArrayList<MenuItem>(){{
            add(new MenuItem("Postpone initiated break"){{
                // insert item functionality here
                addActionListener(e -> {
                    logger.info("Disabled next alarm");
                    showNotification("Work Break", "Upcoming break will be suppressed", TrayIcon.MessageType.INFO);
                    nextAlarmActive = false;
                });
            }});
            add(new MenuItem("Exit") {{
                // insert item functionality here
                addActionListener(e -> {
                    logger.info("Exiting system...");
                    System.exit(0);
                });
            }});
        }};
    }

    private void displayTray(String header, String message, TrayIcon.MessageType severity) {
        logger.info("Displaying tray info notification");
        // Obtain only one instance of SystemTray object
        trayIcon.displayMessage(header, message, severity);
    }

}





