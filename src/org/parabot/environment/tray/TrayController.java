package org.parabot.environment.tray;

import java.awt.SystemTray;
import java.awt.TrayIcon.MessageType;

public class TrayController implements TrayNotifier {
	// This could be final, using an if/else statement instead of just if
	private static final boolean supported = SystemTray.isSupported();

	// We could define the type here, so it has the ability to act as any controller - script, random, etc
	protected final String type;

	// We should not be capable of creating a new TrayController, only this class package
	protected TrayController(String type) {
		this.type = type;

		if (!supported){
			throw new UnsupportedOperationException();
		}
	}

	@Override
	public void notifyUser(String message) {
		messageUser(message, MessageType.INFO);
	}

	@Override
	public void warnUser(String message) {
		messageUser(message, MessageType.WARNING);
	}

	public void messageUser(String message, MessageType messageType){
		if (supported) {
			TrayUI.getInstance().sendMessage(
					type,
					message,
					messageType
			);
		}
	}

}
