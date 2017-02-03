package com.team7;

import com.team7.controller.*;
import com.team7.view.*;
import com.team7.objects.*;

public class Main {

    public static void main(String[] args) {

        // Model
        Player player1 = new Player();
        Player player2 = new Player();
        Game game      = new Game(player1, player2);
        game.startGame();

        // view
        View view = new View();

        waitForGUI( view );

        // controller
        HomeScreenController homeScreenController     = new HomeScreenController(game, view);
        MainScreenController mainScreenController            = new MainScreenController(game, view);
        UnitScreenController unitScreenController          = new UnitScreenController(game, view);
        StructureScreenController structureScreenController = new StructureScreenController(game, view);
        ScreenSelectController screenSelectController = new ScreenSelectController(view); // this controller doesn't need to know model

        //      start the game
        //      while the game is not over
        //             let the current player issue commands
        //             once the current player has finished their turn
        //             give the other player control s
        //

    }

    private static void waitForGUI(View view) {
        long startTime = System.nanoTime();
        // waiting on view construction because:
        // in the main method new view() returns instantly because it schedules the GUI creation to be executed asynchronously on the EDT
        while( view.getScreen() == null ) {
            try {
                Thread.sleep(50);
            }
            catch (Exception e) {}
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;  // time elapsed in milliseconds
        System.out.println("view construction time: " + duration + " ms");
    }

}
