package com.team7.tests;

import com.team7.objects.*;
import com.team7.objects.unit.Unit;
import com.team7.objects.unit.combatUnit.MeleeUnit;
import com.team7.objects.unit.nonCombatUnit.Colonist;
import org.testng.annotations.Test;

import static org.junit.Assert.*;
import static org.testng.AssertJUnit.assertEquals;

public class QueueTests {

    @Test
    // test a queued army attack
    public void testArmyAttack() throws Exception {

        // start game
        Player player1 = new Player();
        Player player2 = new Player();
        Game g = new Game(player1, player2);
        g.startGame();

        // Create army and put attack command in queue
        Army army = new Army(g.getMap().getGrid()[17][2], player1);
        Unit melee1 = new MeleeUnit(g.getMap().getGrid()[17][2], player1);
        Unit melee2 = new MeleeUnit(g.getMap().getGrid()[17][3], player2);
        player1.addUnit(melee1);
        player2.addUnit(melee2);
        army.addUnitToArmy(melee1);
        player1.addArmy(army);



        // Execute attack command
        army.getCommandQueue().addCommand(new CommandObject("attack 2", null));

        g.executeQueues();

        assertTrue(player2.getUnits().get(3).getUnitStats().getHealth() < 100);

    }

    @Test
    // tests a queued order of healing units
    public void testHealUnits() throws Exception {

        // start game
        Player player1 = new Player();
        Player player2 = new Player();
        Game g = new Game(player1, player2);
        g.startGame();

        // Create army and put attack command in queue
        Unit colonist = new Colonist(g.getMap().getGrid()[17][2], player1);
        Unit melee = new MeleeUnit(g.getMap().getGrid()[17][2], player1);
        player1.addUnit(melee);
        player1.addUnit(colonist);


        // Set melee health to low and create a base
        melee.getUnitStats().setHealth(12);
        ((Colonist) colonist).buildBase();

        player1.getStructures().get(0).getCommandQueue().addCommand(new CommandObject("heal/repair unit", null));
        player1.getStructures().get(0).getCommandQueue().addCommand(new CommandObject("heal/repair unit", null));

        g.executeQueues();
        g.executeQueues();

        assertEquals(player1.getUnits().get(3).getUnitStats().getHealth(), 62);

    }







}