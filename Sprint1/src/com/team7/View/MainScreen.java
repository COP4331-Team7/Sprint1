package com.team7.View;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MainScreen extends JPanel {

    private ScreenSelectButtons screenSelectBtns = null;
    private MainViewImage mainAreaView = null;
    private MainViewInfo mainStatusInfo = null;
    private Command commandSelecter = null;

    public MainScreen() {

        this.setLayout(new BorderLayout());

        screenSelectBtns = new ScreenSelectButtons();
        mainAreaView = new MainViewImage();
        mainStatusInfo = new MainViewInfo();
        commandSelecter = new Command();

        this.add( screenSelectBtns, BorderLayout.NORTH );
        this.add( mainAreaView, BorderLayout.CENTER );

        JPanel temp = new JPanel(); // create JPanel to hold two other JPanels
                                    // only one JPanel can be in a specific location, so we add 2 panels to a single panel
                                    // then add the single panel where we want both panels

        temp.add( mainStatusInfo, BorderLayout.WEST );
        temp.add( commandSelecter, BorderLayout.SOUTH);

        this.add( temp, BorderLayout.SOUTH );
    }

    public void giveCommandFocus() {          // give JPanel (commandSelecter) focus to 'make its KeyListener is active'
        commandSelecter.setFocusable(true);
        commandSelecter.requestFocus();
    }

    public ScreenSelectButtons getScreenSelectButtons() {
            return screenSelectBtns;
    }

    public void drawMap() {
        mainAreaView.drawMap();
    }

    public BufferedImage getMainViewImage() {
        return mainAreaView.getCurrImage();
    }
}
