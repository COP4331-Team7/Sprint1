package com.team7.objects;

import com.team7.objects.structure.Structure;
import com.team7.objects.unit.Unit;

public class Player {
    private Unit[] units;
    private Structure[] structures;
    private Army[] armies;
    private int research;
    private int construction;
    private int elixer;
    private boolean isDefeated;

    public Player() {
        units = new Unit[25];
        structures = new Structure[10];
        armies = new Army[10];
        research = 0;
        construction = 0;
        elixer = 50;
        isDefeated = false;
    }

    public Unit[] getUnits() {
        return units;
    }

    public void setUnits(Unit[] units) {
        this.units = units;
    }

    public Structure[] getStructures() {
        return structures;
    }

    public void setStructures(Structure[] structures) {
        this.structures = structures;
    }

    public Army[] getArmies() {
        return armies;
    }

    public void setArmies(Army[] armies) {
        this.armies = armies;
    }

    public int getResearch() {
        return research;
    }

    public void setResearch(int research) {
        this.research = research;
    }

    public int getConstruction() {
        return construction;
    }

    public void setConstruction(int construction) {
        this.construction = construction;
    }

    public boolean isDefeated() {
        return isDefeated;
    }

    public void setDefeated(boolean defeated) {
        isDefeated = defeated;
    }

    public int getElixer() { return elixer; }

    public void setElixer(int elixer) { this.elixer = elixer; }

}
