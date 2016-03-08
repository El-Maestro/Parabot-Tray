package org.parabot.environment.tray;

import java.awt.AWTException;
import java.awt.MenuItem;

public class Booter {
	private static MenuItem item, item2, item3;

	public static void main(String[] args) throws AWTException {
		new TrayUI().initialize();
		item = new MenuItem("new item");
		item2 = new MenuItem("new item2");
		item3 = new MenuItem("new item3");
		TrayUI.getInstance().addCustomMenuItems(item, item2, item3);
		TrayUI.getInstance().removeCustomMenuItem(item);
		TrayController i = new TrayController();
		i.notifyUser("yes");
	}

}
