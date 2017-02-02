package com.team7.objects.unit.combatUnit;

import com.team7.ProbabilityGenerator;
import com.team7.objects.Tile;
import com.team7.objects.unit.UnitStats;
import com.team7.objects.Army;


public class MeleeUnit extends CombatUnit{

    public MeleeUnit(Tile startTile){
        UnitStats stats = new UnitStats(15, 7, 10, 8, 100, 1);
        int id = ProbabilityGenerator.randomInteger(0, 99999);

        setArmy(null);
        setUnitStats(stats);
        setPowered(true);
        setId(id);
        setLocation(startTile);
        setType("Melee");
    }
}
