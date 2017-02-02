package com.team7;

import com.team7.Controller.HomeScreenController;
import com.team7.View.*;
import com.team7.Controller.MapController;
import com.team7.objects.*;

public class Main {

    public static void main(String[] args) {

        // Map testing
//        Map map = new Map();
//        map.setMapDetails();
        Player player1 = new Player();
        Player player2 =new Player();
        Game game = new Game(player1,player2);

        MyFrame view = new MyFrame(800, 600);

        MapController map = new MapController(game, view);
        HomeScreenController hs = new HomeScreenController(game, view);

        map.createMap();
    }





}
