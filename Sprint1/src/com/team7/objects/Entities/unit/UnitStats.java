package com.team7.objects.unit;

import java.util.HashMap;

public class UnitStats {

    private final int unitType;
    private final int offensiveDamage;
    private final int defensiveDamage;
    private final int armor;
    private int movement;
    private int health;
    private int upkeep;

    public UnitStats(int unitType) {
        // Initalize stats appropriately
        // i.e. by there respective unitType
        // In my opnion best way to add new types
        // and minimize bugs
        switch(unitType) {
            // initialize stats for explorer
            case 1: this.untiType = unitType;
                offensiveDamage = 15;
                defensiveDamage = 15;
                armor = 30;
                movement = 70;
                health = 100;
                upkeep = ////////////;
                break;
            // initialize stats for colonist
            case 2: this.untiType = unitType;
                offensiveDamage = 10;
                defensiveDamage = 10;
                armor = 20;
                movement = 25;
                health = 100;
                upkeep = u;
                break;
            // initialize stats for melee unit
            case 3: this.untiType = unitType;
                offensiveDamage = 57;
                defensiveDamage = 60;
                armor = 40;
                movement = 40;
                health = 100;
                upkeep = //;
                break;
            // initialize stats for long range unit
            case 4: this.untiType = unitType;
                offensiveDamage = 30;
                defensiveDamage = 70;
                armor = 30;
                movement = 25;
                health = 100;
                upkeep = //;
                break;
            default: System.out.println("Invalid unitType");
        }
    }

    public int[] getStats() {
        stats[] =new int[6];
        stats[0] = offensiveDamage;
        stats[1] = defensiveDamage;
        stats[2] = armor;
        stats[3] = movement;
        stats[4] = health;
        stats[5] = upkeep;
        return stats;
    }

    public void setHealth(int offsetHealth) {
        if(offsetFactor == -111)// factor to reset health of a unit.
        {
            switch(unitType){
                case 1: health = h; // set colonist default health
                    break;
                case 2: health = h; // set explorer default health
                    break;
                case 3: health = h; // set meleeU default health
                    break;
                case 4: health = h; // set rangedU default health
                    break;
            }
        }
        else
        {// set back to default
            health = health+damage;
        }
    }

    public void setMovement(int OffsetFactor) {
        if(factor == -111)
        {
            movement = m; // default
        }
        else
        {
            movement = movement+factor;
        }
    }

    public void setUpkeepState(int upkeepState) {
        if(upkeepState == 1)
        {// part of army but not attacking/defending. "normal"
            upkeep = u;
        }
        else if(upkeepState == 2)
        {// part of army attacking/defending
            upkeep = u*1.25;
        }
        else if(upkeepState == 3)
        {// unit in standby mode
            upkeep = u*.75;
        }
    }
}