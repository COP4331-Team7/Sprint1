package com.team7.controller;

import com.team7.objects.Army;
import com.team7.objects.Game;
import com.team7.objects.structure.Structure;
import com.team7.view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
                    view.getScreen().getMainScreen().getMainViewImage().rePaintMap();
                    view.getScreen().setCurrScreen("MAIN");
                }
            }
        });
        view.getScreen().getMainScreen().getScreenSelectButtons().getUnitScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == view.getScreen().getMainScreen().getScreenSelectButtons().getUnitScreenButton()) {
                    view.getScreen().setCurrScreen("UNIT_OVERVIEW");
                    view.getScreen().getUnitScreen().setUnits(game.getCurrentPlayer().getUnits());

                    if (view.getScreen().getUnitScreen().getArmyList().getSelectedValue() != null) {
                        String selectedArmy = view.getScreen().getUnitScreen().getArmyList().getSelectedValue();
                        List<Army> armies = game.getCurrentPlayer().getArmies();

                        for (Army a: armies) {
                            if (a.getName().equals(selectedArmy)) {
                                view.getScreen().getUnitScreen().getUnitsinArmyList().setModel(new AbstractListModel() {
                                    @Override
                                    public int getSize() {
                                        return a.getUnits().size();
                                    }

                                    @Override
                                    public Object getElementAt(int index) {
                                        return a.getUnits().get(index).getType() + " " + a.getUnits().get(index).getId();
                                    }
                                });

                                view.getScreen().getUnitScreen().setQueueModel(a.getCommandQueue().getCommands());
                                view.getScreen().getUnitScreen().repaint();
                            }
                        }
                    }
                }
            }
        });
        view.getScreen().getMainScreen().getScreenSelectButtons().getStructureScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == view.getScreen().getMainScreen().getScreenSelectButtons().getStructureScreenButton()) {
                    view.getScreen().setCurrScreen("STRUCTURE_OVERVIEW");
                    view.getScreen().getStructureScreen().setStructures(game.getCurrentPlayer().getStructures());

                    if (view.getScreen().getStructureScreen().getStructureList().getSelectedValue() != null) {
                        String selectedStructure = view.getScreen().getStructureScreen().getStructureList().getSelectedValue();
                        List<Structure> structures = game.getCurrentPlayer().getStructures();
                        Structure selectedStructureObject = null;

                        for (Structure s: structures) {
                            if(selectedStructure.equals(s.getType() + " " + s.getId())) {
                                selectedStructureObject = s;
                            }
                        }

                        view.getScreen().getStructureScreen().setCommands(selectedStructureObject.getCommandQueue().getCommands());
                        view.getScreen().getStructureScreen().repaint();
                    }
                }
            }
        });

        // add ActionListeners screen selection buttons on MainScreen
        view.getScreen().getUnitScreen().getScreenSelectButtons().getMainScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == view.getScreen().getUnitScreen().getScreenSelectButtons().getMainScreenButton()) {
                    view.getScreen().getMainScreen().getMainViewImage().rePaintMap();
                    view.getScreen().setCurrScreen("MAIN");
                }
            }
        });
        view.getScreen().getUnitScreen().getScreenSelectButtons().getUnitScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == view.getScreen().getUnitScreen().getScreenSelectButtons().getUnitScreenButton()) {
                    view.getScreen().setCurrScreen("UNIT_OVERVIEW");
                    view.getScreen().getUnitScreen().setUnits(game.getCurrentPlayer().getUnits());
                    String selectedArmy = view.getScreen().getUnitScreen().getArmyList().getSelectedValue();
                    List<Army> armies = game.getCurrentPlayer().getArmies();

                    for (Army a: armies) {
                        if (a.getName().equals(selectedArmy)) {
                            view.getScreen().getUnitScreen().getUnitsinArmyList().setModel(new AbstractListModel() {
                                @Override
                                public int getSize() {
                                    return a.getUnits().size();
                                }

                                @Override
                                public Object getElementAt(int index) {
                                    return a.getUnits().get(index).getType() + " " + a.getUnits().get(index).getId();
                                }
                            });

                            view.getScreen().getUnitScreen().setQueueModel(a.getCommandQueue().getCommands());
                            view.getScreen().getUnitScreen().repaint();
                        }
                    }
                }
            }
        });
        view.getScreen().getUnitScreen().getScreenSelectButtons().getStructureScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == view.getScreen().getUnitScreen().getScreenSelectButtons().getStructureScreenButton()) {
                    view.getScreen().setCurrScreen("STRUCTURE_OVERVIEW");
                    view.getScreen().getStructureScreen().setStructures(game.getCurrentPlayer().getStructures());

                    if (view.getScreen().getStructureScreen().getStructureList().getSelectedValue() != null) {
                        String selectedStructure = view.getScreen().getStructureScreen().getStructureList().getSelectedValue();
                        List<Structure> structures = game.getCurrentPlayer().getStructures();
                        Structure selectedStructureObject = null;

                        for (Structure s: structures) {
                            if(selectedStructure.equals(s.getType() + " " + s.getId())) {
                                selectedStructureObject = s;
                            }
                        }

                        view.getScreen().getStructureScreen().setCommands(selectedStructureObject.getCommandQueue().getCommands());
                        view.getScreen().getStructureScreen().repaint();
                    }
                }
            }
        });

        // add ActionListeners screen selection buttons on MainScreen
        view.getScreen().getStructureScreen().getScreenSelectButtons().getMainScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == view.getScreen().getStructureScreen().getScreenSelectButtons().getMainScreenButton()) {
                    view.getScreen().getMainScreen().getMainViewImage().rePaintMap();
                    view.getScreen().setCurrScreen("MAIN");
                }
            }
        });
        view.getScreen().getStructureScreen().getScreenSelectButtons().getUnitScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == view.getScreen().getStructureScreen().getScreenSelectButtons().getUnitScreenButton()) {
                    view.getScreen().setCurrScreen("UNIT_OVERVIEW");
                    view.getScreen().getUnitScreen().setUnits(game.getCurrentPlayer().getUnits());
                    String selectedArmy = view.getScreen().getUnitScreen().getArmyList().getSelectedValue();
                    List<Army> armies = game.getCurrentPlayer().getArmies();

                    for (Army a: armies) {
                        if (a.getName().equals(selectedArmy)) {
                            view.getScreen().getUnitScreen().getUnitsinArmyList().setModel(new AbstractListModel() {
                                @Override
                                public int getSize() {
                                    return a.getUnits().size();
                                }

                                @Override
                                public Object getElementAt(int index) {
                                    return a.getUnits().get(index).getType() + " " + a.getUnits().get(index).getId();
                                }
                            });

                            view.getScreen().getUnitScreen().setQueueModel(a.getCommandQueue().getCommands());
                            view.getScreen().getUnitScreen().repaint();
                        }
                    }
                }
            }
        });
        view.getScreen().getStructureScreen().getScreenSelectButtons().getStructureScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == view.getScreen().getStructureScreen().getScreenSelectButtons().getStructureScreenButton()) {
                    view.getScreen().setCurrScreen("STRUCTURE_OVERVIEW");
                    view.getScreen().getStructureScreen().setStructures(game.getCurrentPlayer().getStructures());

                    if (view.getScreen().getStructureScreen().getStructureList().getSelectedValue() != null) {
                        String selectedStructure = view.getScreen().getStructureScreen().getStructureList().getSelectedValue();
                        List<Structure> structures = game.getCurrentPlayer().getStructures();
                        Structure selectedStructureObject = null;

                        for (Structure s: structures) {
                            if(selectedStructure.equals(s.getType() + " " + s.getId())) {
                                selectedStructureObject = s;
                            }
                        }

                        view.getScreen().getStructureScreen().setCommands(selectedStructureObject.getCommandQueue().getCommands());
                        view.getScreen().getStructureScreen().repaint();
                    }
                }
            }
        });


    }
}
