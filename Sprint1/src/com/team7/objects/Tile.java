package com.team7.objects;

import com.team7.objects.areaEffects.AreaEffect;
import com.team7.objects.terrain.Terrain;

import java.util.ArrayList;

/**
 * Created by anip on 29/01/17.
 */
public class Tile {
    private int id;
    private AreaEffect areaEffect;
    private Terrain terrain;
    private Structure structure;
    private Item item;
    ArrayList<Unit> units;
    public Tile(){

    }
}
