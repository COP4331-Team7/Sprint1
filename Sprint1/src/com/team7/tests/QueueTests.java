package com.team7.tests;

import com.sun.source.tree.AssertTree;
import com.team7.objects.*;
import com.team7.objects.unit.Unit;
import com.team7.objects.unit.combatUnit.MeleeUnit;
import org.testng.annotations.Test;

import static org.junit.Assert.*;
import static org.testng.AssertJUnit.assertEquals;

public class QueueTests {

    @Test
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
        army.getCommandQueue().addCommand(new Command("attack 2", null));

        g.executeQueues();

        assertTrue(player2.getUnits().get(3).getUnitStats().getHealth() < 100);



    }


}