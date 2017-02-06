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
