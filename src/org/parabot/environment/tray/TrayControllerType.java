package org.parabot.environment.tray;

/**
 * 
 * @author Bautista, JKetelaar
 *
 */
public enum TrayControllerType {

	SCRIPT("Script"), RANDOM("Random");

	private String type;

	TrayControllerType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

}
