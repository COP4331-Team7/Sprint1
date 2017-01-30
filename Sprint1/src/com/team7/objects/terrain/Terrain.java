package com.team7.objects.terrain;

import com.team7.objects.areaEffects.AreaEffect;

import java.util.ArrayList;

public abstract class Terrain {
    private long id;
    private boolean isPassable;
    private int movementInfluence;
    private ArrayList<AreaEffect> areaEffects;

    public Terrain() {
    }

    public boolean isPassable() {
        return isPassable;
    }

    public void setPassable(boolean passable) {
        isPassable = passable;
    }

    public int getMovementInfluence() {
        return movementInfluence;
    }

    public void setMovementInfluence(int movementInfluence) {
        this.movementInfluence = movementInfluence;
    }

    public ArrayList<AreaEffect> getAreaEffects() {
        return areaEffects;
    }

    public void setAreaEffects(ArrayList<AreaEffect> areaEffects) {
        this.areaEffects = areaEffects;
    }
}
