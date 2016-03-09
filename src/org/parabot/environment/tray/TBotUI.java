package org.parabot.environment.tray;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author JKetelaar
 */
public class TBotUI implements ActionListener {


    private static TBotUI instance;

    public static TBotUI getInstance() {
        return instance == null ? instance = new TBotUI() : instance;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
