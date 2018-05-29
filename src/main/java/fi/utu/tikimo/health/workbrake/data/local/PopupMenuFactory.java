package fi.utu.tikimo.health.workbrake.data.local;

import fi.utu.tikimo.health.workbrake.App;
import fi.utu.tikimo.health.workbrake.service.AlarmTimerTask;
import fi.utu.tikimo.health.workbrake.ui.ShowSystemNotification;

import java.awt.*;
import java.util.ArrayList;
import java.util.logging.Logger;

public class PopupMenuFactory {
    private final Logger logger = Logger.getLogger(App.class.getName());

    public void setNotifier(ShowSystemNotification notifier) {
        this.notifier = notifier;
    }

    private ShowSystemNotification notifier = new ShowSystemNotification();
    private final Thread parentThread;
    /**
     *
     * @param thread Thread to attach menus to
     */
    public PopupMenuFactory(Thread thread) {
        logger.info("Reached popup menu constructor");
        parentThread = thread;
    }

    public ArrayList<MenuItem> getDefaultMenu() {
        logger.info("Reached popup menu getdefaultmenu");
        return new ArrayList<MenuItem>(){{
            add(new MenuItem("Go on lunchbreak") {{
                // functionality here
                addActionListener(e -> {
                    logger.info("Program paused for 45 minute lunch break.");
                    notifier.showNotification("Work Break", "Program paused for 45 min lunch break.", TrayIcon.MessageType.INFO);
                    AlarmTimerTask.setNextSuppressed(true);
                });
            }});
            add(new MenuItem("Postpone initiated break"){{
                // insert item functionality here
                addActionListener(e -> {
                    logger.info("Disabled next alarm");
                    notifier.showNotification("Work Break", "Upcoming break will be suppressed", TrayIcon.MessageType.INFO);
                    AlarmTimerTask.setNextSuppressed(true);
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
}
