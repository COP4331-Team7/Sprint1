package com.team7.tests;
import com.team7.objects.Army;
import com.team7.objects.Tile;
import com.team7.objects.unit.Unit;
import com.team7.objects.unit.combatUnit.MeleeUnit;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArmyTests {

    @Test
    public void testAddUnit() throws Exception{
        Tile t = new Tile();
        Army a = new Army(t, 0);
        MeleeUnit u = new MeleeUnit(t);
        a.addUnitToArmy(u);

        int armySize = a.getUnits().size();

        assertEquals(armySize, 1);
    }

    @Test
    public void testRemoveUnit() throws Exception{
        Tile t = new Tile();
        Army a = new Army(t, 0);
        MeleeUnit u = new MeleeUnit(t);
        a.addUnitToArmy(u);
        a.removeUnitFromArmy(u);
        int armySize = a.getUnits().size();

        assertEquals(armySize, 0);
    }
}
