package com.team7.objects.structure;

import com.team7.objects.Map;
import com.team7.objects.unit.Unit;

import java.util.HashMap;

public class StructureStats {
    private long id;
    private int offensiveDamage;
    private int defensiveDamage;
    private int armor;
    private HashMap<Unit,Integer> productionRates; // No. of turns required to produce a specific unit
    private int health;
    private int upkeep;
}
