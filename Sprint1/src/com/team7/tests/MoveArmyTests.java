package com.team7.tests;

import com.team7.objects.*;
import com.team7.objects.unit.Unit;
import com.team7.objects.unit.combatUnit.CombatUnit;
import com.team7.objects.unit.combatUnit.MeleeUnit;
import com.team7.objects.unit.combatUnit.RangedUnit;
import com.team7.objects.unit.nonCombatUnit.Colonist;
import com.team7.objects.unit.nonCombatUnit.Explorer;
import org.junit.Test;

/**
 * Tests for moving army via rallypoint & navigator
 */
public class MoveArmyTests {

    @Test
    public void moveArmy() throws Exception{
        Player p = new Player();
        Game game = new Game(p,new Player());
        game.startGame();

        for (int i = 0; i < 20; i++){
            for (int j = 0; j < 20; j++){
                game.getMap().getTile(i,j).clearTile();
            }
        }

        Unit ranged = new RangedUnit(game.getMap().getTile(17,3),p);        //create ranged unit at 17,3
        Unit melee = new MeleeUnit(game.getMap().getTile(17,3),p);
        Army a = new Army(game.getMap().getTile(17,3), p);                  //create army at 17,3
        a.addUnitToArmy(ranged);                                                //adding ranged to army
        a.addUnitToArmy(melee);



        Navigator nav = new Navigator(game.getMap(), a);

        nav.parseInputCommand('2');         //go to 18,2
        nav.parseInputCommand('8');
        nav.parseInputCommand('8');
        nav.parseInputCommand('8');
        nav.parseInputCommand('8');
        nav.parseInputCommand('8');
        nav.parseInputCommand('8');
        nav.parseInputCommand('8');
        nav.parseInputCommand('8');

        nav.updateModel();

        System.out.println("LOOK HERE: " + a.getRallyPoint().getyCoordinate());


    }
}
