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
    private AreaEffect areaEffect;
    private Decal decal;
    private Terrain terrain;
    private Structure structure;
    private Item item;
    private Resource resource;
    ArrayList<Unit> units;
    ArrayList<Army> armies;
    private int xCoordinate;
    private int yCoordinate;


    public Tile(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        units = new ArrayList<Unit>();
        armies = new ArrayList<Army>();

    }

    public Tile() {
        units = new ArrayList<Unit>();
        armies = new ArrayList<Army>();
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

    public Decal getDecal() {
        return decal;
    }

    public void setDecal(Decal decal) {
        this.decal = decal;
    }

// Unit and army helper functions

    // Adds unit to Tile's ArrayList of Units
    public Unit addUnitToTile(Unit unit) {

        // Physically add the unit
        this.units.add(unit);



        return unit;
    }

    // Removes unit from Tile's ArrayList of Units
    public Unit removeUnitFromTile(Unit unit) {

        this.units.remove(unit);

        return unit;
    }


    public ArrayList<Unit> getUnits() {
        return units;
    }

    public void setUnits(ArrayList<Unit> units) {
        this.units = units;
    }

    public ArrayList<Army> getArmies() {
        return armies;
    }

    public void setArmies(ArrayList<Army> armies) {
        this.armies = armies;
    }

    // Adds unit to Tile's ArrayList of Units
    public Army addArmyToTile(Army army) {

        // Physically add the unit
        this.armies.add(army);

        return army;
    }

    // Removes unit from Tile's ArrayList of Units
    public Army removeArmyFromTile(Army army) {

        this.armies.remove(army);

        return army;
    }


    public void handleAreaEffects(Unit unit) {
        if(this.getAreaEffect() != null){
            int effect = this.getAreaEffect().getHealthEffect();
            unit.getUnitStats().setHealth(unit.getUnitStats().getHealth() + effect);
            System.out.println(unit.getUnitStats().getHealth());
        }
    }


    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }
}
