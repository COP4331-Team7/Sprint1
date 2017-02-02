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

    public int getOffensiveDamage() {
        return offensiveDamage;
    }

    public void setOffensiveDamage(int offensiveDamage) {
        this.offensiveDamage = offensiveDamage;
    }

    public int getDefensiveDamage() {
        return defensiveDamage;
    }

    public void setDefensiveDamage(int defensiveDamage) {
        this.defensiveDamage = defensiveDamage;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public HashMap<String, Integer> getProductionRates() {
        return productionRates;
    }

    public void setProductionRates(HashMap<String, Integer> productionRates) {
        this.productionRates = productionRates;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getUpkeep() {
        return upkeep;
    }

    public void setUpkeep(int upkeep) {
        this.upkeep = upkeep;
    }
}
