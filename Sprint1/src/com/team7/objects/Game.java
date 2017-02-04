package com.team7.objects;

import com.team7.objects.unit.nonCombatUnit.Colonist;
import com.team7.objects.unit.nonCombatUnit.Explorer;

/*
    The Game class is just inside of the boundary of our “model” (MVC paradigm).
    It keeps track of the Players, the Map, and watches for when a player is defeated, and facilitates gameplay.
*/
public class Game {
    //Array of Player objects representing the two players of the game
    private Player[] players = new Player[2];
    //The game map
    private Map map;
    //The turn number
    private int turn;
    //Current Player
    private Player currentPlayer;

    public Game(Player p1, Player p2) {
        players[0] = p1;
        players[1] = p2;
        turn = 0;
        currentPlayer = players[0];
    }

    //Initializes the map, and runs the turns. Ends the game when a player is defeated
    public void startGame() {

        // create map and populate with items/resources/area effects
        this.map = new Map();
        map.setMapDetails();

        // Put initial 2 explorers and colonists into Player's array to start game
        // Player 1 starts in top right, player 2 starts in bottom left
        players[0].addUnit(new Explorer(this.map.getGrid()[17][3], players[0]));
        players[0].addUnit(new Explorer(this.map.getGrid()[17][4], players[0]));
        players[0].addUnit(new Explorer(this.map.getGrid()[16][4], players[0]));
        players[0].addUnit(new Colonist(this.map.getGrid()[18][2], players[0]));

        players[1].addUnit(new Explorer(this.map.getGrid()[3][18], players[1]));
        players[1].addUnit(new Explorer(this.map.getGrid()[3][17], players[1]));
        players[1].addUnit(new Colonist(this.map.getGrid()[4][18], players[1]));

//
//        while (!players[0].isDefeated() && !players[1].isDefeated()) {
//            //currentPlayer.takeTurn(); --TODO
//            nextTurn();
//        }
//
//        endGame();
    }

    //Switches the turn to the next player
    public void nextTurn() {
        
        if(currentPlayer == players[0])
            currentPlayer = players[1];
        else 
            currentPlayer = players[0];
        
        turn = ++turn % 2;
    }

    //Get the current player
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    //Get the current turn
    public int getTurn() {
        return turn;
    }

    //Get the map
    public Map getMap() {
        return map;
    }

    //Get list of players
    public Player[] getPlayers() {
        return players;
    }

    public void setTurn(int num) {
        turn = num;
    }

    public void setCurrentPlayer(int num) {
        if (num > players.length-1) {
            System.out.println("ERROR: Out of bounds request for setCurrentPlayer()");
            return;
        }
        currentPlayer = players[num];
    }


    public void endGame() {
        
        System.exit(0);
        
        /*   --TODO--
        Display a game over splash screen and exit the program, gunna wait until the GUI is integrated
        to be able to do this. */
    }




    public Tile moveUnit(Player player,Tile current, String command,int commandIndex){
        Tile next = new Tile();
        if (command == "east") {
            next = map.getTile(current.getxCoordinate(), current.getyCoordinate() + 1);
        } else if (command == "west") {
            next = map.getTile(current.getxCoordinate(), current.getyCoordinate() - 1);
        } else if (command == "north") {
            next = map.getTile(current.getxCoordinate() + 1, current.getyCoordinate());
        } else if (command == "south") {
            next = map.getTile(current.getxCoordinate() - 1, current.getyCoordinate());
        }
        return next;

    }

    }
