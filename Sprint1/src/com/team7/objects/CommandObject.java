package com.team7.objects;

import java.util.ArrayList;

public class CommandObject {

    private String commandString;
    private ArrayList<Tile> movementTiles;

    public CommandObject() {
            commandString = "";
            movementTiles = new ArrayList<Tile>();
    }

    public CommandObject(String string, ArrayList<Tile> tileList) {
        commandString = string;
        movementTiles = tileList;
    }

    public String getCommandString() {
        return commandString;
    }

    public void setCommandString(String commandString) {
        this.commandString = commandString;
    }

    public ArrayList<Tile> getMovementTiles() {
        return movementTiles;
    }

    public void setMovementTiles(ArrayList<Tile> movementTiles) {
        this.movementTiles = movementTiles;
    }
}
