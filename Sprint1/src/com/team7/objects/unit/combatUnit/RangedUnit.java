package com.team7.objects.unit.combatUnit;

import com.team7.ProbabilityGenerator;
import com.team7.objects.Army;
import com.team7.objects.Player;
import com.team7.objects.Tile;
import com.team7.objects.unit.UnitStats;

public class RangedUnit extends CombatUnit {

    public RangedUnit(Tile startTile, Player player){
        UnitStats stats = new UnitStats(10, 5, 10, 8, 100, 1);
        int id = ProbabilityGenerator.randomInteger(0, 99999);
        setOwner(player);
        setArmy(null);
        setUnitStats(stats);
        setPowered(true);
        setId(id);
        setLocation(startTile);
        setType("Ranged");
    }

}
