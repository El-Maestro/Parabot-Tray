package org.parabot.environment.tray;

import java.awt.TrayIcon.MessageType;

/**
 * The tray notification interface
 *
 * @author El Maestro
 */

public interface TrayNotifier {

	public void notifyUser(String title, String message, MessageType messageType);

}
