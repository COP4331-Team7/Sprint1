package com.team7.objects;

import com.team7.objects.unit.Unit;
import com.team7.objects.unit.combatUnit.CombatUnit;
import com.team7.objects.unit.combatUnit.MeleeUnit;
import com.team7.objects.unit.combatUnit.RangedUnit;

import java.util.ArrayList;

/*
 * Created in the controller
 */
public class Attacker {

    Map map;

    ArrayList<Unit> selectedUnits;
    ArrayList<Tile> targetTiles;

    int attackDirection;
    int totalMeleeDamage;
    int totalRangedDamage;


    // constructor for single unit
    public Attacker(Map m, Unit unit, int direction){
        this.map = m;
        this.selectedUnits = new ArrayList<Unit>();
        this.selectedUnits.add(unit);
        this.attackDirection = direction;
        this.totalMeleeDamage = calcTotalMeleeDamage();
        this.totalRangedDamage = calcTotalRangedDamage();
        this.targetTiles = calcTargetTiles();
    }

    // constructor for armies
    public Attacker(Map m, ArrayList<Unit> units, int direction){
        this.map = m;
        this.selectedUnits = units;
        this.attackDirection = direction;
        this.totalMeleeDamage = calcTotalMeleeDamage();
        this.totalRangedDamage = calcTotalRangedDamage();
        this.targetTiles = calcTargetTiles();
    }



    // This function handles the attacking. For either the whole army or the individual unit selected.
    // Attacking works like this:
    //     - All of the damage is added up for melee and ranged units. The total damage is
    //       then directed at the tile in the line of fire. If everything (ARMIES, UNITS AND STRUCTURES)
    //       dies on the first tile, the ranged damage will be directed through the rest of the
    //       tiles remaining until there are no units left.

    // FOR UNITS TO REALLY DIE: Player.checkUnitArmyStructs needs to be run on attacked player
    // DOES NOT ACCOUNT FOR DEFENSIVE DAMAGE, an addition to this function or an extra functions must be added

    public void attack() {

        int totalDamage = totalMeleeDamage + totalRangedDamage;

        // Iterate through all tiles in line of fire
        for(int i = 0; i < targetTiles.size(); i++){
            Tile tile = targetTiles.get(i);

            // On the first tile, melee damage is included
            if(i == 0){

                // cycle through all units in the army and subtract damage and armor
                int armySize = tile.getArmies().size();
                for(int j = armySize - 1; j >= 0; j--) {
                    int armyUnitSize = tile.getArmies().get(j).getUnits().size();
                    for(int k = armyUnitSize - 1; k >= 0; k--){

                        // end function if there is no more damage
                        if (totalDamage == 0)
                            return;

                        // get health and armor, check if unit should die or just lose health
                        int health = tile.getArmies().get(j).getUnits().get(k).getUnitStats().getHealth();
                        int armor = tile.getArmies().get(j).getUnits().get(k).getUnitStats().getArmor();

                        // if total damage is greater than health and armor, destroy both and subtract
                        if(totalDamage >= health + armor) {
                            tile.getArmies().get(j).getUnits().get(k).getUnitStats().setArmor(0);
                            tile.getArmies().get(j).getUnits().get(k).getUnitStats().setHealth(0);
                            totalDamage -= health;
                            totalDamage -= armor;
                        }
                        // if total damage is just less than armor, destroy armor and subtract
                        else if(totalDamage < armor){

                            tile.getArmies().get(j).getUnits().get(k).getUnitStats().setArmor(armor - totalDamage);
                            totalDamage = 0;

                        }
                        // if total damage is greater than armor and less than health, handle
                        else {
                            totalDamage -= armor;
                            tile.getArmies().get(j).getUnits().get(k).getUnitStats().setArmor(0);
                            tile.getArmies().get(j).getUnits().get(k).getUnitStats().setArmor(health - totalDamage);
                            totalDamage = 0;
                        }

                    }
                }


                // handle all units the same way
                int unitSize = tile.getUnits().size();
                for(int j = unitSize - 1; j >= 0; j--) {

                    // end function if there is no more damage
                    if (totalDamage == 0)
                        return;

                    // get health and armor, check if unit should die or just lose health
                    int health = tile.getUnits().get(j).getUnitStats().getHealth();
                    int armor = tile.getUnits().get(j).getUnitStats().getArmor();

                    // if total damage is greater than health and armor, destroy both and subtract
                    if(totalDamage >= health + armor) {
                        tile.getUnits().get(j).getUnitStats().setArmor(0);
                        tile.getUnits().get(j).getUnitStats().setHealth(0);
                        totalDamage -= health;
                        totalDamage -= armor;
                    }
                    // if total damage is just less than armor, destroy armor and subtract
                    else if(totalDamage < armor){

                        tile.getUnits().get(j).getUnitStats().setArmor(armor - totalDamage);
                        totalDamage = 0;

                    }
                    // if total damage is greater than armor and less than health, handle
                    else {
                        totalDamage -= armor;
                        tile.getUnits().get(j).getUnitStats().setArmor(0);
                        tile.getUnits().get(j).getUnitStats().setArmor(health - totalDamage);
                        totalDamage = 0;
                    }

                }




                // Handle structures
                if (totalDamage == 0)
                    return;

                int health = tile.getStructure().getStats().getHealth();

                if(totalDamage >= health) {
                    tile.getStructure().getStats().setHealth(0);
                    totalDamage -= health;
                } else {
                    tile.getStructure().getStats().setHealth(health - totalDamage);
                    totalDamage = 0;
                }

                // if some ranged damage is used, account for it
                if(totalDamage < totalRangedDamage){
                    totalRangedDamage = totalDamage;
                }

            }
            else {

                // cycle through all units in the army and subtract damage and armor
                int armySize = tile.getArmies().size();
                for(int j = armySize - 1; j >= 0; j--) {
                    int armyUnitSize = tile.getArmies().get(j).getUnits().size();
                    for(int k = armyUnitSize - 1; k >= 0; k--){

                        // end function if there is no more damage
                        if (totalRangedDamage == 0)
                            return;

                        // get health and armor, check if unit should die or just lose health
                        int health = tile.getArmies().get(j).getUnits().get(k).getUnitStats().getHealth();
                        int armor = tile.getArmies().get(j).getUnits().get(k).getUnitStats().getArmor();

                        // if total damage is greater than health and armor, destroy both and subtract
                        if(totalRangedDamage >= health + armor) {
                            tile.getArmies().get(j).getUnits().get(k).getUnitStats().setArmor(0);
                            tile.getArmies().get(j).getUnits().get(k).getUnitStats().setHealth(0);
                            totalRangedDamage -= health;
                            totalRangedDamage -= armor;
                        }
                        // if total damage is just less than armor, destroy armor and subtract
                        else if(totalRangedDamage < armor){

                            tile.getArmies().get(j).getUnits().get(k).getUnitStats().setArmor(armor - totalRangedDamage);
                            totalRangedDamage = 0;

                        }
                        // if total damage is greater than armor and less than health, handle
                        else {
                            totalRangedDamage -= armor;
                            tile.getArmies().get(j).getUnits().get(k).getUnitStats().setArmor(0);
                            tile.getArmies().get(j).getUnits().get(k).getUnitStats().setArmor(health - totalRangedDamage);
                            totalRangedDamage = 0;
                        }

                    }
                }


                // handle all units the same way
                int unitSize = tile.getUnits().size();
                for(int j = unitSize - 1; j >= 0; j--) {

                    // end function if there is no more damage
                    if (totalRangedDamage == 0)
                        return;

                    // get health and armor, check if unit should die or just lose health
                    int health = tile.getUnits().get(j).getUnitStats().getHealth();
                    int armor = tile.getUnits().get(j).getUnitStats().getArmor();

                    // if total damage is greater than health and armor, destroy both and subtract
                    if(totalRangedDamage >= health + armor) {
                        tile.getUnits().get(j).getUnitStats().setArmor(0);
                        tile.getUnits().get(j).getUnitStats().setHealth(0);
                        totalRangedDamage -= health;
                        totalRangedDamage -= armor;
                    }
                    // if total damage is just less than armor, destroy armor and subtract
                    else if(totalRangedDamage < armor){

                        tile.getUnits().get(j).getUnitStats().setArmor(armor - totalRangedDamage);
                        totalRangedDamage = 0;

                    }
                    // if total damage is greater than armor and less than health, handle
                    else {
                        totalRangedDamage -= armor;
                        tile.getUnits().get(j).getUnitStats().setArmor(0);
                        tile.getUnits().get(j).getUnitStats().setArmor(health - totalRangedDamage);
                        totalRangedDamage = 0;
                    }

                }


                // Handle structures
                if (totalRangedDamage == 0)
                    return;

                int health = tile.getStructure().getStats().getHealth();

                if(totalRangedDamage >= health) {
                    tile.getStructure().getStats().setHealth(0);
                    totalRangedDamage -= health;
                } else {
                    tile.getStructure().getStats().setHealth(health - totalRangedDamage);
                    totalRangedDamage = 0;
                }





            }



        }



    }




    // calculate total melee damage from this tile
    private int calcTotalMeleeDamage() {
        int sum = 0;

        for(int i = 0; i < selectedUnits.size(); i++){
            if(selectedUnits.get(i) instanceof MeleeUnit)
            sum += selectedUnits.get(i).getUnitStats().getOffensiveDamage();
        }

        return sum;
    }

    // calculate total damage from this tile
    private int calcTotalRangedDamage() {
        int sum = 0;

        for(int i = 0; i < selectedUnits.size(); i++){
            if(selectedUnits.get(i) instanceof RangedUnit)
                sum += selectedUnits.get(i).getUnitStats().getOffensiveDamage();
        }

        return sum;
    }


    // This method returns the list of tiles able to be attacked. Public for testing
    // Returns one tile if only melee, 5 tiles in that direction if ranged
    public ArrayList<Tile> calcTargetTiles() {
        ArrayList<Tile> targetTiles = new ArrayList<Tile>();

        // check if there is ranged unit
        boolean hasRanged = false;
        for(int i = 0; i < this.selectedUnits.size(); i++){
            if(this.selectedUnits.get(i) instanceof RangedUnit){
                hasRanged = true;
            }
        }

        int x = this.selectedUnits.get(0).getLocation().getxCoordinate();
        int y = this.selectedUnits.get(0).getLocation().getyCoordinate();

        // looping 5 because that is the max range for ranged units (HARDCODED)
        for(int i = 0; i < 5; i++) {
            if(attackDirection == 1) {
                y++;
                x--;
            }
            else if(attackDirection == 2) {
                y++;
            }
            else if(attackDirection == 3) {
                y++;
                x++;
            }
            else if(attackDirection == 4) {
                x--;
            }
            else if(attackDirection == 6) {
                x++;
            }
            else if(attackDirection == 7) {
                y--;
                x--;
            }
            else if(attackDirection == 8) {
                y--;
            }
            else if(attackDirection == 9) {
                y--;
                x++;
            } else {
                System.out.println("Invalid attack direction");
                break;
            }

            if(isInBounds(x, y)){
                targetTiles.add(map.getTile(x, y));
            }

            if(!hasRanged){
                break;
            }
        }


        return targetTiles;
    }

    // check if x and y are in bounds
    private boolean isInBounds(int x, int y) {
        if (x <= 19 && x >= 0 && y <= 19 && y >= 0) {
            return true;
        }
        return false;
    }



}
