package com.team7.objects.terrain;

import com.team7.objects.areaEffects.AreaEffect;

import java.util.ArrayList;

public abstract class Terrain {
    private long id;
    private boolean isPassable;
    private int movementInfluence;
    private ArrayList<AreaEffect> areaEffects;
}
