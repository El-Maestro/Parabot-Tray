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
import java.util.ArrayList;
import java.util.HashMap;

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
	private HashMap<MenuItem, Integer> customMenuItems;

	public TrayUI() {
		if (!SystemTray.isSupported()) {
			System.out.println("System tray not supported.");
			return;
		}
		instance = this;
		customMenuItems = new HashMap<MenuItem, Integer>();
		menu = new PopupMenu();
		icon = new TrayIcon(getImage(), "Parabot - username", menu);
		icon.setImageAutoSize(true);
		fillMenu();
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

	public void addCustomMenuItems(MenuItem... items) {
		for (int index = 0; index < items.length; index++) {
			customMenuItems.put(items[index], index);
			menu.insert(items[index], index);
		}
		menu.insertSeparator(items.length);
	}

	public void removeAllCustomMenusItems() {
		if (customMenuItems != null) {
			for (MenuItem item : customMenuItems.keySet()) {
				menu.remove(item);
			}
			menu.remove(0);
			customMenuItems.clear();
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

	public HashMap<MenuItem, Integer> getCustomMenuItems() {
		return customMenuItems;
	}

	protected void initialize() throws AWTException {
		SystemTray.getSystemTray().add(icon);
		System.out.println("Tray icon initialized.");
		sendMessage("Parabot", "Welcome " + "username.", MessageType.INFO);
	}

	protected static void sendMessage(String title, String message, MessageType messageType) {
		getInstance().icon.displayMessage(title, message, messageType);
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
		// return new ImageIcon(Images.getResource("/storage/images/icon.png")).getImage();
		return new ImageIcon(
				getClass().getClassLoader().getResource("org/parabot/environment/tray/icon.png"))
						.getImage();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		switch (command) {
			case "Run":
				/*
				 * if (BotUI.getInstance().pauseScript) { BotUI.getInstance().pauseScript = false;
				 * BotUI.getInstance().pause.setEnabled(true);
				 * BotUI.getInstance().run.setEnabled(false); pause.setEnabled(true);
				 * run.setEnabled(false); BotUI.getInstance().setScriptState(Script.STATE_RUNNING);
				 * break; } new ScriptSelector().setVisible(true);
				 */
				break;
			case "Stop":
				// BotUI.getInstance().setScriptState(Script.STATE_STOPPED);
				break;
			case "Pause":
				/*
				 * BotUI.getInstance().setScriptState(Script.STATE_PAUSE);
				 * BotUI.getInstance().pause.setEnabled(false);
				 * BotUI.getInstance().run.setEnabled(true); pause.setEnabled(false);
				 * run.setEnabled(true); BotUI.getInstance().pauseScript = true;
				 */
				break;
			case "Exit Client":
				System.out.println("Exit Client");
				System.exit(0);
				break;
		}
	}
}
