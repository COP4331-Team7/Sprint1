package com.team7.objects.structure;

import com.team7.objects.Player;
import com.team7.objects.Tile;
import com.team7.objects.unit.Unit;
import com.team7.objects.CommandQueue;

public abstract class Structure implements Entity{
        private String id = "B";
        private baseStats stats;
        private Player owner;
        private Tile location;
        private int state; //

    public string getId() {
        return id;
    }

    public int[] getAllCurrentStats() {
        return stats.getStats();
    }
    public void cancelI

    public Tile getLocation(){;} // should be implemented here in unit class
    Tile getLocation(); // might be an Entity operation
    void setLocation(Tile location);

    void setStats(StructureStats stats)
    void setCommand(CommandQueue commandQueue);
    boolean isPowered();
    void setPowered(boolean powered);
    public void powerUp();
    public void powerDown();
    public int getId();
    public void setId(int id);
    public Player getOwner();
    public void setOwner(Player owner);
}
