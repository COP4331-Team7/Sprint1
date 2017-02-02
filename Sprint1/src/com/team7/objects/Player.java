package com.team7.objects;

import com.team7.objects.structure.Structure;
import com.team7.objects.unit.Unit;
import com.team7.objects.unit.nonCombatUnit.Colonist;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Unit> units;
    private List<Structure> structures;
    private List<Army> armies;
    private int research;
    private int construction;
    private int money;
    private boolean isDefeated;

    public Player() {
        units = new ArrayList<Unit>();                               // max size should be 25
        structures = new ArrayList<Structure>();                     // max size should be 10
        armies = new ArrayList<Army>();                              // max size should be 10
        research = 0;
        construction = 0;
        money = 50;
        isDefeated = false;
    }


    // Unit helper functions

    public List<Unit> getUnits() {
        return units;
    }

    public void setUnits(List<Unit> units) {
        this.units = units;
    }

    // Adds unit to Player's ArrayList of Units
    public Unit addUnit(Unit unit) {

        // Ensures we are able to have a unit
        if(checkMaxUnitsFull() || checkMaxUnitsIndividual()){
            return unit;
        }

        // Physically add the unit
        this.units.add(unit);

        return unit;
    }

    // Removes unit from Player's ArrayList of Units
    public Unit removeUnit(Unit unit) {

        this.units.remove(unit);

        if(this.units.size() == 0){
            this.isDefeated = true;
        }

        return unit;
    }


    // Checks if we have 25 Units
    public boolean checkMaxUnitsFull(){
        if(this.units.size() == 25){
            System.out.println("You have too many units.");
            return true;
        }
        return false;
    }

    // Check if we have 10 units of a certain type
    public boolean checkMaxUnitsIndividual(){
        int explorerCount = 0;
        int colonistCount = 0;
        int meleeCount = 0;
        int rangedCount = 0;

        for(int i = 0; i < this.units.size(); i++){
            Unit unit = this.units.get(i);

            if(unit.getType() == "Colonist"){
                colonistCount++;
            }
            else if(unit.getType() == "Explorer"){
                explorerCount++;
            }
            else if(unit.getType() == "Melee"){
                meleeCount++;
            }
            else if(unit.getType() == "Ranged"){
                rangedCount++;
            }

            if(colonistCount == 10 || explorerCount == 10 || meleeCount == 10 || rangedCount == 10) {
                System.out.println("You have too many units of a particular type.");
                return true;
            }
        }
            return false;
    }


    // Structure helper functions

    public List<Structure> getStructures() {
        return structures;
    }

    public void setStructures(List<Structure> structures) {
        this.structures = structures;
    }


    // Army helper functions

    public List<Army> getArmies() {
        return armies;
    }

    public void setArmies(List<Army> armies) {
        this.armies = armies;
    }



    // Extra getters and setters

    public int getResearch() {
        return research;
    }

    public void setResearch(int research) {
        this.research = research;
    }

    public int getConstruction() {
        return construction;
    }

    public void setConstruction(int construction) {
        this.construction = construction;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public boolean isDefeated() {
        return isDefeated;
    }

    public void setDefeated(boolean defeated) {
        isDefeated = defeated;
    }
}
