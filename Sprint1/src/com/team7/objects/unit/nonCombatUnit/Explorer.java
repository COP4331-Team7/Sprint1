package com.team7.objects.unit.nonCombatUnit;

import com.team7.objects.unit.UnitStats;
import com.team7.objects.Tile;

public class Explorer extends NonCombatUnit {

    public Explorer(int id, Tile startTile){
        UnitStats stats = new UnitStats(0, 0, 0, 10, 100, 1);
        setUnitStats(stats);
        setPowered(true);
        setId(id);
        setLocation(startTile);
    }

}
