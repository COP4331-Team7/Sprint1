package com.team7.objects;

import com.team7.objects.unit.Unit;
import com.team7.objects.unit.UnitCommandQueue;

import java.util.ArrayList;


public class Army {
    private long id;
    private ArrayList<Unit> units;
    private UnitCommandQueue commandQueue;
    private int slowestSpeed; // Moves with speed of slowest unit
    private Tile rallyPoint;
    private int direction;
    private boolean isPowered;
    private int turnsFrozen;

}
