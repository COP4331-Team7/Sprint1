package com.team7.objects.structure;

import com.team7.objects.Map;
import com.team7.objects.unit.Unit;

import java.util.HashMap;

public class StructureStats {
    private int structureType;
    private int offensiveDamage;
    private int defensiveDamage;
    private int armor;
    private HashMap<String,Integer> productionRates;// special to base
    private int health;
    private int upkeep;

    public StructureStats(int structureType) {

        switch(structureType) {
        // initialize stats for Base
        case 1: this.structureType = structureType;
            offensiveDamage = 15;
            defensiveDamage = 15;
            armor = 30;
            health = 100;
            upkeep = ////////////;
            break;
        default: System.out.println("Invalid unitType");
        break;
    }

    public int[] getStats() {
            stats[] =new int[5];
            stats[0] = offensiveDamage;
            stats[1] = defensiveDamage;
            stats[2] = armor;
            stats[3] = health;
            stats[4] = upkeep;
            return stats;
        }
}
