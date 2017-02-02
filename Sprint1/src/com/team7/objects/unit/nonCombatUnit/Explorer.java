package com.team7.objects.unit.nonCombatUnit;

import com.team7.ProbabilityGenerator;
import com.team7.objects.unit.UnitStats;
import com.team7.objects.Tile;

public class Explorer extends NonCombatUnit {

    public Explorer(Tile startTile){
        UnitStats stats = new UnitStats(0, 0, 0, 10, 100, 1);
        int id = ProbabilityGenerator.randomInteger(0, 99999);

        setArmy(null);
        setUnitStats(stats);
        setPowered(true);
        setId(id);
        setLocation(startTile);
        setType("Explorer");
    }

}
