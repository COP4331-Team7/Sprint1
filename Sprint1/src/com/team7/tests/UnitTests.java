package com.team7.tests;

import com.team7.objects.Player;
import com.team7.objects.Map;
import com.team7.objects.unit.nonCombatUnit.Colonist;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;


public class UnitTests {

    @Test
    // Test that a Player can't add more than 10 units of the same type
    public void checkMaxTypes() throws Exception {

        // create map and player
        Map map = new Map();
        Player testPLayer = new Player();

        // attempt to add 11 players
        for(int i = 0; i < 11; i++){
            testPLayer.addUnit(new Colonist(map.getGrid()[0][0]));
        }

        //check that only 10 players are added
        int numberColonists = testPLayer.getUnits().size();

        assertEquals(10, numberColonists);

    }

}
