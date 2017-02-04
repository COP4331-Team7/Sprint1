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
        setArmies((ArrayList<Army>)game.getCurrentPlayer().getArmies());
    }

    void setUnits(ArrayList<Unit> units) {
        view.getScreen().getUnitScreen().setUnits(units);
    }

    void setArmies(ArrayList<Army> armies) {
        view.getScreen().getUnitScreen().setArmies(armies);
    }

    void addActionListeners() {
        //Add Action Listener for "Create Army" button
        view.getScreen().getUnitScreen().getAddArmyButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == view.getScreen().getUnitScreen().getAddArmyButton()) {
                    System.out.println("Create Army hit");
                    String unitString = (String) view.getScreen().getUnitScreen().getUnitList().getSelectedValue();
                    Unit selected = null;
                    List<Unit> units = game.getCurrentPlayer().getUnits();
                    for (Unit u : units) {
                        String cur = u.getType() + " " + u.getId();
                        if (cur == unitString) {
                            selected = u;
                            Army newArmy = new Army(selected.getLocation(), game.getCurrentPlayer());
                            game.getCurrentPlayer().addArmy(newArmy);
                            System.out.println("Created Army" + newArmy.getName());
                            break;
                        }
                    }
                }
            }
        });
    }

}
