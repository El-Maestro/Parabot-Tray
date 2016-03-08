package org.parabot.environment.tray;

import java.awt.SystemTray;
import java.awt.TrayIcon.MessageType;

public class TrayController implements TrayNotifier {
	private boolean supported;

	public TrayController() throws UnsupportedOperationException {
		if (!SystemTray.isSupported()) {
			System.out.println("System tray not supported.");
			supported = false;
		}
		System.out.println("System tray supported.");
		supported = true;
	}

	public TrayNotifier getController() {
		return new TrayController();
	}

	@Override
	public void notifyUser(String message) {
		if (supported) {
			TrayUI.sendMessage("Script", message, MessageType.INFO);
		}

	}

	@Override
	public void warnUser(String message) {
		if (supported) {
			TrayUI.sendMessage("Script", message, MessageType.WARNING);
		}
	}

}
