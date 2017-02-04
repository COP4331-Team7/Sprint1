package com.team7.objects.unit.nonCombatUnit;

import com.team7.ProbabilityGenerator;
import com.team7.objects.Player;
import com.team7.objects.unit.UnitStats;
import com.team7.objects.Tile;

public class Explorer extends NonCombatUnit {

    public Explorer(Tile startTile, Player player){
        UnitStats stats = new UnitStats(0, 0, 10, 10, 100, 1);
        int id = ProbabilityGenerator.randomInteger(0, 99999);
        setOwner(player);
        setArmy(null);
        setUnitStats(stats);
        setPowered(true);
        setId(id);
        setLocation(startTile);
        setType("Explorer");
    }

}
