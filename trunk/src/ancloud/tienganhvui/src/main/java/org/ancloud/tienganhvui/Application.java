package org.ancloud.tienganhvui;

import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;

import org.springframework.boot.SpringApplication;

public class Application {
	public static void main(String[] args) {
		 SpringApplication.run(AppConfig.class, args);
		 
//		 TrayIcon trayIcon = null;
//		if (SystemTray.isSupported()) {
//			SystemTray tray = SystemTray.getSystemTray();
//			Image image = null;
//			try {
//				image = ImageIO.read(Application.class.getClassLoader().getResourceAsStream("org/ancloud/wui/trek-mini-logo.png"));
//			} catch (IOException e1) {
//				e1.printStackTrace();
//			}
//			ActionListener listener = new ActionListener() {
//				public void actionPerformed(ActionEvent e) {
//					if (Desktop.isDesktopSupported()) {
//						Desktop desktop = Desktop.getDesktop();
//						try {
//							desktop.browse(new URI("http://localhost:9000"));
//						} catch (IOException | URISyntaxException ex) {
//							ex.printStackTrace();
//						}
//					}
//				}
//			};
//			PopupMenu popup = new PopupMenu();
//			MenuItem exitItem = new MenuItem("Exit");
//			exitItem.addActionListener( new ActionListener() {
//				public void actionPerformed(ActionEvent e) {
//					System.exit(0);
//				}
//			});
//			popup.add(exitItem);
//			MenuItem openBrowserItem = new MenuItem("Open app browser ...");
//			openBrowserItem.addActionListener(listener);
//			popup.add(openBrowserItem);
//			trayIcon = new TrayIcon(image, "ancloud-WUI", popup);
//			trayIcon.setImageAutoSize(true);
//			trayIcon.addActionListener(listener);
//			try {
//				tray.add(trayIcon);
//			} catch (AWTException e) {
//				System.err.println(e);
//			}
//		}
	}
}
