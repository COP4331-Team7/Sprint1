package com.team7.objects;

import com.team7.objects.areaEffects.AreaEffect;
import com.team7.objects.items.Item;
import com.team7.objects.structure.Structure;
import com.team7.objects.terrain.Terrain;
import com.team7.objects.unit.Unit;

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
