package fi.utu.tikimo.health.workbrake.ui;

import fi.utu.tikimo.health.workbrake.App;
import fi.utu.tikimo.health.workbrake.data.local.model.PopupMenuException;

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
    private boolean supportedOS = false;
    private TrayIcon trayIcon;
    private boolean nextSuppressed;
    private PopupMenu popupMenu;

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

    public void initTrayIcon() {
        try {
            // Require that there are items in popup menu, at least the Exit function
            if (popupMenu == null || popupMenu.getItemCount() == 0) {
                throw new PopupMenuException("No items in popup menu. Call setPopupMenu(ArrayList<MenuItem>) before initialization.");
            }
            // If the icon is a resource
            trayIcon = new TrayIcon(
                    Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("trayIcon.png")),
                    "Work Brake",
                    popupMenu);
            trayIcon.setImageAutoSize(true);
            trayIcon.setToolTip("Work brake (c) Tijam Moradi");

            try {
                systemTray.add(trayIcon);
            } catch (AWTException e) {
                logger.severe(e.getLocalizedMessage());
                e.printStackTrace();
            }

        } catch (PopupMenuException pme) {
            logger.warning(pme.getMessage());
        }


    }

    public void showNotification(String header, String message, TrayIcon.MessageType severity) {
        if (supportedOS && SystemTray.isSupported()) {
            logger.info("Displaying tray info notification");
            // Obtain only one instance of SystemTray object
            trayIcon.displayMessage(header, message, severity);

            logger.info("Playing notification sound");
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

    public boolean getNextSuppressed() {
        return nextSuppressed;
    }

    public void setNextSuppressed(boolean nextSuppressed) {
        this.nextSuppressed = nextSuppressed;
    }

    public void setPopupMenu(ArrayList<MenuItem> defaultMenu) {
        popupMenu = new PopupMenu();
        for (MenuItem menuItem : defaultMenu) {
            popupMenu.add(menuItem);
            System.out.println("Added menu item: " + menuItem.getLabel());
        }
        logger.fine("All menu items added");
    }

}





