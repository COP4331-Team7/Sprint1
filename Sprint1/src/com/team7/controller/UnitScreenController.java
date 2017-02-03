package com.team7.controller;

import com.team7.view.*;
import com.team7.objects.Game;
import com.team7.objects.Player;
import com.team7.objects.unit.Unit;

import java.util.ArrayList;
import java.util.List;

public class UnitScreenController {
    private Game game;
    private View view;

    public UnitScreenController(Game game, View view) {
        this.game = game;
        this.view = view;
        setUnits((ArrayList<Unit>) game.getCurrentPlayer().getUnits());
    }

    void setUnits(ArrayList<Unit> units) {
        view.getScreen().getUnitScreen().setUnits(units);
    }

}
