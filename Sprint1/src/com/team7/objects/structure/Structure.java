package com.team7.objects.structure;

import com.team7.objects.Player;
import com.team7.objects.Tile;
import com.team7.objects.unit.Unit;

/**
 * Created by anip on 29/01/17.
 */
public abstract class Structure {
    private long id;
    private Player owner;
    private Tile location;
    private Unit defender;
    private StructureStats stats;
    private StructureCommadQueue commands;
    private boolean isPowered;

}
