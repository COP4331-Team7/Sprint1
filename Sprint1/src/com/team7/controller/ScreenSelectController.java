package com.team7.controller;

import com.team7.objects.Game;
import com.team7.view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScreenSelectController {

    private View view;
    private Game game;

    public ScreenSelectController(View view, Game game) {
        this.view = view;
        this.game = game;
        addActionListeners();
    }

    private void addActionListeners() {

        // add ActionListeners screen selection buttons on MainScreen
        view.getScreen().getMainScreen().getScreenSelectButtons().getMainScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == view.getScreen().getMainScreen().getScreenSelectButtons().getMainScreenButton()) {
                    view.getScreen().setCurrScreen("MAIN");
                }
            }
        });
        view.getScreen().getMainScreen().getScreenSelectButtons().getUnitScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == view.getScreen().getMainScreen().getScreenSelectButtons().getUnitScreenButton()) {
                    view.getScreen().setCurrScreen("UNIT_OVERVIEW");
                    view.getScreen().getUnitScreen().setUnits(game.getCurrentPlayer().getUnits());
                }
            }
        });
        view.getScreen().getMainScreen().getScreenSelectButtons().getStructureScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == view.getScreen().getMainScreen().getScreenSelectButtons().getStructureScreenButton()) {
                    view.getScreen().setCurrScreen("STRUCTURE_OVERVIEW");
                    view.getScreen().getStructureScreen().setStructures(game.getCurrentPlayer().getStructures());
                }
            }
        });

        // add ActionListeners screen selection buttons on MainScreen
        view.getScreen().getUnitScreen().getScreenSelectButtons().getMainScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == view.getScreen().getUnitScreen().getScreenSelectButtons().getMainScreenButton()) {
                    view.getScreen().setCurrScreen("MAIN");
                }
            }
        });
        view.getScreen().getUnitScreen().getScreenSelectButtons().getUnitScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == view.getScreen().getUnitScreen().getScreenSelectButtons().getUnitScreenButton()) {
                    view.getScreen().setCurrScreen("UNIT_OVERVIEW");
                    view.getScreen().getUnitScreen().setUnits(game.getCurrentPlayer().getUnits());
                }
            }
        });
        view.getScreen().getUnitScreen().getScreenSelectButtons().getStructureScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == view.getScreen().getUnitScreen().getScreenSelectButtons().getStructureScreenButton()) {
                    view.getScreen().setCurrScreen("STRUCTURE_OVERVIEW");
                    view.getScreen().getStructureScreen().setStructures(game.getCurrentPlayer().getStructures());
                }
            }
        });

        // add ActionListeners screen selection buttons on MainScreen
        view.getScreen().getStructureScreen().getScreenSelectButtons().getMainScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == view.getScreen().getStructureScreen().getScreenSelectButtons().getMainScreenButton()) {
                    view.getScreen().setCurrScreen("MAIN");
                }
            }
        });
        view.getScreen().getStructureScreen().getScreenSelectButtons().getUnitScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == view.getScreen().getStructureScreen().getScreenSelectButtons().getUnitScreenButton()) {
                    view.getScreen().setCurrScreen("UNIT_OVERVIEW");
                    view.getScreen().getUnitScreen().setUnits(game.getCurrentPlayer().getUnits());
                }
            }
        });
        view.getScreen().getStructureScreen().getScreenSelectButtons().getStructureScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == view.getScreen().getStructureScreen().getScreenSelectButtons().getStructureScreenButton()) {
                    view.getScreen().setCurrScreen("STRUCTURE_OVERVIEW");
                    view.getScreen().getStructureScreen().setStructures(game.getCurrentPlayer().getStructures());
                }
            }
        });


    }
}
