package com.team7.objects;

import com.team7.objects.areaEffects.AreaEffect;
import com.team7.objects.items.Item;
import com.team7.objects.resource.Resource;
import com.team7.objects.structure.Structure;
import com.team7.objects.terrain.Terrain;
import com.team7.objects.unit.Unit;

import java.util.ArrayList;

/*
 * For the 1st iteration, a Tile will have a pre-specified Terrain
 * All other attributes will be generated
 */
public class Tile {
    private int id;
    private AreaEffect areaEffect;
    private Terrain terrain;
    private Structure structure;
    private Item item;
    private Resource resource;
    ArrayList<Unit> units;


    public Tile(Terrain terrain) {
        this.terrain = terrain;
    }

    public AreaEffect getAreaEffect() {
        return areaEffect;
    }

    public void setAreaEffect(AreaEffect areaEffect) {
        this.areaEffect = areaEffect;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public void setUnits(ArrayList<Unit> units) {
        this.units = units;
    }
}
