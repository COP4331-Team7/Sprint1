package com.team7.objects.structure;

import com.team7.objects.Player;
import com.team7.objects.Tile;
import com.team7.objects.unit.Unit;
import com.team7.objects.CommandQueue;

public abstract class Structure {
    private int id;
    private Player owner;
    private Tile location;
    private Unit defender;
    private StructureStats stats;
    private CommandQueue commands;
    private boolean isPowered;

    public Structure() {
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Tile getLocation() {
        return location;
    }

    public void setLocation(Tile location) {
        this.location = location;
    }

    public Unit getDefender() {
        return defender;
    }

    public void setDefender(Unit defender) {
        this.defender = defender;
    }

    public StructureStats getStats() {
        return stats;
    }

    public void setStats(StructureStats stats) {
        this.stats = stats;
    }

    public CommandQueue getCommands() {
        return commands;
    }

    public void setCommands(CommandQueue commands) {
        this.commands = commands;
    }

    public boolean isPowered() {
        return isPowered;
    }

    public void setPowered(boolean powered) {
        isPowered = powered;
    }
}
