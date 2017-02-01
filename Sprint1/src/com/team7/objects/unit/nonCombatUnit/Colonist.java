package com.team7.objects.unit.nonCombatUnit;

import com.team7.ProbabilityGenerator;
import com.team7.objects.unit.UnitStats;
import com.team7.objects.Tile;

public class Colonist extends NonCombatUnit {

	public Colonist(Tile startTile){
	    UnitStats stats = new UnitStats(0, 0, 0, 5, 100, 1);
        int id = ProbabilityGenerator.randomInteger(0, 99999);

        setUnitStats(stats);
        setPowered(true);
	    setId(id);
	    setLocation(startTile);
	    setType("Colonist");
    }

}
