package com.team7.objects.unit.nonCombatUnit;

import com.team7.ProbabilityGenerator;
import com.team7.objects.Player;
import com.team7.objects.unit.UnitStats;
import com.team7.objects.Tile;

public class Explorer extends NonCombatUnit {

    private static int ids = 1;

    public Explorer(Tile startTile, Player player){
        UnitStats stats = new UnitStats(0, 0, 10, 100, 100, 3);
        //int id = ProbabilityGenerator.randomInteger(0, 99999);
        setOwner(player);
        setArmy(null);
        setUnitStats(stats);
        setPowered(true);
        setId( takeId() );
        setLocation(startTile);
        setMovesFrozen(0);
        setType("Explorer");
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
