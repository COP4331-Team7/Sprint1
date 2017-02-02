package com.team7.objects.unit.nonCombatUnit;

import com.team7.ProbabilityGenerator;
import com.team7.objects.Player;
import com.team7.objects.structure.Base;
import com.team7.objects.structure.Structure;
import com.team7.objects.unit.UnitStats;
import com.team7.objects.Tile;

public class Colonist extends NonCombatUnit {

	public Colonist(Tile startTile, Player player){
	    UnitStats stats = new UnitStats(0, 0, 0, 5, 100, 1);
        int id = ProbabilityGenerator.randomInteger(0, 99999);
		setOwner(player);
		setArmy(null);
        setUnitStats(stats);
        setPowered(true);
	    setId(id);
	    setLocation(startTile);
	    setType("Colonist");
    }

    // Build a base on the tile, give it to the player and sacrifice colonist
	public void buildBase(){

		// build base on tile and give it to player
		Structure base = new Base(this.getLocation(), this.getOwner());
		this.getOwner().addStructure(base);
		this.getLocation().setStructure(base);


		// sacrifice colonist from tile and player
		this.getLocation().removeUnitFromTile(this);
		this.getOwner().removeUnit(this);
	}

}
