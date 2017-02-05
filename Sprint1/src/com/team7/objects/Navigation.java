package com.team7.objects;

import java.util.ArrayList;

/**
 * Start Tile
 * End Tile
 * Queue of 'cursor' commands
 */
public class Navigation {
    private ArrayList<Tile> path;
    private Tile src;
    private Tile end;
    public Navigation(ArrayList<Tile> path, Tile startTile, Tile end){
        this.path = path;
        this.src = startTile;
        this.end = end;

    }


}
