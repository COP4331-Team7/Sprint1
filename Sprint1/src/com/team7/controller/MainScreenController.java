package com.team7.controller;

import com.team7.view.*;
import com.team7.objects.*;

import java.util.ArrayList;

public class MainScreenController {
 private Game game;
 private View view;

    public MainScreenController(Game game, View view) {
        this.game = game;
        this.view = view;
        setMap( game.getMap() );
    }

    public void setMap(  Map map ) {
        view.getScreen().getMainScreen().getMainViewImage().setMap( map );
    }
}
