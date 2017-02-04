package com.team7.controller;

import com.team7.objects.Army;
import com.team7.view.*;
import com.team7.objects.Game;
import com.team7.objects.Player;
import com.team7.objects.unit.Unit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class UnitScreenController {
    private Game game;
    private View view;

    public UnitScreenController(Game game, View view) {
        this.game = game;
        this.view = view;
        setUnits((ArrayList<Unit>) game.getCurrentPlayer().getUnits());
        addActionListeners();
    }

    void setUnits(ArrayList<Unit> units) {
        view.getScreen().getUnitScreen().setUnits(units);
    }

    private void addActionListeners() {

        //Add Action Listener for "Create Army" button
        view.getScreen().getUnitScreen().getAddArmyButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == view.getScreen().getUnitScreen().getAddArmyButton()) {
                    String unitString = (String) view.getScreen().getUnitScreen().getUnitList().getSelectedValue();
                    if (unitString == null) {
                        view.getScreen().getUnitScreen().displayMessage("Select a unit to create an army with");
                        return;
                    } else if (game.getCurrentPlayer().getArmies().size() >= 10) {
                        view.getScreen().getUnitScreen().displayMessage("You already have 10 armies");
                        return;
                    }
                    //System.out.println("Create Army hit with fetched unit string " + unitString);
                    Unit selected = null;
                    List<Unit> units = game.getCurrentPlayer().getUnits();
                    for (Unit u : units) {
                        String cur = u.getType() + " " + u.getId();
                        if (cur.equals(unitString)) {
                            System.out.println("Found unit " + u.getType());
                            selected = u;
                            if (!u.getType().equals("Colonist") && !u.getType().equals("Explorer")) {
                                if (u.getArmy() == null) {
                                    Army newArmy = new Army(selected.getLocation(), game.getCurrentPlayer());
                                    newArmy.addUnitToArmy(u);
                                    game.getCurrentPlayer().addArmy(newArmy);
                                    view.getScreen().getUnitScreen().getArmyModel().addElement(newArmy.getName());
                                    view.getScreen().getUnitScreen().changeListVal();
                                    view.getScreen().getUnitScreen().repaint();
                                    System.out.println("Created " + newArmy.getName());
                                    System.out.println("There are now " + game.getCurrentPlayer().getArmies().size() + " armies.");
                                } else {
                                    view.getScreen().getUnitScreen().displayMessage("That unit is already in an army");
                                }
                            } else {
                                view.getScreen().getUnitScreen().displayMessage("You can't construct an army with that type of unit");
                            }
                            break;
                        }
                    }
                } else {
                    System.out.println("ERROR");
                }
            }
        });

        //Add ActionListener for disband armies button
        view.getScreen().getUnitScreen().getDisbandArmyButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == view.getScreen().getUnitScreen().getDisbandArmyButton()) {
                    String selectedArmy = (String) view.getScreen().getUnitScreen().getArmyList().getSelectedValue();
                    List<Army> armies = game.getCurrentPlayer().getArmies();

                    for (Army a: armies) {
                        String cur = a.getName();
                        if (cur == selectedArmy) {

                        }
                    }
                }
            }
        });
    }



}
