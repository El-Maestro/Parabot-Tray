package org.parabot.environment.tray;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class otherClass implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command == "new item") {
			System.out.println("here");
		}
	}

}
