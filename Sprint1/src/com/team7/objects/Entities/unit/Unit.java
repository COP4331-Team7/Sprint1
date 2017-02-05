package com.team7.objects.unit;


// need list to return list of stats
import com.team7.objects.Player;
import com.team7.objects.Tile;

public abstract class Unit implements Entity {
    private String id = "U";
    private UnitStats stats;
    private Player owner;
    private Tile location;
    private int state;

    public string getId() {
        return id;
    }

    public int[] getAllCurrentStats() {
        return stats.getStats
    }

    public Tile getLocation(){;} // should be implemented here in unit class

    void defend(){}
    void attack(){}
    void powerUp(){}
    void powerDown(){}
    void cancelQueueOrder(){}

    public void abstract setId(String inputId);
    public void abstract attack(Direction d);
    public void abstract defend(Direction d);
    public void abstract move(Direction d); // not sure about

     // this needs to return a specific location
    // Tile class should have
    // Maybe a coordinate class??
}

