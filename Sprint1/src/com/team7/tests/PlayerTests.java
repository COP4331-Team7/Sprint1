package com.team7.tests;

import com.team7.objects.Army;
import com.team7.objects.Player;
import com.team7.objects.Map;
import com.team7.objects.unit.Unit;
import com.team7.objects.unit.combatUnit.MeleeUnit;
import com.team7.objects.unit.combatUnit.RangedUnit;
import com.team7.objects.unit.nonCombatUnit.Colonist;
import com.team7.objects.unit.nonCombatUnit.Explorer;
import org.testng.annotations.Test;


import static org.junit.jupiter.api.Assertions.*;



public class PlayerTests {

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

    @Test
    // Create a couple Units for a player and then remove them to see if game ends
    public void checkPlayerDefeated() throws Exception {

        // create map and player
        Map map = new Map();
        Player testPLayer = new Player();

        // create units, armies and structures

        Unit colonist1 = new Colonist(map.getGrid()[0][0]);
        Unit colonist2 = new Colonist(map.getGrid()[0][0]);
        Unit melee = new MeleeUnit(map.getGrid()[0][0]);
        Unit explorer = new Explorer(map.getGrid()[0][0]);
        Unit ranged = new RangedUnit(map.getGrid()[0][0]);

        testPLayer.addUnit(colonist1);
        testPLayer.addUnit(colonist2);
        testPLayer.addUnit(melee);
        testPLayer.addUnit(explorer);
        testPLayer.addUnit(ranged);

        testPLayer.addArmy(new Army(map.getGrid()[0][0], 1));


        // remove units, armies and structures
        testPLayer.removeArmy(map.getGrid()[0][0].getArmies().get(0));

        testPLayer.removeUnit(colonist1);
        testPLayer.removeUnit(colonist2);
        testPLayer.removeUnit(explorer);
        testPLayer.removeUnit(ranged);
        // Comment out the line below to watch it fail
        testPLayer.removeUnit(melee);

        assertEquals(testPLayer.isDefeated(), true);

    }







}
