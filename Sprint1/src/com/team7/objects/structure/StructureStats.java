package com.team7.objects.structure;

import com.team7.objects.Map;
import com.team7.objects.unit.Unit;

import java.util.HashMap;

public class StructureStats {
    private int offensiveDamage;
    private int defensiveDamage;
    private int armor;
    private HashMap<String,Integer> productionRates; // No. of turns required to produce a specific unit
    private int health;
    private int upkeep;

    public StructureStats(int offensiveDamage, int defensiveDamage, int armor, HashMap<String, Integer> productionRates, int health, int upkeep) {
        this.offensiveDamage = offensiveDamage;
        this.defensiveDamage = defensiveDamage;
        this.armor = armor;
        this.productionRates = productionRates;
        this.health = health;
        this.upkeep = upkeep;
    }
}
