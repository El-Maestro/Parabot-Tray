package org.parabot.environment.tray;

/**
 * The tray notification interface
 *
 * @author El Maestro
 */

public interface TrayNotifier {

	public void notifyUser(String message);

	public void warnUser(String message);

}
