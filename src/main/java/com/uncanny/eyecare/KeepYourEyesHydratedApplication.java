package com.uncanny.eyecare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.awt.*;

@SpringBootApplication
@EnableScheduling
public class KeepYourEyesHydratedApplication {
    {
        // This makes sure that jvm doesn't run in headless mode, which will stop the notifications
        System.setProperty("java.awt.headless", "false");
    }

    public static void main(String[] args) {
        SpringApplication.run(KeepYourEyesHydratedApplication.class, args);
    }

    @Scheduled(fixedRate = 20*60*1000)
    public void scheduledMethod() throws AWTException {
        String toolTip = "Keep Eyes Hydrated";

        if (SystemTray.isSupported()) {
            SystemTray tray = SystemTray.getSystemTray();
            TrayIcon[] trayIcons = tray.getTrayIcons();

            for (TrayIcon trayIcon: trayIcons ) {
                if (trayIcon.getToolTip().equals(toolTip)) {
                    tray.remove(trayIcon);
                }
            }

            //If the icon is a file
            Image image = Toolkit.getDefaultToolkit().createImage("2_1.jpg");
            //Alternative (if the icon is on the classpath):
            //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));

            TrayIcon trayIcon = new TrayIcon(image, toolTip);
            //Let the system resize the image if needed
            trayIcon.setImageAutoSize(true);
            //Set tooltip text for the tray icon
            tray.add(trayIcon);

            trayIcon.displayMessage("Hey, Rahul", "Give your eyes some rest. Do not forget to look around.", TrayIcon.MessageType.INFO);
        } else
            System.out.println("Tray Icon Not Supported");
    }
}
