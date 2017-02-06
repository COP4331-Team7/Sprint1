package com.team7.objects.unit.combatUnit;

import com.team7.ProbabilityGenerator;
import com.team7.objects.Player;
import com.team7.objects.Tile;
import com.team7.objects.unit.UnitStats;
import com.team7.objects.Army;


public class MeleeUnit extends CombatUnit{
    private static int ids = 1;

    public MeleeUnit(Tile startTile, Player player){
        UnitStats stats = new UnitStats(20, 7, 10, 8, 100, 4);
     //   int id = ProbabilityGenerator.randomInteger(0, 99999);
        setOwner(player);
        setArmy(null);
        setUnitStats(stats);
        setPowered(true);
        setId(takeId());
        setLocation(startTile);
        setType("Melee");
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
