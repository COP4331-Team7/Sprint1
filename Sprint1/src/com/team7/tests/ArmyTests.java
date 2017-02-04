package com.team7.tests;
import com.team7.objects.Army;
import com.team7.objects.Map;
import com.team7.objects.Player;
import com.team7.objects.Tile;
import com.team7.objects.unit.Unit;
import com.team7.objects.unit.combatUnit.MeleeUnit;
import com.team7.objects.unit.nonCombatUnit.Colonist;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArmyTests {

    @Test
    public void testAddUnit() throws Exception{
        Tile t = new Tile();
        Player p = new Player();
        Army a = new Army(t, p);
        MeleeUnit u = new MeleeUnit(t, p);
        a.addUnitToArmy(u);

        int armySize = a.getUnits().size();

        assertEquals(armySize, 1);
    }

    @Test
    public void testRemoveUnit() throws Exception{
        Tile t = new Tile();
        Player p = new Player();
        Army a = new Army(t, p);
        MeleeUnit u = new MeleeUnit(t, p);
        a.addUnitToArmy(u);
        a.removeUnitFromArmy(u);
        int armySize = a.getUnits().size();

        assertEquals(armySize, 0);
    }


    // Tests
    @Test
    public void testDecommission() throws Exception {

        // create map, player, unit, army structure
        Map map = new Map();
        Player testPLayer = new Player();
        Unit melee = new MeleeUnit(map.getGrid()[0][0], testPLayer);
        Unit melee2 = new MeleeUnit(map.getGrid()[0][0], testPLayer);
        Unit colonist = new Colonist(map.getGrid()[1][0], testPLayer);
        Army army = new Army(map.getGrid()[0][0],  testPLayer);


        // add units and armies
        testPLayer.addUnit(melee);
        testPLayer.addUnit(melee2);
        testPLayer.addUnit(colonist);
        army.addUnitToArmy(melee);
        army.addUnitToArmy(melee2);
        army.addUnitToArmy(colonist);
        testPLayer.addArmy(army);

        // Assert size of the armies is 2 because only the melee units should be in there
        assertTrue(testPLayer.getArmies().get(0).getUnits().size() == 2);

        testPLayer.getArmies().get(0).decommission();

        testPLayer.checkUnitArmyStructs();

        assertTrue(testPLayer.getArmies().size() == 0);


    }



}
