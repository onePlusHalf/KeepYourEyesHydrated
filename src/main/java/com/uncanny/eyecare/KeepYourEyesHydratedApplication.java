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
        System.setProperty("java.awt.headless", "false");
    }

    public static void main(String[] args) {
        SpringApplication.run(KeepYourEyesHydratedApplication.class, args);
    }

    @Scheduled(fixedRate = 50000)
    public void scheduledMethod() throws AWTException {
        if (SystemTray.isSupported()) {
            SystemTray tray = SystemTray.getSystemTray();

            //If the icon is a file
            Image image = Toolkit.getDefaultToolkit().createImage("2_1.jpg");
            //Alternative (if the icon is on the classpath):
            //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));

            TrayIcon trayIcon = new TrayIcon(image, "Keep Eyes Hydrated");
            //Let the system resize the image if needed
            trayIcon.setImageAutoSize(true);
            //Set tooltip text for the tray icon
            trayIcon.setToolTip("Keep Eyes Hydrated");
            tray.add(trayIcon);

            trayIcon.displayMessage("Hey, Man", "Do not forget to look around you.", TrayIcon.MessageType.INFO);
        } else
            System.out.println("Not Supported");
    }
}
