package com.team7.tests;
import com.team7.objects.Game;
import com.team7.objects.Player;
import org.testng.annotations.Test;

public class gameTests {

    @Test
    public void testNextTurn() {
        Player p1 = new Player();
        Player p2 = new Player();
        Game g = new Game(p1, p2);
        g.nextTurn();

        AssertEquals(1, g.getCurrentPlayerNum());
    }

    @Test
    public void test

}
