package com.team7.objects;

import com.team7.objects.Map;
import com.team7.objects.Player;
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
    /*Int value representing which player's turn it is.
    '0' means it is player 1's turn, '1' mean's it is player 2'*/
    private Player currentPlayer;

    public Game(Player p1, Player p2) {
        players[0] = p1;
        players[1] = p2;
        turn = 0;
        currentPlayer = p1;
    }

    //Initializes the map, and runs the turns. Ends the game when a player is defeated
    public void startGame() {

        this.map = new Map();

        // Put initial 2 explorers and colonists into Player's array to start game
        // Player 1 starts in top right, player 2 starts in bottom left
        players[0].addUnit(new Explorer(this.map.getGrid()[2][17]));
        players[0].addUnit(new Explorer(this.map.getGrid()[2][18]));
        players[0].addUnit(new Colonist(this.map.getGrid()[3][17]));

        players[1].addUnit(new Explorer(this.map.getGrid()[18][2]));
        players[1].addUnit(new Explorer(this.map.getGrid()[18][3]));
        players[1].addUnit(new Colonist(this.map.getGrid()[17][2]));


        while (!players[0].isDefeated() && !players[1].isDefeated()) {
            //currentPlayer.takeTurn(); --TODO
            nextTurn();
        }

        endGame();
    }

    //Switches the turn to the next player
    public void nextTurn() {
        
        if(currentPlayer = p1) 
            currentPlayer = p2;
        else 
            currentPlayer = p1;
        
        turn += 1 % 2;
    }
    
    //Get the current player
    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    

    public void endGame() {
        
        System.exit(0);
        
        /*   --TODO--
        Display a game over splash screen and exit the program, gunna wait unti the GUI is integrated
        to be able to do this. */
    }
}
