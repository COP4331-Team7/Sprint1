package com.team7.tests;

import com.team7.objects.*;
import com.team7.objects.structure.Base;
import com.team7.objects.structure.Structure;
import com.team7.objects.unit.Unit;
import com.team7.objects.unit.combatUnit.CombatUnit;
import com.team7.objects.unit.combatUnit.MeleeUnit;
import com.team7.objects.unit.combatUnit.RangedUnit;
import com.team7.objects.unit.nonCombatUnit.Colonist;
import com.team7.objects.unit.nonCombatUnit.Explorer;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;


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

        testPLayer.addArmy(new Army(map.getGrid()[0][0], testPLayer));


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
        Army army = new Army(map.getGrid()[0][0],  testPLayer);


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


    @Test
    // Check Structure is being created, make some units. Kill them and check defeated
    public void testBaseCreateUnit() throws Exception {

        // create map, player, unit, army structure
        Map map = new Map();
        Player testPLayer = new Player();
        Unit colonist = new Colonist(map.getGrid()[17][3], testPLayer);
        testPLayer.addUnit(colonist);
        testPLayer.addArmy(new Army(map.getGrid()[17][3], testPLayer));

        assertTrue(testPLayer.getUnits().size() == 1);

        // Build base and create units
        ((Colonist) colonist).buildBase();

        ((Base) testPLayer.getStructures().get(0)).createUnit("Explorer");
        ((Base) testPLayer.getStructures().get(0)).createUnit("Melee");
        ((Base) testPLayer.getStructures().get(0)).createUnit("Melee");

        // add units to army
        testPLayer.getArmies().get(0).addUnitToArmy(testPLayer.getUnits().get(1));
        testPLayer.getArmies().get(0).addUnitToArmy(testPLayer.getUnits().get(2));

        assertTrue(testPLayer.getUnits().size() == 3);

        // kill all units and structures
        for(int i = 0; i < testPLayer.getUnits().size(); i++){
            testPLayer.getUnits().get(i).getUnitStats().setHealth(0);
        }
        testPLayer.getStructures().get(0).getStats().setHealth(0);

        // delete dead everything
        testPLayer.checkUnitArmyStructs();


        // check that the player lost
        assertEquals(testPLayer.isDefeated(), true);


    }


    @Test
    // Test creating units and making sure they exist on the tiles
    public void checkUnitType() throws Exception {
        Map m = new Map();
        Player p1 = new Player();

        Unit colonist = new Colonist(m.getGrid()[0][0], p1);

        p1.addUnit(colonist);

        Unit testUnit = p1.getUnits().get(0);

        assertTrue(testUnit.getType() ==  "Colonist");
        assertTrue(testUnit == colonist);

    }



    @Test
    // This tests the target tiles returned by calcTargetTiles is correct
    public void testTargetTiles() throws Exception {

        // create map and player
        Map map = new Map();
        Player testPLayer = new Player();

        // create units and give them to player
        Unit melee = new MeleeUnit(map.getGrid()[17][2], testPLayer);
        Unit ranged = new RangedUnit(map.getGrid()[18][2], testPLayer);
        testPLayer.addUnit(melee);
        testPLayer.addUnit(ranged);

        // check Melee units only get the tile next to it
        Attacker attacker = new Attacker(map, melee, 2);
        ArrayList<Tile> tiles =  attacker.calcTargetTiles();

        // Ensures we get the exact tile
        assertEquals(tiles.get(0).getxCoordinate(), map.getTile(17, 3).getxCoordinate());
        assertEquals(tiles.get(0).getyCoordinate(), map.getTile(17, 3).getyCoordinate());
        assertEquals(tiles.get(0), map.getTile(17, 3));

        // Check we get 5 tiles
        attacker = new Attacker(map, ranged, 4);
        tiles = attacker.calcTargetTiles();

        assertEquals(tiles.size(), 5);
        assertEquals(tiles.get(0), map.getTile(17, 2));
        assertEquals(tiles.get(1), map.getTile(16, 2));
        assertEquals(tiles.get(4), map.getTile(13, 2));


    }


    @Test
    // This tests the attacking for a single unit
    public void testAttackMelee() throws Exception {

        // create map and player
        Map map = new Map();
        Player player1 = new Player();
        Player player2 = new Player();

        // create units and give them to player
        Unit melee = new MeleeUnit(map.getGrid()[17][2], player1);
        Unit teammate = new MeleeUnit(map.getGrid()[17][3], player1);
        Unit ranged = new RangedUnit(map.getGrid()[18][2], player2);
        player1.addUnit(melee);
        player2.addUnit(ranged);
        player1.addUnit(teammate);

        // check Melee units only get the tile next to it
        Attacker attacker = new Attacker(map, melee, 6);

        attacker.attack();

        assertEquals(player2.getUnits().get(0).getUnitStats().getArmor(), 0);
        assertTrue(player2.getUnits().get(0).getUnitStats().getHealth() < 100);


        // check ranged attack
        attacker = new Attacker(map, ranged, 4);

        attacker.attack();
        assertEquals(player1.getUnits().get(0).getUnitStats().getArmor(), 0);
        assertTrue(player1.getUnits().get(0).getUnitStats().getHealth() < 100);


        // check you can't attack a teammate
        attacker = new Attacker(map, melee, 2);
        attacker.attack();

        assertEquals(player1.getUnits().get(1).getUnitStats().getArmor(), 10);
        assertEquals(player1.getUnits().get(1).getUnitStats().getHealth(), 100);


    }


    @Test
    // This tests the attacking for a single unit
    public void testAttackRanged() throws Exception {

        // create map and player
        Map map = new Map();
        Player player1 = new Player();
        Player player2 = new Player();

        // create units and give them to player
        Unit melee = new MeleeUnit(map.getGrid()[17][2], player1);
        Unit ranged = new RangedUnit(map.getGrid()[17][5], player2);
        player1.addUnit(melee);
        player2.addUnit(ranged);


        // check Melee units only get the tile next to it
        Attacker attacker = new Attacker(map, ranged, 8);

        attacker.attack();

        assertEquals(player1.getUnits().get(0).getUnitStats().getArmor(), 0);
        assertTrue(player1.getUnits().get(0).getUnitStats().getHealth() < 100);


    }



    @Test
    // This tests the attacking for an army
    public void testAttackArmy() throws Exception {

        // create map and player
        Map map = new Map();
        Player player1 = new Player();
        Player player2 = new Player();

        // create army and units for each player
        Unit melee1 = new MeleeUnit(map.getGrid()[17][2], player1);
        Unit melee2 = new MeleeUnit(map.getGrid()[17][2], player1);
        Unit melee3 = new MeleeUnit(map.getGrid()[17][2], player1);
        Unit melee4 = new MeleeUnit(map.getGrid()[17][2], player1);
        Unit melee5 = new MeleeUnit(map.getGrid()[17][2], player1);
        Unit ranged1 = new RangedUnit(map.getGrid()[17][2], player1);
        Unit ranged2 = new RangedUnit(map.getGrid()[17][2], player1);
        Unit ranged3 = new RangedUnit(map.getGrid()[17][2], player1);
        Army army1 = new Army(map.getGrid()[17][2], player1);
        player1.addUnit(melee1);
        player1.addUnit(melee2);
        player1.addUnit(melee3);
        player1.addUnit(melee4);
        player1.addUnit(melee5);
        player1.addUnit(ranged1);
        player1.addUnit(ranged2);
        player1.addUnit(ranged3);
        army1.addUnitToArmy(melee1);
        army1.addUnitToArmy(melee2);
        army1.addUnitToArmy(melee3);
        army1.addUnitToArmy(melee4);
        army1.addUnitToArmy(melee5);
        army1.addUnitToArmy(ranged1);
        army1.addUnitToArmy(ranged2);
        army1.addUnitToArmy(ranged3);
        player1.addArmy(army1);


        Unit melee8 = new MeleeUnit(map.getGrid()[17][3], player2);
        Unit melee9 = new MeleeUnit(map.getGrid()[17][3], player2);
        Unit melee10 = new MeleeUnit(map.getGrid()[17][4], player2);
        Army army2 = new Army(map.getGrid()[17][3], player2);
        player2.addUnit(melee8);
        player2.addUnit(melee9);
        player2.addUnit(melee10);
        army2.addUnitToArmy(melee8);
        army2.addUnitToArmy(melee9);
        player2.addArmy(army2);
        player2.addStructure(new Base(map.getGrid()[16][2], player2));



        // check Melee units only get the tile next to it and ranged hits farther
        Attacker attacker = new Attacker(map, army1.getUnits(), 2);
        attacker.attack();
        attacker = new Attacker(map, army1.getUnits(), 2);
        attacker.attack();
        attacker = new Attacker(map, army1.getUnits(), 2);
        attacker.attack();
        attacker = new Attacker(map, army1.getUnits(), 2);
        attacker.attack();
        attacker = new Attacker(map, army1.getUnits(), 2);
        attacker.attack();

        // destroy base
        attacker = new Attacker(map, army1.getUnits(), 4);
        attacker.attack();
        attacker = new Attacker(map, army1.getUnits(), 4);
        attacker.attack();
        attacker = new Attacker(map, army1.getUnits(), 4);
        attacker.attack();


        // check health
        System.out.println(player2.getStructures().get(0).getStats().getHealth());

        // print health for each unit
        for(int i = 0; i < player2.getUnits().size(); i++) {
            System.out.println(player2.getUnits().get(i).getUnitStats().getHealth());
        }


        player2.checkUnitArmyStructs();

        assertEquals(player2.isDefeated(), true);

    }





}
