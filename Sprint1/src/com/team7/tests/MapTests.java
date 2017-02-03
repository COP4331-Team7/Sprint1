package com.team7.tests;


import com.team7.objects.Map;
import com.team7.objects.Player;
import com.team7.objects.Tile;
import com.team7.objects.areaEffects.Storm;
import com.team7.objects.terrain.Mountains;
import com.team7.objects.unit.Unit;
import com.team7.objects.unit.nonCombatUnit.Colonist;
import org.testng.annotations.Test;


import static org.junit.jupiter.api.Assertions.*;
public class MapTests {

    @Test
    public void getTileTest() throws Exception {
        Map m = new Map();
        Tile t = new Tile();
        t = m.getTile(0,0);

        assertTrue(t.getTerrain() instanceof Mountains);
    }



    @Test
    // Test creating units and making sure they exist on the tiles
    public void checkUnitExists() throws Exception {
        Map m = new Map();
        Player p1 = new Player();

        Unit colonist = new Colonist(m.getGrid()[0][0], p1);

        p1.addUnit(colonist);

        Unit testUnit = m.getGrid()[0][0].getUnits().get(0);

        assertTrue(testUnit.getType() ==  "Colonist");
        assertTrue(testUnit == colonist);

    }



    @Test
    // Test area effects affecting unit
    public void testAreaEffects() throws Exception {
        Map map = new Map();
        Player p1 = new Player();

        // Create a storm on tile and place a colonist there
        map.getGrid()[0][0].setAreaEffect(new Storm());
        Unit unit = new Colonist(map.getGrid()[0][0], p1);

        // helper function to add area effects
        map.getGrid()[0][0].handleAreaEffects(unit);

        assertTrue(unit.getUnitStats().getHealth() < 100);

    }



}
