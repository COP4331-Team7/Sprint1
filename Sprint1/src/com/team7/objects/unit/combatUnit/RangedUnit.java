package com.team7.objects.unit.combatUnit;

import com.team7.ProbabilityGenerator;
import com.team7.objects.Army;
import com.team7.objects.Player;
import com.team7.objects.Tile;
import com.team7.objects.unit.UnitStats;

public class RangedUnit extends CombatUnit {

    private static int ids = 1;

    public RangedUnit(Tile startTile, Player player){
        UnitStats stats = new UnitStats(12, 5, 10, 6, 100, 4);
     //   int id = ProbabilityGenerator.randomInteger(0, 99999);
        setOwner(player);
        setArmy(null);
        setUnitStats(stats);
        setPowered(true);
        setId(takeId());
        setLocation(startTile);
        setType("Ranged");
        setMovesFrozen(0);
        setDefenseDirection(0);
    }

    public int takeId() {
        for(int i = 0; i < 10; i++) {
            if( ((ids >> i) & 1) == 0 ) {
                returnId(i);
                return  i;
            }
        }
        return  -1;
    }
    public void returnId(int id) {
        ids = (ids ^ (1 << id) );
    }



}
