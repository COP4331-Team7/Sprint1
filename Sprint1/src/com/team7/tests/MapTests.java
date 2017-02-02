package com.team7.tests;


import com.team7.objects.Map;
import com.team7.objects.Tile;
import com.team7.objects.terrain.Mountains;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class MapTests {

    @Test
    public void getTileTest() throws Exception {
        Map m = new Map();
        Tile t = new Tile();
        t = m.getTile(0,0);

        assertTrue(t.getTerrain() instanceof Mountains);
    }
}
