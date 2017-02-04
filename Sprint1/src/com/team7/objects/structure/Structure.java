package com.team7.objects.structure;

import com.team7.objects.Player;
import com.team7.objects.Tile;
import com.team7.objects.unit.Unit;
import com.team7.objects.CommandQueue;

public abstract class Structure {
    private int id;
    private Player owner;
    private Tile location;
    private StructureStats stats;
    private CommandQueue commandQueue;
    private boolean isPowered;


    public Tile getLocation() {
        return location;
    }

    public void setLocation(Tile location) {
        this.location = location;
    }

    public StructureStats getStats() {
        return stats;
    }

    public void setStats(StructureStats stats) {
        this.stats = stats;
    }

    public CommandQueue getCommandQueue() {
        return commandQueue;
    }

    public void setCommandQueue(CommandQueue commandQueue) {
        this.commandQueue = commandQueue;
    }

    public boolean isPowered() {
        return isPowered;
    }

    public void setPowered(boolean powered) {
        isPowered = powered;
    }

    public void powerUp() {

        // TODO: fill out what happens to structure when power up happens (frozen?)
        isPowered = true;
    }

    public void powerDown() {
        // TODO: fill out what happens to structure when power down happens (frozen?)
        isPowered = false;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
}
