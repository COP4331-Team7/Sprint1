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
    int movesFrozen;


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

    public void decommission() {
        this.getStats().setHealth(0);
    }

    public void powerUp() {

        this.getStats().setUpkeep(8);
        this.setMovesFrozen(2);

        isPowered = true;
    }

    public void powerDown() {

        this.getStats().setUpkeep(2);

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


    public int getMovesFrozen() {
        return movesFrozen;
    }

    public void setMovesFrozen(int movesFrozen) {
        this.movesFrozen = movesFrozen;
    }
}
