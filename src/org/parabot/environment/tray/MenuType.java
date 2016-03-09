package org.parabot.environment.tray;

/**
 * Type of tray icon menu.
 * 
 * @author Bautista
 *
 */

public enum MenuType {

	SCRIPT("Script", 1), RANDOM("Random", 2);

	private String type;
	private int typeIndex;

	MenuType(String type, int typeIndex) {
		this.type = type;
		this.typeIndex = typeIndex;
	}

	public String getType() {
		return type;
	}

	public int getTypeIndex() {
		return typeIndex;
	}

}
