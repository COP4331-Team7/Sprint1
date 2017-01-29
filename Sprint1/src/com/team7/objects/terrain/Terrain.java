package com.team7.objects.terrain;

import com.team7.objects.areaEffects.AreaEffect;

import java.util.ArrayList;

/**
 * Created by anip on 29/01/17.
 */
public abstract class Terrain {
    private long id;
    private boolean isPassable;
    private int movementInfluence;
    private ArrayList<AreaEffect> areaEffects;
}
