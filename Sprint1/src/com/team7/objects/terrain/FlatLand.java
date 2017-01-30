package com.team7.objects.terrain;

import com.team7.objects.areaEffects.ElixirShower;
import com.team7.objects.areaEffects.Storm;

import java.util.ArrayList;
import java.util.Arrays;

/*
 * FlatLand gives a Tile the characteristic of:
 *  passable
 *  no movement effect
 *  potential ElixirShower or Storm
 */
public class FlatLand extends Terrain {
    public FlatLand() {
        setPassable(true);
        setMovementInfluence(0);
        setAreaEffects(new ArrayList<>
                (Arrays.asList(new ElixirShower(),
                        new Storm())));
    }
	
}
