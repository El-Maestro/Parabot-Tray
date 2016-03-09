package org.parabot.environment.tray;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.ImageIcon;

/**
 * The tray icon user interface.
 *
 * @author El Maestro
 */

public class TrayUI implements ActionListener {
	private TrayIcon icon;
	private static TrayUI instance;
	private PopupMenu menu;
	private MenuItem run, stop, pause, exit, none;
	private HashMap<MenuType, ArrayList<MenuItem>> customMenuItems;
	private final HashMap<String, TrayController> trayControllers;

	public TrayUI() {
		instance = this;
		customMenuItems = new HashMap<MenuType, ArrayList<MenuItem>>();
		menu = new PopupMenu();
		icon = new TrayIcon(getImage(), "Parabot - username", menu);
		icon.setImageAutoSize(true);
		fillMenu();
		try {
			getInstance().initialize();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		trayControllers = new HashMap<String, TrayController>();
	}

	private void fillMenu() {
		run = new MenuItem("Run");
		stop = new MenuItem("Stop");
		pause = new MenuItem("Pause");
		exit = new MenuItem("Exit Client");
		none = new MenuItem("None");

		run.addActionListener(this);
		stop.addActionListener(this);
		pause.addActionListener(this);
		exit.addActionListener(this);

		menu.add(run);
		menu.add(pause);
		menu.add(stop);
		menu.addSeparator();
		menu.add(exit);
		menu.addSeparator();
		menu.add(none);

		pause.setEnabled(false);
		stop.setEnabled(false);
	}

	public void addCustomMenuItems(MenuType type, MenuItem... items) {
		ArrayList<MenuItem> list = new ArrayList<MenuItem>(Arrays.asList(items));
		for (int index = 0; index < items.length; index++) {
			menu.insert(items[index], index);
			list.add(items[index]);
		}
		customMenuItems.put(type, list);
		menu.insertSeparator(items.length);
	}

	public void removeAllCustomMenusItems(MenuType type) {
		if (customMenuItems != null) {
			for (Entry<MenuType, ArrayList<MenuItem>> item : customMenuItems.entrySet()) {
				for (int index = 0; index < item.getValue().size(); index++) {
					menu.remove(item.getValue().get(index));
				}
			}
			menu.remove(0);
			customMenuItems.remove(type);
		}
	}

	public void removeCustomMenuItem(MenuItem item) {
		if (customMenuItems != null) {
			menu.remove(item);
			customMenuItems.remove(item);
			if (customMenuItems.isEmpty()) {
				menu.remove(0);
			}
		}
	}

	public HashMap<MenuType, ArrayList<MenuItem>> getCustomMenuItems() {
		return customMenuItems;
	}

	protected void initialize() throws AWTException {
		try {
			SystemTray.getSystemTray().add(icon);
			sendMessage("Parabot", "Welcome " + "username.", MessageType.INFO);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	protected static void sendMessage(String title, String message, MessageType messageType) {
		getInstance().icon.displayMessage(title, message, messageType);
	}

	protected static void sendMessage(TrayControllerType type, String message,
			MessageType messageType) {
		getInstance().icon.displayMessage(type.getType(), message, messageType);
	}

	protected MenuItem getRunMenu() {
		return run;
	}

	protected MenuItem getStopMenu() {
		return stop;
	}

	protected MenuItem getPauseMenu() {
		return pause;
	}

	public static TrayUI getInstance() {
		return instance == null ? instance = new TrayUI() : instance;
	}

	private Image getImage() {
		URL resource = getClass().getClassLoader()
				.getResource("org/parabot/environment/tray/icon.png");
		if (resource != null) {
			return new ImageIcon(resource).getImage();
		}
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TBotUI.getInstance().actionPerformed(e);
		System.exit(0);
	}

	public TrayController getTrayController(TrayControllerType type) {
		TrayController current;
		if ((current = trayControllers.get(type.getType().toLowerCase())) == null) {
			switch (type.getType().toLowerCase()) {
				case "script":
					current = new ScriptTrayController();
					break;
				case "random":
					current = new RandomTrayController();
					break;
			}
		}
		trayControllers.put(type.getType().toLowerCase(), current);
		return current;
	}
}
