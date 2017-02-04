package com.team7.tests;

import com.team7.objects.Game;
import com.team7.objects.Navigator;
import com.team7.objects.Player;
import com.team7.objects.areaEffects.Storm;
import com.team7.objects.areaEffects.VolcanicVent;
import com.team7.objects.resource.MoneyBag;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test Navigation class for one Unit
 */
public class MoveUnitTest {
    @Test
    public void moveUnit() throws Exception{
        Game game = new Game(new Player(),new Player());
        game.startGame();
        Navigator navigator = new Navigator(game.getMap(),game.getCurrentPlayer().getUnits().get(0));
        assertEquals(game.getCurrentPlayer().getUnits().get(0).getLocation().getxCoordinate(), 17);
        assertEquals(game.getCurrentPlayer().getUnits().get(0).getLocation().getyCoordinate(), 3);
        navigator.parseInputCommand("1");
        navigator.updateModel();
        assertEquals(game.getCurrentPlayer().getUnits().get(0).getLocation().getxCoordinate(), 18);
        assertEquals(game.getCurrentPlayer().getUnits().get(0).getLocation().getyCoordinate(), 2);

    }
    @Test
    public void moveUnitBounds() throws Exception{
        Game game = new Game(new Player(),new Player());
        game.startGame();
        Navigator navigator = new Navigator(game.getMap(),game.getCurrentPlayer().getUnits().get(0));
        assertEquals(game.getCurrentPlayer().getUnits().get(0).getLocation().getxCoordinate(), 17);
        assertEquals(game.getCurrentPlayer().getUnits().get(0).getLocation().getyCoordinate(), 3);
        navigator.parseInputCommand("1");
        navigator.parseInputCommand("1");
        navigator.parseInputCommand("1");
        navigator.parseInputCommand("1");
        navigator.parseInputCommand("1");
        navigator.parseInputCommand("1");
    }
    @Test
    public void checkResourceEffect() throws Exception{
        Game game = new Game(new Player(),new Player());
        game.startGame();
        game.getMap().getTile(18, 2).clearTile();
        game.getMap().getTile(18,2).setResource(new MoneyBag());
        Navigator navigator = new Navigator(game.getMap(),game.getCurrentPlayer().getUnits().get(0));
        int previous = game.getCurrentPlayer().getMoney();
        navigator.parseInputCommand("1");
        navigator.updateModel();
        navigator.parseInputCommand("9");
        navigator.updateModel();
        navigator.parseInputCommand("9");
        navigator.updateModel();

        assertEquals(game.getCurrentPlayer().getUnits().get(0).getLocation().getxCoordinate(),16);
        assertEquals(game.getCurrentPlayer().getUnits().get(0).getLocation().getyCoordinate(),4);
        assertTrue(game.getCurrentPlayer().getMoney()>=previous);

    }
    @Test
    public void checkAreaEffect() throws Exception{
        Game game = new Game(new Player(),new Player());
        game.startGame();
        game.getMap().getTile(18, 2).clearTile();
        game.getMap().getTile(18,2).setAreaEffect(new Storm());
        int oldHealth = game.getCurrentPlayer().getUnits().get(0).getUnitStats().getHealth();
        Navigator navigator = new Navigator(game.getMap(),game.getCurrentPlayer().getUnits().get(0));
        navigator.parseInputCommand("1");
        navigator.updateModel();
        navigator.parseInputCommand("9");
        navigator.updateModel();
        assertTrue(game.getCurrentPlayer().getUnits().get(0).getUnitStats().getHealth() < oldHealth);
        assertEquals(game.getCurrentPlayer().getUnits().get(0).getLocation().getxCoordinate(),17);
        assertEquals(game.getCurrentPlayer().getUnits().get(0).getLocation().getyCoordinate(),3);

    }

    @Test
    public void checkInstantDeath() throws Exception{
        Game game = new Game(new Player(),new Player());
        game.startGame();
        game.getMap().getTile(18, 2).clearTile();
        game.getMap().getTile(18,2).setAreaEffect(new VolcanicVent());

    }


}
