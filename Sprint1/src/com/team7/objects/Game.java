package com.team7.objects;

import com.team7.objects.Map;
import com.team7.objects.Player;

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
        map = new Map();
        
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
