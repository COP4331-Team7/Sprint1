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
    private int defenseDirection;
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
        this.defenseDirection = 0;
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


    public boolean isPowered() {
        return isPowered;
    }

    public void powerUp() {


        for(int i = 0; i < units.size(); i++) {
            this.units.get(i).getUnitStats().setUpkeep(4);
            this.units.get(i).setMovesFrozen(2);
        }

        isPowered = true;
    }

    public void powerDown() {

        for(int i = 0; i < units.size(); i++) {
            this.units.get(i).getUnitStats().setUpkeep(1);
        }

        isPowered = false;
    }

    public void decommission() {
        for(int i = 0; i < this.units.size(); i++){
            this.units.get(i).decommission();
        }
    }

    public void disband() {
        for(int i = 0; i < this.units.size(); i++){
            removeUnitFromArmy(this.units.get(i));
        }
        this.owner.removeArmy(this);
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

    public int getDefenseDirection() {
        return defenseDirection;
    }

    public void setDefenseDirection(int defenseDirection) {
        this.defenseDirection = defenseDirection;
    }

    public void setPowered(boolean powered) {
        isPowered = powered;
    }

    public void setName(String name) {
        this.name = name;
    }

    // function processQueue will take in a string, and check for validity,
    // decode to call helper function for attack, defend etc..

}

