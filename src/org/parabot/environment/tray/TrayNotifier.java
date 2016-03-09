package org.parabot.environment.tray;

/**
 * The tray notification interface
 *
 * @author El Maestro
 */

public interface TrayNotifier {

	void notifyUser(String message);

	void warnUser(String message);

}
