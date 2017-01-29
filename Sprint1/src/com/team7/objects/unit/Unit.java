package com.team7.objects.unit;

import com.team7.objects.Player;
import com.team7.objects.Tile;

/**
 * Created by anip on 29/01/17.
 */
public abstract class Unit {
    private long id;
    private Player owner;
    private UnitStats unitStats;
    private Tile location;
}
