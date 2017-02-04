package com.team7.objects;

import com.team7.objects.unit.Unit;
import com.team7.objects.unit.combatUnit.CombatUnit;
import com.team7.objects.unit.combatUnit.RangedUnit;

import java.util.ArrayList;

/*
 * Also created in the controller
 */
public class Attacker {

    Map map;

    ArrayList<Unit> selectedUnits;
    ArrayList<Tile> targetTiles;

    int attackDirection;
    int totalDamage;
    int totalDefense;


    // constructor for single unit
    public Attacker(Map m, Unit unit, int direction){
        this.map = m;
        this.selectedUnits = new ArrayList<Unit>();
        this.selectedUnits.add(unit);
        this.attackDirection = direction;
        this.totalDamage = calcTotalDamage();
        this.totalDefense = calcTotalDefense();
        this.targetTiles = calcTargetTiles();
    }

    // constructor for armies
    public Attacker(Map m, ArrayList<Unit> units, int direction){
        this.map = m;
        this.selectedUnits = units;
        this.attackDirection = direction;
        this.totalDamage = calcTotalDamage();
        this.totalDefense = calcTotalDefense();
        this.targetTiles = calcTargetTiles();
    }



    private int calcTotalDamage() {
        int sum = 0;

        for(int i = 0; i < selectedUnits.size(); i++){
            sum += selectedUnits.get(i).getUnitStats().getOffensiveDamage();
        }

        return sum;
    }

    private int calcTotalDefense() {
        int sum = 0;

        for(int i = 0; i < selectedUnits.size(); i++){
            sum += selectedUnits.get(i).getUnitStats().getDefensiveDamage();
        }

        return sum;
    }


    private ArrayList<Tile> calcTargetTiles() {
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


    private boolean isInBounds(int x, int y) {
        if (x <= 19 && x >= 0 && y <= 19 && y >= 0) {
            return true;
        }
        return false;
    }



}
