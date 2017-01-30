package com.team7.objects.terrain;

import com.team7.objects.areaEffects.ElixirShower;
import com.team7.objects.areaEffects.Storm;
import com.team7.objects.areaEffects.VolcanicVent;

import java.util.ArrayList;
import java.util.Arrays;

/*
 * Hills gives a Tile the characteristic of:
 *  passable
 *  slows down movement (Unit/Army) by 3 total tiles
 *  potential ElixirShower, Storm, or VolcanicVent
 */
public class Hills extends Terrain {
	public Hills() {
        setPassable(true);
        setMovementInfluence(-2);
        setAreaEffects(new ArrayList<>
                (Arrays.asList(new ElixirShower(),
                        new Storm(),
                        new VolcanicVent())));
    }
}
