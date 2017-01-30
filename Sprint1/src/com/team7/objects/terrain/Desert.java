package com.team7.objects.terrain;

import com.team7.objects.areaEffects.AreaEffect;
import com.team7.objects.areaEffects.ElixirShower;

import java.util.ArrayList;
import java.util.Arrays;

/*
 * Desert gives a Tile the characteristic of:
 *  passable
 *  slows down movement (Unit/Army) by 2 total tiles
 *  potential ElixirShower
 */
public class Desert extends Terrain {
	public Desert() {
	    setPassable(true);
	    setMovementInfluence(-2);
	    setAreaEffects(new ArrayList<>
                (Arrays.asList(new ElixirShower())));
    }
}
