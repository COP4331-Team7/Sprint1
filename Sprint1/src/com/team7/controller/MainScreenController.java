package com.team7.controller;

import com.team7.objects.structure.Base;
import com.team7.objects.structure.Structure;
import com.team7.objects.unit.Unit;
import com.team7.objects.unit.nonCombatUnit.Colonist;
import com.team7.view.*;
import com.team7.objects.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainScreenController {
 private Game game;
 private View view;
 private ArrayList<String> path = new ArrayList<String >();
 public ArrayList<Tile> queuedTiles = new ArrayList<>();
 Navigator navigator;

    public MainScreenController(Game game, View view) {
        this.game = game;
        this.view = view;
        setMap( game.getMap() );
        setCurrentPlayer( game.getCurrentPlayer() );
        view.getScreen().getMainScreen().getCommand().setScreen( view.getScreen() );
        view.getScreen().getMainScreen().getCommand().setController( this );    // give controller

        addActionListeners();
    }

    public void setMap(  Map map ) {
        view.getScreen().getMainScreen().getMainViewImage().setMap( map );
    }
    public void moveMode(Unit selected){ //called 1nce
        navigator = new Navigator(game.getMap(), selected);
    }
    public void moveMode(Army selected){ //called 1nce
        navigator = new Navigator(game.getMap(), selected);
    }

    public boolean sendCommand(char command){ //called per number keystroke

        return navigator.parseInputCommand(command);
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
                    view.getScreen().getUnitScreen().setUnits((ArrayList<Unit>) game.getCurrentPlayer().getUnits());
                    view.getScreen().getMainScreen().giveCommandFocus();
                    view.getScreen().getMainScreen().drawMap();
                }
            }
        } );
        view.getScreen().getMainScreen().getCommand().getExecuteCommandButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == view.getScreen().getMainScreen().getCommand().getExecuteCommandButton()) {
                    System.out.println("Player " + game.getTurn() + "'s command: ");
                    view.getScreen().getMainScreen().getCommand().extractCommand();
                    view.getScreen().getMainScreen().getCommand().clearCommand();
                    view.getScreen().getMainScreen().giveCommandFocus();
                }
            }
        } );
    }

    public MainViewInfo getStatusInfo() {
        return view.getScreen().getMainScreen().getMainViewInfo();
    }

    public MainViewImage getMainView() {
        return view.getScreen().getMainScreen().getMainViewImage();
    }



    public void updateModel() {
        if(navigator.updateModel()!=null){
            queuedTiles = navigator.updateModel();
            //Iterate through each tile in path
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    ArrayList<Tile> pathOfCursorTiles = navigator.updateModel();
                    for(int i = 0; i < pathOfCursorTiles.size(); i++){
                        if(!navigator.reDrawMapViaModel(pathOfCursorTiles.get(i), null)){
                            //user is out of movement, cut arraylist
                            for (int j = i; j > 0; j--){        //remove all elements from i and under
                                pathOfCursorTiles.remove(j);
                            }
                            queuedTiles = pathOfCursorTiles;
                            System.out.println("queuedTiles at cursor: " + queuedTiles.toString());
                            //send queuedTiles to the commandQ
                            return;
                        }
                        navigator.reDrawMapViaModel(pathOfCursorTiles.get(i), null);
                        view.getScreen().getMainScreen().getMainViewImage().zoomToDestination( navigator.updateModel().get(i).getxCoordinate() - 11/2, navigator.updateModel().get(i).getyCoordinate() - 7/2, 50);
                        view.getScreen().getMainScreen().getMainViewInfo().updateStats();

                        SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            view.getScreen().getMainScreen().drawMap();
                        }});
                        try {
                            Thread.sleep(400);
                        }catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                     }
                }
            });

            t.start();

        }
        }

        public void reDrawGUI() {
            view.getScreen().getMainScreen().drawMap();
        }


        public ArrayList<Tile> getQueuedTile() {
            System.out.println("getQdTiles: " + queuedTiles.toString());
            return queuedTiles;
        }

        public String getCommandString() {
            return view.getScreen().getMainScreen().getCommand().extractCommand();
        }




        // COMMANDS
        public void createBase(Unit unit){
            if(unit instanceof Colonist){
                ((Colonist) unit).buildBase();
            }
        }

        public void decomissionUnit(Unit unit) {
            unit.decommission();
            game.getCurrentPlayer().checkUnitArmyStructs();
        }

        public void powerUpUnit(Unit unit) {
            unit.powerUp();
        }

        public void powerDownUnit(Unit unit) {
            unit.powerDown();
        }

        // HARDCODED TO ADD TO FIRST ARMY
        public void reinforceArmy(Unit unit) {
            game.getCurrentPlayer().getArmies().get(0).addUnitToArmy(unit);
        }


        public void armyAttack(Army army, int dir) {
            army.attack(this.game.getMap(), dir);
        }

        public void armyDefend(Army army, int dir) {
            army.setDefenseDirection(dir);
        }

        public void disbandArmy(Army army) {
            army.disband();
        }

        public void decomissionArmy(Army army) {
            army.decommission();
            game.getCurrentPlayer().checkUnitArmyStructs();
        }

        public void powerDownArmy(Army army) {
            army.powerDown();
        }

        public void powerUpArmy(Army army) {
            army.powerUp();
        }

        public void cancelQueue(Army army) {
            army.getCommandQueue().getCommands().clear();
        }


        public void makeUnit(Structure base, String type) {
            ((Base) base).createUnit(type);
        }

        public void defendStructure(Structure base, int dir) {
            base.setDefenseDirection(dir);
        }

        public void healUnit(Structure base) {
            ((Base) base).healUnits();
        }

        public void decomissionStructure(Structure base) {
            base.decommission();
            game.getCurrentPlayer().checkUnitArmyStructs();
        }

        public void powerUpStructure(Structure base) {
            base.powerUp();
        }

        public void powerDownStructure(Structure base) {
            base.powerDown();
        }

        public void cancelQueueStructure(Structure base) {
            base.getCommandQueue().getCommands().clear();
        }


    }

