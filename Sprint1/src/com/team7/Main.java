package com.team7;

import com.team7.Controller.*;
import com.team7.View.*;
import com.team7.objects.*;
import javafx.stage.Screen;
import jdk.nashorn.internal.runtime.ECMAErrors;

public class Main {

    public static void main(String[] args) {

        // Model
        Player player1 = new Player();
        Player player2 = new Player();
        Game game      = new Game(player1,player2);

        // View
        View view = new View();

        waitForGUI( view );

        // Controller
        HomeScreenController homeScreenController     = new HomeScreenController(game, view);
        MainScreenController mainScreenController            = new MainScreenController(game, view);
        UnitScreenController unitScreenController          = new UnitScreenController(game, view);
        StructureScreenController structureScreenController = new StructureScreenController(game, view);
        ScreenSelectController screenSelectController = new ScreenSelectController(view); // this controller doesn't need to know model

    }

    private static void waitForGUI(View view) {
        long startTime = System.nanoTime();
        // waiting on view construction because:
        // in the main method new View() returns instantly because it schedules the GUI creation to be executed asynchronously on the EDT
        while( view.getFrame() == null ) {
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
