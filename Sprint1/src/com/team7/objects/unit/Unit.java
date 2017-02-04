package com.team7.objects.unit;

import com.team7.objects.Army;
import com.team7.objects.Player;
import com.team7.objects.Tile;
import com.team7.objects.areaEffects.AreaEffect;
import com.team7.objects.items.Item;
import com.team7.objects.items.Obstacle;
import com.team7.objects.resource.HieroglyphicBooks;
import com.team7.objects.resource.MoneyBag;
import com.team7.objects.resource.MoonRocks;
import com.team7.objects.resource.Resource;

public abstract class Unit {
    private int id;
    private Player owner;
    private String type;
    private UnitStats unitStats;
    private Tile location;
    private boolean isPowered;
    int movesFrozen;
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

    public void powerUp() {
        this.getUnitStats().setUpkeep(4);
        this.setMovesFrozen(2);
        isPowered = true;
    }

    public void powerDown() {

        this.getUnitStats().setUpkeep(1);
        isPowered = false;
    }

    public void decommission() {
        this.getUnitStats().setHealth(0);
    }


    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public Army getArmy() {
        return army;
    }

    public void setArmy(Army army) {
        this.army = army;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public int getMovesFrozen() {
        return movesFrozen;
    }

    public void setMovesFrozen(int movesFrozen) {
        this.movesFrozen = movesFrozen;
    }
}


