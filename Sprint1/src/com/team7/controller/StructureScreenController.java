package com.team7.controller;

import com.team7.objects.structure.Structure;
import com.team7.objects.structure.StructureStats;
import com.team7.view.*;
import com.team7.objects.Game;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.ArrayList;
import java.util.List;

public class StructureScreenController {
    private Game game;
    private View view;

    public StructureScreenController(Game game, View view) {
        this.game = game;
        this.view = view;
        setStructures();
        addActionListeners();
    }

    private void setStructures() {view.getScreen().getStructureScreen().setStructures((ArrayList<Structure>) game.getCurrentPlayer().getStructures());}

    //List selector for structure select
    private void addActionListeners() {
        view.getScreen().getStructureScreen().getStructureList().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                //System.out.println("New Structure selected");
                String selectedStructure = view.getScreen().getStructureScreen().getStructureList().getSelectedValue();
                List<Structure> structures = game.getCurrentPlayer().getStructures();
                Structure selectedStructureObject = null;

                for (Structure s: structures) {
                    if(selectedStructure.equals(s.getType() + " " + s.getId())) {
                        selectedStructureObject = s;
                    }
                }

                view.getScreen().getStructureScreen().getStatsTextArea().setText(getStats(selectedStructureObject));
                view.getScreen().getStructureScreen().setCommands(selectedStructureObject.getCommandQueue().getCommands());
                view.getScreen().getStructureScreen().repaint();
            }
        });
    }

    private String getStats(Structure s) {
        StructureStats stats = s.getStats();
        String out = "Offensive Damage: " + stats.getOffensiveDamage() +
                "\nDefensive Damage: " + stats.getDefensiveDamage() +
                "\nArmor: " + stats.getArmor() +
                "\nProduction Rates: " + stats.getProductionRates() +
                "\nHealth: " + stats.getHealth() +
                "\nUpkeep: " + stats.getUpkeep();
        return out;
    }


}
