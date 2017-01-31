package com.team7.objects.unit.nonCombatUnit;

import com.team7.objects.unit.UnitStats;
import com.team7.objects.Tile;

public class Colonist extends NonCombatUnit {

	public Colonist(int id, Tile startTile){
        UnitStats stats = new UnitStats(0, 0, 0, 5, 100, 1);
        setPowered(true);
	    setId(id);
	    setLocation(startTile);
    }

}
