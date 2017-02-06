package com.team7.objects;

import com.team7.objects.areaEffects.ElixirShower;
import com.team7.objects.items.Obstacle;
import com.team7.objects.resource.HieroglyphicBooks;
import com.team7.objects.resource.MoneyBag;
import com.team7.objects.resource.MoonRocks;
import com.team7.objects.unit.Unit;
import com.team7.objects.unit.nonCombatUnit.Explorer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created in the controller
 */
public class Navigator {
    Map map;
    Unit selectedUnit;

    Queue<Tile> tilePath;
    ArrayList<Tile> tilePathList = new ArrayList<>();

    int unitsAliveInList;

    int x=0;
    int y=0;

    ArrayList<Unit> selectedUnits = new ArrayList<>();
    int unitsLeft;
    ArrayList<Integer> healthOfAllUnits = new ArrayList<>();

   int maxMovement;

    //when MOVE mode is executed
    public Navigator(Map map, Unit selectedUnit){
        this.map = map;
        this.selectedUnit = selectedUnit;

        selectedUnits.add(0, selectedUnit);
        x = selectedUnit.getLocation().getxCoordinate();
        y = selectedUnit.getLocation().getyCoordinate();

        maxMovement = selectedUnit.getUnitStats().getMovement();

        unitsLeft = selectedUnits.size();
        healthOfAllUnits.add(0, selectedUnit.getUnitStats().getHealth());   //add to 0th index the health of the unit passed
        unitsAliveInList  = selectedUnits.size();


    }

    public Navigator(Map map, Army army){
        this.map = map;
        selectedUnits = army.getUnits();
        x = army.getRallyPoint().getxCoordinate();
        y = army.getRallyPoint().getyCoordinate();
        tilePath = new LinkedList<>();
        tilePath.add(army.getRallyPoint());

        maxMovement = army.getSlowestSpeed();

        unitsAliveInList = selectedUnits.size();


        unitsLeft = selectedUnits.size();
        System.out.println("unitsLeft = " + unitsLeft);

        for (int i = 0; i < selectedUnits.size(); i++){
            healthOfAllUnits.add(i, selectedUnits.get(i).getUnitStats().getHealth());   //add army health to arraylist
        }


        System.out.println("");
    }



    //when arrow key is pressed
    public boolean parseInputCommand(char command){
        int tmpX = x;
        int tmpY = y;
        switch (command){
            case '1':       //SW
                tmpY++;
                tmpX--;
                break;
            case '2':       //S
                tmpY++;
                break;
            case '3':       //SE
                tmpY++;
                tmpX++;
                break;
            case '4':       //W
                tmpX--;
                break;
            //case "5": center
            //    break;
            case '6':       //E
                tmpX++;
                break;
            case '7':       //NW
                tmpY--;
                tmpX--;
                break;
            case '8':       //N
                tmpY--;
                break;
            case '9':       //NE
                tmpY--;
                tmpX++;
                break;
        }

        if (isInBounds(tmpX, tmpY)){ //first ensure Tile is in Bounds
            System.out.println("Inbounds");
            if (isTilePassable(map.getTile(tmpX, tmpY))){//second ensure Tile is passable by current Unit
                System.out.println("Is passable");
                if (hasMovementLeft()){ //third ensure that a unit can still move
                    System.out.println("has movement left");
                    //at this point, the move is VALID from a cursor perspective
                    tilePathList.add(map.getTile(tmpX,tmpY));
                    maxMovement += map.getTile(tmpX, tmpY).getTerrain().getMovementInfluence();
                    x = tmpX;
                    y = tmpY;
                    return true;
                }
            }
        }

        return false;
        //TODO check if unit is frozen
    }
    
    public ArrayList<Tile> updateModel(){
        if (tilePathList.isEmpty()){
            System.out.println("Empty Tile Path");
            return null;
        } else {
            return tilePathList;
        }
    }

    public void reDrawMapViaModel(Tile currentTileInPath) {
        selectedUnit = selectedUnits.get(0);        //the first unit in an army, or an individual unit (ie explorer)
        System.out.println("Iterating through tile path \t" + currentTileInPath);
        boolean dead = tryToRemoveUnit(selectedUnit);
        if(!dead){calculateNetPlayerStatEffectByTile(currentTileInPath, selectedUnit);}
        for (int j = 0; j < selectedUnits.size(); j++) {//iterate through each unit commanded (1 for non-Army)
            if (unitsAliveInList == 0) {      //if no units are alive, dont move them
                //delete the army
                if (selectedUnit.getArmy() != null) {
                    selectedUnit.setArmy(null);
                }

                return;
            }
            selectedUnit = selectedUnits.get(j);
            System.out.println("Iterating through each unit on tile \t" + selectedUnit);
            calculateNetUnitEffectByTile(currentTileInPath, selectedUnit);      //updates the unit health and movement
            System.out.println("Final Health \t" + selectedUnit.getUnitStats().getHealth());

            if (!dead) { //update location

                if (selectedUnit.getArmy() != null){        //make sure army is being referenced, army still alive
                    selectedUnit.getLocation().removeArmyFromTile(selectedUnit.getArmy());      //remove army from old TILE

                    selectedUnit.getArmy().setRallyPoint(currentTileInPath);                    //update ARMY with tile reference
                    currentTileInPath.addArmyToTile(selectedUnit.getArmy());                    //update TILE with army reference
                }

                selectedUnit.getLocation().removeUnitFromTile(selectedUnit);    //remove unit from old TILE

                selectedUnit.setLocation(currentTileInPath);                    //update UNIT with tile reference
                currentTileInPath.addUnitToTile(selectedUnit);                  //update TILE with unit reference



                System.out.println("selectedUnit Tile x: " + selectedUnit.getLocation().getxCoordinate());
                System.out.println("selectedUnit Tile y: " + selectedUnit.getLocation().getyCoordinate());
                //Check the health after it jas been changed on the tile
                    boolean isDeadAfterEffect = tryToRemoveUnit(selectedUnit);
                    if(isDeadAfterEffect){currentTileInPath.setDecal(new Decal("Skull"));}
                //
            } else {

                unitsAliveInList--;
                System.out.println("1 Unit died and remaining \t" + unitsAliveInList);
                if (selectedUnit.getArmy() != null) {
                    System.out.println("Removing army \t" );
                    selectedUnit.getArmy().removeUnitFromArmy(selectedUnit);        //remove unit from army
                }
                selectedUnit.getOwner().removeUnit(selectedUnit);
                selectedUnit.getLocation().removeUnitFromTile(selectedUnit);
            }

        }


    }


    private boolean tryToRemoveUnit(Unit unitToCheck){
        if (unitToCheck.getUnitStats().getHealth() <= 0){
            unitToCheck.getOwner().removeUnit(unitToCheck);
            return true;
        }
        return false;
    }

    private void calculateNetUnitEffectByTile(Tile currentTile, Unit currentUnit){
        //can be visited MULTIPLE times per movement interaction, to affect all units moving (i.e. army RP)
        //AreaEffect
        if (currentTile.getAreaEffect() != null){
            if(currentTile.getAreaEffect().isInstantDeath()){
                currentUnit.getUnitStats().setHealth(0); //instant death will set health to 0
                return;
            }
            currentUnit.getUnitStats().setHealth(currentUnit.getUnitStats().getHealth() + currentTile.getAreaEffect().getHealthEffect());
        }
    }

    private void calculateNetPlayerStatEffectByTile(Tile currentTile, Unit selectedUnit){
        //only visited ONCE per movement interaction

        //TODO add movement influence here


        //Resource - MUST BE DONE BY EXPLORER
        if (selectedUnit instanceof Explorer){
            if (currentTile.getResource() != null){
                if (currentTile.getResource() instanceof MoneyBag){
                    //increase Money stat
                    selectedUnit.getOwner().setMoney(currentTile.getResource().getStatInfluence() + selectedUnit.getOwner().getMoney());
                }
                if (currentTile.getResource() instanceof HieroglyphicBooks){
                    //increase Research stat
                    selectedUnit.getOwner().setResearch(currentTile.getResource().getStatInfluence() + selectedUnit.getOwner().getResearch());
                }
                if (currentTile.getResource() instanceof MoonRocks){
                    //increase Construction stat
                    selectedUnit.getOwner().setConstruction(currentTile.getResource().getStatInfluence() + selectedUnit.getOwner().getConstruction());
                }
                currentTile.setResource(null);
            }
        }

        //Item (OneShotItem by default) - CAN BE DONE BY ANY UNIT
        if (currentTile.getItem() != null){
            selectedUnit.getOwner().setMoney(currentTile.getItem().getStatInfluence() + selectedUnit.getOwner().getMoney());
            currentTile.setItem(null);
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
        maxMovement--;
        return maxMovement > 0;
    }
}
