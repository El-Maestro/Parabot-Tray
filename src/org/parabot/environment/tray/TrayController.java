package org.parabot.environment.tray;

import java.awt.SystemTray;
import java.awt.TrayIcon.MessageType;

public class TrayController {
	private boolean supported;

	public TrayController() throws UnsupportedOperationException {
		if (!SystemTray.isSupported()) {
			System.out.println("System tray not supported.");
			supported = false;
		}
		System.out.println("System tray supported.");
		supported = true;
	}

	public void sendTrayNotification(String title, String message, MessageType messageType) {
		if (supported) {
			TrayUI.sendMessage(title, message, messageType);
		}
	}

}
