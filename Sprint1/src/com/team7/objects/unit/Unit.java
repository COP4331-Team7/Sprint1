package com.team7.objects.unit;

import com.team7.objects.Army;
import com.team7.objects.Player;
import com.team7.objects.Tile;

public abstract class Unit {
    private int id;
    private String type;
    private UnitStats unitStats;
    private Tile location;
    private boolean isPowered;
    private Army army;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UnitStats getUnitStats() {
        return unitStats;
    }

    public void setUnitStats(UnitStats unitStats) {
        this.unitStats = unitStats;
    }

    public Tile getLocation() {
        return location;
    }

    public void setLocation(Tile location) {
        this.location = location;
    }

    public boolean isPowered() {
        return isPowered;
    }

    public void setPowered(boolean powered) {
        isPowered = powered;
    }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public Army getArmy() {
        return army;
    }

    public void setArmy(Army army) {
        this.army = army;
    }

}


