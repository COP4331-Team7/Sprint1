package com.team7.objects;

import com.team7.objects.items.Obstacle;
import com.team7.objects.resource.HieroglyphicBooks;
import com.team7.objects.resource.MoneyBag;
import com.team7.objects.resource.MoonRocks;
import com.team7.objects.unit.Unit;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created in the controller
 */
public class Navigator {
    Map map;
    Queue<String> inputCommands;

    public Navigator(Map m){
        this.map = map;
    }

    //when a unit is selected and cursor in MOVE mode
    public void beginMovementCommands(){
        inputCommands = new LinkedList<>();
    }
    //when arrow key is pressed
    public void inputCommands(String command){
        inputCommands.add(command);
    }

    //when ENTER key is pressed
    public void operateUnitMovementCommand(Unit unit, Player commander){
        Queue<Tile> tilePath = new LinkedList<>();
        String direction;
        int x = unit.getLocation().getxCoordinate();
        int y = unit.getLocation().getyCoordinate();
        while (!inputCommands.isEmpty()){
            direction = inputCommands.remove();
            switch (direction){
                case "E":
                    if (verifyBound(x, y++)){
                        Tile tmp = map.getTile(x, y++);
                        if(verifyTile(tmp)){
                            tilePath.add(tmp);
                        }
                    }
                    break;
            }
        }

        calculateNetEffectByTile(tilePath);



    }

    //calculate all tile effects
    private void calculateNetEffectByTile(Queue<Tile> tilePath) {
        int collectedElixir = 0;
        int collectedMoney = 0;
        int collectedResearch = 0;
        int collectedConstruction = 0;

        Tile currentTile;
        while (!tilePath.isEmpty()) {
            currentTile = tilePath.remove();

            //AreaEffect
            if (currentTile.getAreaEffect() != null){
               collectedElixir += currentTile.getAreaEffect().getHealthEffect();
            }

            //Resource
            if (currentTile.getResource() != null){
                if (currentTile.getResource() instanceof MoneyBag){
                    //increase Money stat
                    collectedMoney += currentTile.getResource().getStatInfluence();
                }
                if (currentTile.getResource() instanceof HieroglyphicBooks){
                    //increase Research stat
                    collectedResearch += currentTile.getResource().getStatInfluence();
                }
                if (currentTile.getResource() instanceof MoonRocks){
                    //increase Construction stat
                    collectedConstruction += collectedConstruction;
                }
            }

            //Item (OneShotItem by default)
            if (currentTile.getItem() != null){
                collectedMoney += currentTile.getItem().getStatInfluence();
            }


        }
    }


    private boolean verifyBound(int x, int y) {
        if (x <= 19 || x >= 0 || y <= 19 || y >= 0) {
            return true;
        }
        return false;
    }

    private boolean verifyTile(Tile tmp) {
        //check if impassible
        if (!tmp.getTerrain().isPassable()){
            return false;
        }
        //check if obstacle
        if (tmp.getItem() != null){
            if (tmp.getItem() instanceof Obstacle){
                return false;
            }
        }
        return true;

    }
}
