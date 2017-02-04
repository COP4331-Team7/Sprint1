package com.team7.objects;

import com.team7.objects.areaEffects.ElixirShower;
import com.team7.objects.items.Obstacle;
import com.team7.objects.resource.HieroglyphicBooks;
import com.team7.objects.resource.MoneyBag;
import com.team7.objects.resource.MoonRocks;
import com.team7.objects.unit.Unit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created in the controller
 */
public class Navigator {
    Map map;
    String inputCommand;
    Unit selectedUnit;
    Queue<Tile> tilePath;
    int x;
    int y;

    ArrayList<Unit> selectedUnits;
    int unitsLeft;

    int health;
    int movement;
    int collectedResearch = 0;
    int collectedMoney = 0;
    int collectedConstruction = 0;

    //when MOVE mode is executed
    public Navigator(Map map, Unit selectedUnit){
        this.map = map;
        this.selectedUnit = selectedUnit;
        selectedUnits = new ArrayList<>();
        selectedUnits.add(selectedUnit);
        x = selectedUnit.getLocation().getxCoordinate();
        y = selectedUnit.getLocation().getyCoordinate();
        tilePath = new LinkedList<>();
        tilePath.add(selectedUnit.getLocation());
        movement = selectedUnit.getUnitStats().getMovement();

        unitsLeft = selectedUnits.size();
    }

    public Navigator(Map map, Army army){
        this.map = map;
        selectedUnits = army.getUnits();
        x = army.getRallyPoint().getxCoordinate();
        y = army.getRallyPoint().getyCoordinate();
        tilePath = new LinkedList<>();
        tilePath.add(army.getRallyPoint());
        movement = army.getSlowestSpeed();

        unitsLeft = selectedUnits.size();
    }



    //when arrow key is pressed
    public boolean parseInputCommand(String command){
        int tmpX = x;
        int tmpY = y;
        switch (command){
            case "1":       //SW
                tmpX++;
                tmpY--;
                break;
            case "2":       //S
                tmpX++;
                break;
            case "3":       //SE
                tmpX++;
                tmpY++;
                break;
            case "4":       //W
                tmpY--;
                break;
            //case "5": center
            //    break;
            case "6":       //E
                tmpY++;
                break;
            case "7":       //NW
                tmpX--;
                tmpY--;
                break;
            case "8":       //N
                tmpX--;
                break;
            case "9":       //NE
                tmpX--;
                tmpY++;
                break;
        }

        for(Unit u : selectedUnits){
            health = u.getUnitStats().getHealth();

            if (isInBounds(tmpX, tmpY)){ //first ensure Tile is in Bounds
                if (isTilePassable(map.getTile(tmpX, tmpY))){ //second ensure Tile is passable by current Unit
                    if (hasMovementLeft()){ //third ensure that a unit can still move
                        if (isUnitAlive() && hasUnitRemaining()) { //ensure unit is alive to affect stats and navigator has unit
                            calculateNetEffectByTile(map.getTile(tmpX, tmpY));
                            tilePath.add(map.getTile(tmpX, tmpY)); //only add to tilePath if Unit survived the way
                        }
                        x = tmpX;
                        y = tmpY;
                        return true;
                    }
                }
            }
        }

        return false;
    }

    //when ENTER is pressed
    public void updateModel(){
        if (tilePath.peek() != null){
            tilePath.peek().removeUnitFromTile(selectedUnit); //remove unit from starting point


            for(int i = 0; i < tilePath.size() - 1; i++){
                tilePath.remove().clearTile();    //remove all tiles in path EXCEPT the last one
                //last element in tilePath is the unit destination
            }
            tilePath.peek().addUnitToTile(selectedUnit);        //add Unit to final tile
            selectedUnit.setLocation(tilePath.peek());          //add tile location to unit
            tilePath.remove().clearTile();                      //clear tile of resources
        }

        //update stats
        selectedUnit.getUnitStats().setHealth(this.health);
        selectedUnit.getOwner().setMoney(selectedUnit.getOwner().getMoney() + this.collectedMoney);
        selectedUnit.getOwner().setConstruction(selectedUnit.getOwner().getConstruction() + this.collectedConstruction);
        selectedUnit.getOwner().setResearch(selectedUnit.getOwner().getResearch() + this.collectedResearch);
    }


    private boolean hasUnitRemaining(){
        if (!isUnitAlive()){    //if unit is dead, decrement units left
            unitsLeft--;
        }
        return unitsLeft > 0;
    }
    private boolean isUnitAlive(){
        return health > 0;
    }
    //calculate all tile effects
    //TODO add instant death
    private void calculateNetEffectByTile(Tile currentTile) {

        //AreaEffect
        if (currentTile.getAreaEffect() != null){
            if(currentTile.getAreaEffect().isInstantDeath()){
                health = 0; //instant death will set health to 0
                return;
            }
           health += currentTile.getAreaEffect().getHealthEffect();
        }

        if (isUnitAlive()) { //stat collection only occurs if unit is alive
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


    private boolean isInBounds(int x, int y) {
        if (x <= 19 && x >= 0 && y <= 19 && y >= 0) {
            return true;
        }
        return false;
    }

    private boolean isTilePassable(Tile tmp) {
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

    private boolean hasMovementLeft() {

        return true;
    }
}
