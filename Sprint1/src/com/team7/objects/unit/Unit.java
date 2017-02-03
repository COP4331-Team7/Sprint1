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

        updateUnitOnTileChange(location);
    }

    private void updateUnitOnTileChange(Tile location) {

        //AreaEffect
        if (location.getAreaEffect() != null){
            int currentUnitElixir = this.unitStats.getHealth();
            int updatedUnitElixir = currentUnitElixir + location.getAreaEffect().getHealthEffect();

            if (location.getAreaEffect().isInstantDeath()){ //instant death is equivalent to health of 0
                this.unitStats.setHealth(0);
            }else{ //not instant death, update health level
                if (updatedUnitElixir > 100){
                    updatedUnitElixir = 100; //health maximum of 100
                }
                this.unitStats.setHealth(updatedUnitElixir);    //update UnitStat
            }
        }

        //Resource
        if (location.getResource() != null){
            int updatedPlayerResourceLevel = location.getResource().getStatInfluence();

            if (location.getResource() instanceof MoneyBag){
                //increase Money stat
                updatedPlayerResourceLevel += owner.getMoney();
                if (updatedPlayerResourceLevel > 100){
                   updatedPlayerResourceLevel = 100; //Money maximum of 100
                }
                this.owner.setMoney(updatedPlayerResourceLevel);
            }
            if (location.getResource() instanceof HieroglyphicBooks){
                //increase Research stat
                updatedPlayerResourceLevel += owner.getResearch();
                if (updatedPlayerResourceLevel > 100){
                    updatedPlayerResourceLevel = 100; //Research maximum of 100
                }
                this.owner.setResearch(updatedPlayerResourceLevel);
            }
            if (location.getResource() instanceof MoonRocks){
                //increase Construction stat
                updatedPlayerResourceLevel += owner.getConstruction();
                if (updatedPlayerResourceLevel > 100){
                    updatedPlayerResourceLevel = 100;   //Construction maximum of 100
                }
                this.owner.setConstruction(updatedPlayerResourceLevel);
            }
        }

        //Item
        if (location.getItem() != null){
            if(location.getItem() instanceof Obstacle){
                //impassable!
                //indicate that Model cannot change, Tile cannot be accessed
            } else{ //item is a OneShotItem
                if(!location.getItem().isInactive()){ //OneShotItem is still in play
                    location.getItem().setInactive(true); //disable item
                    int updatedMoneyLevel = owner.getMoney() + location.getItem().getStatInfluence();
                    if (updatedMoneyLevel > 100){
                        updatedMoneyLevel = 100;
                    }
                    this.owner.setMoney(updatedMoneyLevel); //first iter: all OneShotItems increase the Money stat
                }
            }
        }
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

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
}


