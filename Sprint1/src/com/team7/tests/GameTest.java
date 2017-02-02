package com.team7.tests;

import com.team7.objects.Game;
import com.team7.objects.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;



class GameTest {

    @Test
    void nextTurn() throws Exception {
        Player p1 = new Player();
        Player p2 = new Player();
        Game g = new Game(p1, p2);

        g.nextTurn();
        assertEquals(2, g.getTurn());
    }

    @Test
    void getCurrentPlayer() throws Exception{
        Player p1 = new Player();
        Player p2 = new Player();
        Game g = new Game(p1, p2);

        Player t = g.getCurrentPlayer();
        assertEquals(t, p1);
    }

}