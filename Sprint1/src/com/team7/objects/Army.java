package com.team7.objects;

import com.team7.ProbabilityGenerator;
import com.team7.objects.unit.Unit;
import com.team7.objects.CommandQueue;

import java.util.ArrayList;
import java.util.StringJoiner;


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
    private String name; 



    public Army(Tile startTile, Player player){
        int id = ProbabilityGenerator.randomInteger(0, 99999);
        this.units = new ArrayList<Unit>();
        this.commands = new CommandQueue();
        this.owner = player;
        this.slowestSpeed = 100;
        this.rallyPoint = startTile;
        this.direction = 0;
        this.isPowered = true;
        this.turnsFrozen = 0;
        this.name = "Army " + id; 
    }

    // Adds unit to Army's ArrayList of Units
    public void addUnitToArmy(Unit unit) {
        if(unit.getType() != "Colonist" && unit.getType() != "Explorer") {
            // Physically add the unit
            this.units.add(unit);
            unit.setArmy(this);

            // Check for new slowest speed
            if(unit.getUnitStats().getMovement() < this.slowestSpeed){
                this.slowestSpeed = unit.getUnitStats().getMovement();
            }
        }
    }

    // Removes unit from Army's ArrayList of Units
    public void removeUnitFromArmy(Unit unit) {

        this.units.remove(unit);
        unit.setArmy(null);
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

    public void powerUp() {

        // TODO: fill out what happens to units when power up happens (frozen?)
        isPowered = true;
    }

    public void powerDown() {
        // TODO: fill out what happens to units when power down happens (frozen?)
        isPowered = false;
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
    public String getName() {
        return name;
    }

    // function processQueue will take in a string, and check for validity,
    // decode to call helper function for attack, defend etc..

}

