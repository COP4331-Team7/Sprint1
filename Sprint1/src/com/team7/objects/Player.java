package com.team7.objects;

import com.team7.objects.structure.Structure;
import com.team7.objects.unit.Unit;
import com.team7.objects.unit.nonCombatUnit.Colonist;

import java.util.ArrayList;


public class Player {
    private ArrayList<Unit> units;
    private ArrayList<Structure> structures;
    private ArrayList<Army> armies;
    private int research;
    private int construction;
    private int money;
    private boolean noUnits;
    private boolean noStructures;
    private boolean noArmies;

    public Player() {
        units = new ArrayList<Unit>();                               // max size should be 25
        structures = new ArrayList<Structure>();                     // max size should be 10
        armies = new ArrayList<Army>();                              // max size should be 10
        research = 0;
        construction = 0;
        money = 500;
        noUnits = true;
        noStructures = true;
        noArmies = true;
    }



    // Big function that controls everything that could happen in a turn
    public void Turn() {
        //get cursor commands from controller
        //parse command
        //keep track of movement state
        ArrayList<Tile> path = new ArrayList<Tile>();
        Tile start = new Tile();
        if("key-pressed"=="right"){}
            //check is it possible
            //then
                //Create a cursor of TIle class
                    //Added this to path ArrayList
        Tile end = new Tile();
        if ("key-pressed" == "Enter") {

             }

        Navigation navigation = new Navigation(path, start, end);

        checkUnitArmyStructs();     // check if any structures/units/armies are dead and remove them
        subtractUpkeep();           // subtracts upkeep from all units/structures, ends game if money = 0
        subtractMovesFrozen();      // subtracts moves frozen from anything that is frozen

    }





    // Unit helper functions

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public void setUnits(ArrayList<Unit> units) {
        this.units = units;
    }

    // Adds unit to Player's ArrayList of Units
    public Unit addUnit(Unit unit) {

        // Ensures we are able to have a unit
        if(checkMaxUnitsFull() || checkMaxUnitsIndividual()){
            return unit;
        }

        // Physically add the unit to player and put it on the map
        this.noUnits = false;
        this.units.add(unit);
        unit.getLocation().addUnitToTile(unit);

        return unit;
    }

    // Removes unit from Player's ArrayList of Units
    public Unit removeUnit(Unit unit) {

        // Physically remove unit form player and tile
        // Put skull  on tile because unit died?
        this.units.remove(unit);
        unit.getLocation().removeUnitFromTile(unit);
        unit.getLocation().setDecal(new Decal("Skull"));

        if(this.units.size() == 0){
            this.noUnits = true;
        }

        return unit;
    }

    public Unit getUnit(Unit unit){
        for(int i = 0; i < units.size(); i++){
            if(units.get(i) == unit){
                return unit;
            }
        }

        return null;
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

    public ArrayList<Structure> getStructures() {
        return structures;
    }

    public void setStructures(ArrayList<Structure> structures) {
        this.structures = structures;
    }

    // Adds structure to Player's ArrayList of Structures
    public Structure addStructure(Structure structure) {

        // Ensures we are able to have a unit
        if(this.structures.size() == 10){
            System.out.println("You have too many units.");
            return structure;
        }

        // Physically add the unit and put it on the map
        this.noStructures = false;
        this.structures.add(structure);
        structure.getLocation().setStructure(structure);

        return structure;
    }

    // Removes unit from Player's ArrayList of Units
    public Structure removeStructure(Structure structure) {

        // Physically remove unit form player and tile
        this.structures.remove(structure);
        structure.getLocation().setStructure(null);

        if(this.structures.size() == 0){
            this.noStructures = true;
        }

        return structure;
    }



    // Army helper functions

    public ArrayList<Army> getArmies() {
        return armies;
    }

    public void setArmies(ArrayList<Army> armies) {
        this.armies = armies;
    }


    // Adds army to Player's ArrayList of armies
    public Army addArmy(Army army) {

        // Ensures we are able to have a unit
        if(this.armies.size() == 10){
            System.out.println("You have too many units.");
            return army;
        }

        // Physically add the unit and put it on the map
        this.noArmies = false;
        this.armies.add(army);
        army.getRallyPoint().addArmyToTile(army);

        return army;
    }


    // Removes army to Player's ArrayList of armies
    public Army removeArmy(Army army) {

       // physically remove the army
        this.armies.remove(army);
        army.getRallyPoint().removeArmyFromTile(army);

        if(this.armies.size() == 0){
            this.noArmies = true;
        }

        return army;
    }

    // run this function at the end of each turn to see if there are any dead structures
    // units or armies that need to be removed from the array lists
    public void checkUnitArmyStructs(){

        // check if any units are dead, if so remove from list
        int unitSize = this.units.size();
        for(int i = unitSize - 1; i >= 0; i--) {
            if(this.units.get(i).getUnitStats().getHealth() <= 0) {
                removeUnit(this.units.get(i));
            }
        }

        int armySize = this.armies.size();
        // check if any army units are dead, if so remove them
        // then check if army is empty, if so, remove it
        for(int i = armySize - 1; i >= 0; i--) {
            int armyUnitSize = this.armies.get(i).getUnits().size();
            for(int j = armyUnitSize - 1; j >= 0; j--){
                // if any unit in the army is dead, remove it from the army
                if(this.armies.get(i).getUnits().get(j).getUnitStats().getHealth() <= 0) {
                    removeUnit(this.armies.get(i).getUnits().get(j));
                    this.armies.get(i).removeUnitFromArmy(this.armies.get(i).getUnits().get(j));
                }
            }
            if(this.armies.get(i).getUnits().size() == 0){
                removeArmy(this.armies.get(i));
            }
        }

        int structureSize = this.structures.size();
        // check for any dead structures
        for(int i = structureSize - 1; i >= 0; i--) {
            if(this.structures.get(i).getStats().getHealth() <= 0) {
                removeStructure(this.structures.get(i));
            }
        }


    }

    private void subtractUpkeep() {

        int sum = 0;

        // add all unit stats
        for(int i = 0; i < this.units.size(); i++){
            sum += this.units.get(i).getUnitStats().getUpkeep();
        }

        // add all structure stats
        for(int i = 0; i < this.structures.size(); i++){
            sum += this.structures.get(i).getStats().getUpkeep();
        }

        this.setMoney(this.getMoney() - sum);


        // if they run out of money the game is over
        if(this.getMoney() == 0){
            noStructures = true;
            noUnits = true;
            noArmies = true;
        }

    }


    private void subtractMovesFrozen() {

        // subtract one from moves frozen for all frozen units
        for(int i = 0; i < this.units.size(); i++){
            if(this.units.get(i).getMovesFrozen() > 0) {
                this.units.get(i).setMovesFrozen(this.units.get(i).getMovesFrozen() - 1);
            }
        }

        // subtract one from moves frozen for all frozen structures
        for(int i = 0; i < this.structures.size(); i++){
            if(this.structures.get(i).getMovesFrozen() > 0) {
                this.structures.get(i).setMovesFrozen(this.structures.get(i).getMovesFrozen() - 1);
            }
        }

    }

    // This function will loop through all armies and structures and execute next command in queue
    private void updateQueues() {

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
        return noArmies && noUnits && noStructures;
    }



    public boolean isNoUnits() {
        return noUnits;
    }

    public boolean isNoStructures() {
        return noStructures;
    }

    public boolean isNoArmies() {
        return noArmies;
    }
}
