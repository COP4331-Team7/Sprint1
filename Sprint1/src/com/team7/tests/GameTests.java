package com.team7.tests;

import com.team7.objects.Game;
import com.team7.objects.Map;
import com.team7.objects.Player;
import com.team7.objects.unit.Unit;
import com.team7.objects.unit.nonCombatUnit.Colonist;
import org.testng.annotations.Test;

import static org.junit.Assert.*;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;


public class GameTests {

    @Test
    public void nextTurn() throws Exception {
        Player p1 = new Player();
        Player p2 = new Player();
        Game g = new Game(p1, p2);

        g.nextTurn();
        assertEquals(2, g.getTurn());
    }

    @Test
    public void getCurrentPlayer() throws Exception{
        Player p1 = new Player();
        Player p2 = new Player();
        Game g = new Game(p1, p2);

        Player t = g.getCurrentPlayer();
        assertEquals(t, p1);
    }




}