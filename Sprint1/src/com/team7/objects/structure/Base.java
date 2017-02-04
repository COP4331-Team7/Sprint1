package com.team7.objects.structure;

import com.team7.objects.CommandQueue;
import com.team7.objects.Player;
import com.team7.objects.Tile;
import com.team7.objects.unit.Unit;
import com.team7.objects.unit.combatUnit.MeleeUnit;
import com.team7.objects.unit.combatUnit.RangedUnit;
import com.team7.objects.unit.nonCombatUnit.Colonist;
import com.team7.objects.unit.nonCombatUnit.Explorer;

import java.util.HashMap;

public class Base extends Structure {
    public Base(Tile startTile, Player player) {

        HashMap<String, Integer> productionRateMap = new HashMap<>(); //holds the number of turns it takes for a Base to create a Unit
        productionRateMap.put("Melee", 2);
        productionRateMap.put("Ranged", 2);
        productionRateMap.put("Colonist", 5);
        productionRateMap.put("Explorer", 1);

        setOwner(player);
        setStats(new StructureStats(0, 0, 0, productionRateMap, 300, 2));
        setLocation(startTile);
        setCommandQueue(new CommandQueue()); //create reference to a CommandQueue
        setPowered(true); //a Base is powered upon creation
    }


    public Unit createUnit(String type) {

        Unit unit = null;

        if(type == "Colonist"){
            unit = new Colonist(this.getLocation(), this.getOwner());
        }
        else if(type == "Explorer"){
            unit = new Explorer(this.getLocation(), this.getOwner());
        }
        else if(type == "Melee"){
            unit = new MeleeUnit(this.getLocation(), this.getOwner());
        }
        else if(type == "Ranged"){
            unit = new RangedUnit(this.getLocation(), this.getOwner());
        } else {
            System.out.println("Not a valid type");
        }

        // add unit to tile and to player's array and take away money
        this.getLocation().addUnitToTile(unit);
        this.getOwner().addUnit(unit);
        this.getOwner().setMoney(this.getOwner().getMoney() - unit.getUnitStats().getUpkeep());

        return unit;
    }

}
