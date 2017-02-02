package com.team7;

import GUI_src.MainViewImage;
import GUI_src.MyFrame;
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
        MapController map = new MapController(game,new MyFrame(800,600));
        map.createMap();
    }
}
