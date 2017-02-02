package com.team7.tests;

import com.team7.objects.Army;
import com.team7.objects.Player;
import com.team7.objects.Map;
import com.team7.objects.structure.Base;
import com.team7.objects.structure.Structure;
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
            testPLayer.addUnit(new Colonist(map.getGrid()[0][0], testPLayer));
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

        Unit colonist1 = new Colonist(map.getGrid()[0][0], testPLayer);
        Unit colonist2 = new Colonist(map.getGrid()[0][0], testPLayer);
        Unit melee = new MeleeUnit(map.getGrid()[0][0], testPLayer);
        Unit explorer = new Explorer(map.getGrid()[0][0], testPLayer);
        Unit ranged = new RangedUnit(map.getGrid()[0][0], testPLayer);

        testPLayer.addUnit(colonist1);
        testPLayer.addUnit(colonist2);
        testPLayer.addUnit(melee);
        testPLayer.addUnit(explorer);
        testPLayer.addUnit(ranged);

        testPLayer.addArmy(new Army(map.getGrid()[0][0], 1, testPLayer));


        // remove units, armies and structures
        testPLayer.removeArmy(map.getGrid()[0][0].getArmies().get(0));

        testPLayer.removeUnit(colonist2);
        testPLayer.removeUnit(explorer);
        testPLayer.removeUnit(ranged);
        // Comment out the line below to watch it fail
        testPLayer.removeUnit(melee);


        // create base with colonist 1 so he should die, then remove base
        ((Colonist) colonist1).buildBase();
        testPLayer.removeStructure(testPLayer.getStructures().get(0));


        assertEquals(testPLayer.isDefeated(), true);

    }



    @Test
    // Tests that if a unit's health is 0 that it is deleted from army and player array and from Tile
    // function checkUnitArmyStructs does a sweep to remove all dead things
    public void testCheckUnitArmyStructs() throws Exception {

        // create map, player, unit, army structure
        Map map = new Map();
        Player testPLayer = new Player();
        Unit melee = new MeleeUnit(map.getGrid()[0][0], testPLayer);
        Unit colonist = new Colonist(map.getGrid()[1][0], testPLayer);
        Army army = new Army(map.getGrid()[0][0], 0, testPLayer);


        // add units and armies
        testPLayer.addUnit(melee);
        testPLayer.addUnit(colonist);
        testPLayer.addArmy(army);
        army.addUnitToArmy(melee);

        // kill unit and make sure it is out of Player array and tile array
        melee.getUnitStats().setHealth(0);
        testPLayer.checkUnitArmyStructs();
        assertTrue(testPLayer.getUnits().size() == 1);          // melee should be dead, colonist should be alive
        assertTrue(testPLayer.getArmies().size() == 0);         // army should be empty
        assertTrue(map.getGrid()[0][0].getUnits().size() == 0); // 00 tile should be empty


        //destroy structure
        ((Colonist) colonist).buildBase();
        testPLayer.getStructures().get(0).getStats().setHealth(0);
        testPLayer.checkUnitArmyStructs();


        // check that the player lost
        assertEquals(testPLayer.isDefeated(), true);

    }







}
