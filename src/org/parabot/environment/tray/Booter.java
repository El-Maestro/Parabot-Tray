package org.parabot.environment.tray;

import java.awt.MenuItem;

public class Booter {

	private static MenuItem item, item2, item3;

	public static void main(String[] args) {
		playAsClient();
	}

	private static void playAsClient() {
		TrayUI trayUI = TrayUI.getInstance();
		item = new MenuItem("new item");
		item2 = new MenuItem("new item2");
		item3 = new MenuItem("new item3");
		TrayUI.getInstance().addCustomMenuItems(MenuType.SCRIPT,
				new MenuItem[] { item, item2, item3 });
		TrayUI.getInstance().removeCustomMenuItem(item);
		TrayUI.getInstance().removeAllCustomMenusItems(MenuType.SCRIPT);
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		playAsScript(trayUI.getTrayController(TrayControllerType.SCRIPT));
	}

	private static void playAsScript(TrayNotifier notifier) {
		notifier.notifyUser("Welcome to my script");
		notifier.warnUser("lolol");
		// As you can see, I can call the interface method, but I cannot call any other method from
		// the TrayController, like #messageUser
	}

}