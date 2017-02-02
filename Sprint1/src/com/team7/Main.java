package com.team7;

import com.team7.Controller.HomeScreenController;
import com.team7.Controller.ScreenSelectController;
import com.team7.View.*;
import com.team7.Controller.MapController;
import com.team7.objects.*;
import javafx.stage.Screen;

public class Main {

    public static void main(String[] args) {

        // Map testing
//        Map map = new Map();
//        map.setMapDetails();
        Player player1 = new Player();
        Player player2 =new Player();
        Game game = new Game(player1,player2);

        MyFrame view = new MyFrame(800, 600);

        MapController mapController = new MapController(game, view);
        HomeScreenController homeScreenController = new HomeScreenController(game, view);
        ScreenSelectController screenSelectController = new ScreenSelectController(view);



    }





}
