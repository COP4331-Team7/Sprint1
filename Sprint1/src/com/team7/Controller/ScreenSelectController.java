package com.team7.Controller;

import com.team7.View.*;
import com.team7.objects.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScreenSelectController {

    private MyFrame view;

    public ScreenSelectController(MyFrame view) {
        this.view = view;
        addActionListeners();
    }

    private void addActionListeners() {

        // add ActionListeners screen selection buttons on MainScreen
        view.getScreenSelectButtons().getMainScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == view.getScreenSelectButtons()) {
                    view.setCurrScreen("MAIN");
                }
            }
        });
        view.getScreenSelectButtons().getUnitScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == view.getScreenSelectButtons()) {
                    view.setCurrScreen("UNIT_OVERVIEW");
                }
            }
        });
        view.getScreenSelectButtons().getStructureScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == view.getScreenSelectButtons()) {
                    view.setCurrScreen("STRUCTURE_OVERVIEW");
                }
            }
        });

    }
}
