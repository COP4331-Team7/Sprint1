package com.team7.controller;

import com.team7.view.*;
import com.team7.objects.*;

public class MainScreenController {
 private Game game;
 private View view;

    public MainScreenController(Game game, View view) {
        this.game = game;
        this.view = view;
        setMap( game.getMap() );
        setCurrentPlayer( game.getCurrentPlayer() );
    }

    public void setMap(  Map map ) {
        view.getScreen().getMainScreen().getMainViewImage().setMap( map );
    }

    public void setCurrentPlayer( Player player ) {
        view.getScreen().getMainScreen().getMainViewImage().setCurrentPlayer( player );
        view.getScreen().getMainScreen().getMainViewInfo().setCurrentPlayer(  player );
        view.getScreen().getMainScreen().getCommand().setCurrentPlayer(  player );
    }


}
