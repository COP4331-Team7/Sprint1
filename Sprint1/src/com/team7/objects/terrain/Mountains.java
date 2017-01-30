package com.team7.objects.terrain;


import com.team7.objects.areaEffects.ElixirShower;
import com.team7.objects.areaEffects.Storm;

import java.util.ArrayList;
import java.util.Arrays;

/*
 * Mountains gives a Tile the characteristic of:
 *  impassable (by starting Units)
 *  no movement effect (movement is not possible)
 *  potential ElixirShower or Storm
 */
public class Mountains extends Terrain {
	public Mountains() {
        setPassable(false);
        setMovementInfluence(0);
        setAreaEffects(new ArrayList<>
                (Arrays.asList(new ElixirShower(),
                        new Storm())));
    }
}
