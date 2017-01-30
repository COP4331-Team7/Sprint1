package com.team7.objects.unit;

import com.team7.objects.Player;
import com.team7.objects.Tile;

public abstract class Unit {
    private long id;
    private Player owner;
    private UnitStats unitStats;
    private Tile location;
}
