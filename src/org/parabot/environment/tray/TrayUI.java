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
import java.util.HashMap;

import javax.swing.ImageIcon;

/**
 * The tray icon user interface.
 *
 * @author El Maestro
 */

public class TrayUI implements ActionListener {
	private static TrayUI instance;

	private TrayIcon icon;
	private PopupMenu menu;
	private MenuItem run, stop, pause, exit;

	private HashMap<MenuItem, Integer> customMenuItems;

	private final HashMap<String, TrayController> trayControllers;

	public TrayUI() {
		customMenuItems = new HashMap<>();
		menu = new PopupMenu();

		icon = new TrayIcon(getImage(), "Parabot - username", menu);
		icon.setImageAutoSize(true);

		fillMenu();

		this.initialize();

		trayControllers = new HashMap<>();
	}

	private void fillMenu() {
		run = new MenuItem("Run");
		stop = new MenuItem("Stop");
		pause = new MenuItem("Pause");
		exit = new MenuItem("Exit Client");

		run.addActionListener(this);
		stop.addActionListener(this);
		pause.addActionListener(this);
		exit.addActionListener(this);

		// Re-use your own code, cuz why not
		addCustomMenuItems(run, pause, stop);
		menu.addSeparator(); // TODO: See #addCustomMenuItems - As we shouldn't be calling this ourselves
		addCustomMenuItems(exit);

		pause.setEnabled(false);
		stop.setEnabled(false);
	}

	// TODO: What if a script adds items (or doesn't), should there be a separator? Yes, how are we gonna manage that
	// TODO: Seems like the order of the menu items is incorrect, exit is above run etc
	// TODO: Give the option to set the order (between our own limits, could not be below exit for example)
	public void addCustomMenuItems(MenuItem... items) {
		for (int index = 0; index < items.length; index++) {
			customMenuItems.put(items[index], index);
			menu.insert(items[index], index);
		}
		menu.insertSeparator(items.length);
	}

	// TODO: See #addCustomMenuItems
	public void removeAllCustomMenusItems() {
		if (customMenuItems != null) {
			for (MenuItem item : customMenuItems.keySet()) {
				menu.remove(item);
			}
			menu.remove(0);
			customMenuItems.clear();
		}
	}

	// TODO: See #addCustomMenuItems
	public void removeCustomMenuItem(MenuItem item) {
		if (customMenuItems != null) {
			menu.remove(item);
			customMenuItems.remove(item);
			if (customMenuItems.isEmpty()) {
				menu.remove(0);
			}
		}
	}

	private void initialize() {
		try {
			SystemTray.getSystemTray().add(icon);
			sendMessage("Parabot", "Welcome " + "username.", MessageType.INFO);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	protected void sendMessage(String title, String message, MessageType messageType) {
		this.icon.displayMessage(title, message, messageType);
	}

	public static TrayUI getInstance() {
		return instance == null ? instance = new TrayUI() : instance;
	}

	private Image getImage() {
		URL resource = getClass().getClassLoader().getResource("org/parabot/environment/tray/icon.png");
		if (resource != null) {
			return new ImageIcon(resource).getImage();
		}
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		TBotUI.getInstance().actionPerformed(e);
	}

	public TrayController getTrayController(String type) {
		TrayController current;
		if ((current = trayControllers.get(type.toLowerCase())) == null){
			switch (type.toLowerCase()){
				case "script":
					current = new ScriptTrayController();
					break;
				case "random":
					current = new RandomTrayController();
					break;
			}
		}
		trayControllers.put(type.toLowerCase(), current);
		return current;
	}
}
