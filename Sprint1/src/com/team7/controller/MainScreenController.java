package com.team7.controller;

import com.team7.view.*;
import com.team7.objects.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreenController {
 private Game game;
 private View view;

    public MainScreenController(Game game, View view) {
        this.game = game;
        this.view = view;
        setMap( game.getMap() );
        setCurrentPlayer( game.getCurrentPlayer() );

        addActionListeners();
    }

    public void setMap(  Map map ) {
        view.getScreen().getMainScreen().getMainViewImage().setMap( map );
    }

    public void setCurrentPlayer( Player player ) {
        view.getScreen().getMainScreen().getMainViewImage().setCurrentPlayer( player );
        view.getScreen().getMainScreen().getCommand().setCurrentPlayer(  player );
    }

    private void addActionListeners() {
        view.getScreen().getMainScreen().getCommand().getEndTurnButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == view.getScreen().getMainScreen().getCommand().getEndTurnButton()) {
                    game.nextTurn();
                }
            }
        } );
    }


}
