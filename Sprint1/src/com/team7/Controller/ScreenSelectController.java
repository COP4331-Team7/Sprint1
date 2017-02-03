package com.team7.Controller;

import com.team7.View.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScreenSelectController {

    private View view;

    public ScreenSelectController(View view) {
        this.view = view;
        addActionListeners();
    }

    private void addActionListeners() {

        // add ActionListeners screen selection buttons on MainScreen
        view.getFrame().getMainScreen().getScreenSelectButtons().getMainScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == view.getFrame().getMainScreen().getScreenSelectButtons().getMainScreenButton()) {
                    view.getFrame().setCurrScreen("MAIN");
                }
            }
        });
        view.getFrame().getMainScreen().getScreenSelectButtons().getUnitScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == view.getFrame().getMainScreen().getScreenSelectButtons().getUnitScreenButton()) {
                    view.getFrame().setCurrScreen("UNIT_OVERVIEW");
                }
            }
        });
        view.getFrame().getMainScreen().getScreenSelectButtons().getStructureScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == view.getFrame().getMainScreen().getScreenSelectButtons().getStructureScreenButton()) {
                    view.getFrame().setCurrScreen("STRUCTURE_OVERVIEW");
                }
            }
        });

        // add ActionListeners screen selection buttons on MainScreen
        view.getFrame().getUnitScreen().getScreenSelectButtons().getMainScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == view.getFrame().getUnitScreen().getScreenSelectButtons().getMainScreenButton()) {
                    view.getFrame().setCurrScreen("MAIN");
                }
            }
        });
        view.getFrame().getUnitScreen().getScreenSelectButtons().getUnitScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == view.getFrame().getUnitScreen().getScreenSelectButtons().getUnitScreenButton()) {
                    view.getFrame().setCurrScreen("UNIT_OVERVIEW");
                }
            }
        });
        view.getFrame().getUnitScreen().getScreenSelectButtons().getStructureScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == view.getFrame().getUnitScreen().getScreenSelectButtons().getStructureScreenButton()) {
                    view.getFrame().setCurrScreen("STRUCTURE_OVERVIEW");
                }
            }
        });

        // add ActionListeners screen selection buttons on MainScreen
        view.getFrame().getStructureScreen().getScreenSelectButtons().getMainScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == view.getFrame().getStructureScreen().getScreenSelectButtons().getMainScreenButton()) {
                    view.getFrame().setCurrScreen("MAIN");
                }
            }
        });
        view.getFrame().getStructureScreen().getScreenSelectButtons().getUnitScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == view.getFrame().getStructureScreen().getScreenSelectButtons().getUnitScreenButton()) {
                    view.getFrame().setCurrScreen("UNIT_OVERVIEW");
                }
            }
        });
        view.getFrame().getStructureScreen().getScreenSelectButtons().getStructureScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == view.getFrame().getStructureScreen().getScreenSelectButtons().getStructureScreenButton()) {
                    view.getFrame().setCurrScreen("STRUCTURE_OVERVIEW");
                }
            }
        });

    }
}
