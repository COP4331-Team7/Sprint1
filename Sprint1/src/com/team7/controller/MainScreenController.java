package com.team7.controller;

import com.team7.objects.unit.Unit;
import com.team7.view.*;
import com.team7.objects.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
        view.getScreen().getMainScreen().getMainViewInfo().setCurrentPlayer( player );
        view.getScreen().getMainScreen().getCommand().setCurrentPlayer(  player );
    }

    private void addActionListeners() {
        view.getScreen().getMainScreen().getCommand().getEndTurnButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == view.getScreen().getMainScreen().getCommand().getEndTurnButton()) {
                    game.nextTurn();
                    view.getScreen().getMainScreen().getMainViewInfo().clearStats();
                    view.getScreen().getMainScreen().getCommand().clearCommand();
                    setCurrentPlayer( game.getCurrentPlayer() );
                    view.getScreen().getUnitScreen().setArmies((ArrayList<Army>) game.getCurrentPlayer().getArmies());
                    view.getScreen().getUnitScreen().setUnits((ArrayList<Unit>) game.getCurrentPlayer().getUnits());
                    view.getScreen().getMainScreen().giveCommandFocus();
                }
            }
        } );
        view.getScreen().getMainScreen().getCommand().getExecuteCommandButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == view.getScreen().getMainScreen().getCommand().getExecuteCommandButton()) {
                    System.out.println("Player " + game.getTurn() + "'s command: ");
                    view.getScreen().getMainScreen().getCommand().queueCommand();
                    view.getScreen().getMainScreen().giveCommandFocus();
                }
            }
        } );
    }


}
