package com.team7.objects;

import java.util.ArrayList;

public class Command {

    private String commandString;
    private ArrayList<Tile> movementTiles;

    public Command() {
            commandString = "";
            movementTiles = new ArrayList<Tile>();
    }

    public Command(String string, ArrayList<Tile> tileList) {
        commandString = string;
        movementTiles = tileList;
    }

}
