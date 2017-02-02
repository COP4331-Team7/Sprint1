package com.team7.objects.structure;

import com.team7.objects.CommandQueue;
import com.team7.objects.Tile;

import java.util.HashMap;

public class Base extends Structure {
    public Base(Tile startTile) {

        HashMap<String, Integer> productionRateMap = new HashMap<>(); //holds the number of turns it takes for a Base to create a Unit
        productionRateMap.put("Melee", 1);
        productionRateMap.put("Ranged", 1);
        productionRateMap.put("Colonist", 1);
        productionRateMap.put("Explorer", 1);

        setStats(new StructureStats(0, 0, 0, productionRateMap, 300, 2));
        setLocation(startTile);
        setCommandQueue(new CommandQueue()); //create reference to a CommandQueue
        setPowered(true); //a Base is powered upon creation
    }
}
