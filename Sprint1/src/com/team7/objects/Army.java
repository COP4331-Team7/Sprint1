package com.team7.objects;

import com.team7.ProbabilityGenerator;
import com.team7.objects.unit.Unit;
import com.team7.objects.CommandQueue;

import java.util.ArrayList;


public class Army {
    private long id;
    private Player owner;
    private ArrayList<Unit> units;
    private CommandQueue commands;
    private int slowestSpeed; // Moves with speed of slowest unit
    private Tile rallyPoint;
    private int direction;
    private boolean isPowered;
    private int turnsFrozen;



    public Army(Tile startTile, Player player){
        int id = ProbabilityGenerator.randomInteger(0, 99999);
        this.units = new ArrayList<Unit>();
        this.commands = new CommandQueue();
        this.owner = player;
        this.slowestSpeed = 0;
        this.rallyPoint = startTile;
        this.direction = 0;
        this.isPowered = true;
        this.turnsFrozen = 0;
    }

    // Adds unit to Army's ArrayList of Units
    public Unit addUnitToArmy(Unit unit) {

        // Physically add the unit
        this.units.add(unit);
        unit.setArmy(this);


        return unit;
    }

    // Removes unit from Army's ArrayList of Units
    public Unit removeUnitFromArmy(Unit unit) {

        this.units.remove(unit);
        unit.setArmy(null);

        return unit;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public void setUnits(ArrayList<Unit> units) {
        this.units = units;
    }

    public CommandQueue getCommands() {
        return commands;
    }

    public void setCommands(CommandQueue commands) {
        this.commands = commands;
    }

    public int getSlowestSpeed() {
        return slowestSpeed;
    }

    public void setSlowestSpeed(int slowestSpeed) {
        this.slowestSpeed = slowestSpeed;
    }

    public Tile getRallyPoint() {
        return rallyPoint;
    }

    public void setRallyPoint(Tile rallyPoint) {
        this.rallyPoint = rallyPoint;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public boolean isPowered() {
        return isPowered;
    }

    public void setPowered(boolean powered) {
        isPowered = powered;
    }

    public int getTurnsFrozen() {
        return turnsFrozen;
    }

    public void setTurnsFrozen(int turnsFrozen) {
        this.turnsFrozen = turnsFrozen;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    // function processQueue will take in a string, and check for validity,
    // decode to call helper function for attack, defend etc..

}

