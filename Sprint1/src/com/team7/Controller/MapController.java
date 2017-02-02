package com.team7.Controller;


import com.team7.View.*;
import com.team7.objects.Game;

public class MapController {
 private Game game;
 private MyFrame view;

    public MapController(Game game, MyFrame view) {
        this.game = game;
        this.view = view;

    }
    public void createMap(){
        view.getMainScreen();
    }
}
