package com.team7.objects;

import com.team7.ProbabilityGenerator;
import com.team7.objects.areaEffects.ElixirShower;
import com.team7.objects.items.Obstacle;
import com.team7.objects.resource.Elixir;
import com.team7.objects.terrain.Desert;
import com.team7.objects.terrain.FlatLand;
import com.team7.objects.terrain.Hills;
import com.team7.objects.terrain.Mountains;

public class Map {
    private long id;
    private String name;
    private Tile[][] tiles;

    public Map() {
        tiles = new Tile[2][2];
        tiles[0][0] = new Tile(new Desert());
        tiles[0][1] = new Tile(new FlatLand());
        tiles[1][0] = new Tile(new Hills());
        tiles[1][1] = new Tile(new Mountains());

        name = "Test Map, 2x2";
    }

    public void setTileDetails
            (double percentOfMapWithItems, double percentOfMapWithAreaEffects, double percentOfMapWithResources){
        populateMap(percentOfMapWithItems, percentOfMapWithAreaEffects, percentOfMapWithResources);
    }

    private void populateMap
            (double percentOfMapWithItems, double percentOfMapWithAreaEffects, double percentOfMapWithResources) {
        for (Tile[] tileArray: tiles){
            for (Tile tile: tileArray) {
                if (ProbabilityGenerator.willOccur(percentOfMapWithItems)){
                    tile.setItem(new Obstacle());
                }
                if (ProbabilityGenerator.willOccur(percentOfMapWithAreaEffects)){
                    tile.setAreaEffect(new ElixirShower());
                }
                if (ProbabilityGenerator.willOccur(percentOfMapWithResources)){
                    tile.setResource(new Elixir());
                }
            }
        }
    }

}
