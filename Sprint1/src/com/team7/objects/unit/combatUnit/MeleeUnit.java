package com.team7.objects.unit.combatUnit;

import com.team7.ProbabilityGenerator;
import com.team7.objects.Player;
import com.team7.objects.Tile;
import com.team7.objects.unit.UnitStats;
import com.team7.objects.Army;


public class MeleeUnit extends CombatUnit{

    public MeleeUnit(Tile startTile, Player player){
        UnitStats stats = new UnitStats(20, 7, 10, 8, 100, 4);
        int id = ProbabilityGenerator.randomInteger(0, 99999);
        setOwner(player);
        setArmy(null);
        setUnitStats(stats);
        setPowered(true);
        setId(id);
        setLocation(startTile);
        setType("Melee");
        setMovesFrozen(0);
        setAttackDirection(0);
        setDefenseDirection(0);
    }



}
