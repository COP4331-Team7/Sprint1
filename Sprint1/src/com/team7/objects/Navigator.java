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

    int x=0;
    int y=0;

    ArrayList<Unit> selectedUnits = new ArrayList<>();
    int unitsLeft;
    ArrayList<Integer> healthOfAllUnits = new ArrayList<>();

    int health;
    int movement;
    int collectedResearch = 0;
    int collectedMoney = 0;
    int collectedConstruction = 0;

    boolean tmpTileInQueue;

    //when MOVE mode is executed
    public Navigator(Map map, Unit selectedUnit){
        this.map = map;
        this.selectedUnit = selectedUnit;

        selectedUnits.add(0, selectedUnit);
        x = selectedUnit.getLocation().getxCoordinate();
        y = selectedUnit.getLocation().getyCoordinate();

        movement = selectedUnit.getUnitStats().getMovement();

        unitsLeft = selectedUnits.size();
        healthOfAllUnits.add(0, selectedUnit.getUnitStats().getHealth());   //add to 0th index the health of the unit passed
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
            if (isTilePassable(map.getTile(tmpX, tmpY))){//second ensure Tile is passable by current Unit
                if (hasMovementLeft()){ //third ensure that a unit can still move
                    //at this point, the move is VALID from a cursor perspective
                    tilePathList.add(map.getTile(tmpX,tmpY));
                    x = tmpX;
                    y = tmpY;
                    return true;
                }
            }
        }

        return false;

//        boolean valid = false;
//        boolean tmpTileinQueue = false;
//        for(int i = 0; i < selectedUnits.size(); i++){
//            selectedUnit = selectedUnits.get(i);
//            health = healthOfAllUnits.get(i);
//
//            System.out.print("Current Unit in Navigator checker index: " + i + "\t\t");
//            System.out.print("name: " + selectedUnit + "\t\t");
//            System.out.println("health: " + health + "\n");
//
//            //TODO check if unit is frozen
//            if (isInBounds(tmpX, tmpY)){ //first ensure Tile is in Bounds
//                if (isTilePassable(map.getTile(tmpX, tmpY))){//second ensure Tile is passable by current Unit
//                    if (hasMovementLeft()){ //third ensure that a unit can still move
//
//                        //at this point, the move is VALID from a cursor perspective
//                        valid = true;
//
//                        x = tmpX;
//                        y = tmpY;
//                        return true;
//
//                        //now affect unit
//                        //  if (isUnitAlive()) { //ensure unit is alive to affect stats
//                        //     calculateNetUnitEffectByTile(map.getTile(tmpX, tmpY));
//                        //     healthOfAllUnits.add(i, health);
//                        //     System.out.println("Health of unit has been changed to: " + healthOfAllUnits.get(i));
//                        //    if(!tilePathList.contains(map.getTile(tmpX, tmpY))){    //only add to list once
//                        //        calculateNetPlayerStatEffectByTile(map.getTile(tmpX, tmpY));
//                        //        tmpTileinQueue = true;
//                        //        tilePath.add(map.getTile(tmpX, tmpY)); //only add to tilePath if Unit survived the way, only do so once
//                        //       tilePathList.add(map.getTile(tmpX, tmpY));
//                        //       System.out.println("Added tile " + map.getTile(tmpX,tmpY) + " to queue \n");
//                        //   }
//
//                        //}
//                    }
//                }
//            }
//
//        }
//        System.out.println("END OF PARSE \n\n");
//        if(valid){
//            x = tmpX;
//            y = tmpY;
//            return true;
//        }
//        return false;
    }

    //when ENTER is pressed
    public void updateModel(){
        System.out.println("START OF UPDATE \n");
        Queue<Tile> tmpTilePath = tilePath;
        for(int i = 0; i < selectedUnits.size(); i++){
            selectedUnit = selectedUnits.get(i);

            System.out.println("current unit: " + selectedUnit);

            System.out.println("src coordinate X: "+ selectedUnit.getLocation().getxCoordinate());
            System.out.println("src coordinate Y: "+ selectedUnit.getLocation().getyCoordinate());
            System.out.println("tilePathQ size: " + tmpTilePath.size());

            if (tmpTilePath.peek() != null){
                tmpTilePath.peek().removeUnitFromTile(selectedUnit); //remove unit from starting point
                //Previously the loop was running for less no. of times because it was checking the tilePath.size()
                // which is changing on every iteration because of deletion
                for(int j = 0; j < tmpTilePath.size() - 1; j++){
                    tmpTilePath.remove().clearTile();    //remove all tiles in path EXCEPT the last one
                    //last element in tilePath is the unit destination
                }

                //now only one Tile left in tilePath, the last one (destination)
                tmpTilePath.peek().addUnitToTile(selectedUnit);        //add Unit to final tile
                selectedUnit.setLocation(tmpTilePath.peek());          //add tile location to unit

                if (selectedUnit.getArmy() != null){
                    selectedUnit.getArmy().setRallyPoint(tmpTilePath.peek());
                }

                tmpTilePath.remove().clearTile();                      //clear tile of resources
                tmpTilePath = tilePath;
                System.out.println("tmpTilePath updated to: " + tmpTilePath.toString());
            }

            //update stats
            selectedUnit.getUnitStats().setHealth(healthOfAllUnits.get(i));
            System.out.println("updated unit health to: " + healthOfAllUnits.get(i) + "\n\n");

        }

        System.out.println("target coordinate X: "+selectedUnit.getLocation().getxCoordinate());
        System.out.println("target coordinate Y: "+selectedUnit.getLocation().getyCoordinate() + "\n\n\n");

        selectedUnit.getOwner().setMoney(selectedUnit.getOwner().getMoney() + this.collectedMoney);
        selectedUnit.getOwner().setConstruction(selectedUnit.getOwner().getConstruction() + this.collectedConstruction);
        selectedUnit.getOwner().setResearch(selectedUnit.getOwner().getResearch() + this.collectedResearch);

    }

    public void updateModelViaPath(){
        System.out.println("START OF UPDATE \n");
        if (tilePathList.isEmpty()){
            return;
        }

        int unitsAliveInList = selectedUnits.size();

        //there exists some path of the CURSOR
        Tile currentTileInPath;
        for(int i = 0; i < tilePathList.size(); i++){ //iterate through each tile in final path list
            selectedUnit = selectedUnits.get(0);        //the first unit in an army, or an individual unit (ie explorer)
            currentTileInPath = tilePathList.get(i);
            calculateNetPlayerStatEffectByTile(currentTileInPath, selectedUnit);
            for(int j = 0; j < selectedUnits.size(); j++){  //iterate through each unit commanded (1 for non-Army)
                if(unitsAliveInList == 0){      //if no units are alive, dont move them
                    //delete the army
                    if (selectedUnit.getArmy() != null) {
                        selectedUnit.setArmy(null);
                    }

                    return;
                }
                selectedUnit = selectedUnits.get(j);
                calculateNetUnitEffectByTile(currentTileInPath, selectedUnit);      //updates the unit health and movement
                boolean dead = tryToRemoveUnit(selectedUnit);

                if (!dead){ //update location
                    selectedUnit.getLocation().removeUnitFromTile(selectedUnit);    //remove unit from old TILE

                    selectedUnit.setLocation(currentTileInPath);                    //update UNIT with tile reference
                    currentTileInPath.addUnitToTile(selectedUnit);                  //update TILE with unit reference

                    System.out.println("selectedUnit Tile x: " + selectedUnit.getLocation().getxCoordinate());
                    System.out.println("selectedUnit Tile y: " + selectedUnit.getLocation().getyCoordinate());
                }else{
                    unitsAliveInList--;
                    if (selectedUnit.getArmy() != null){
                        selectedUnit.getArmy().removeUnitFromArmy(selectedUnit);        //remove unit from army
                    }
                    selectedUnit.getOwner().removeUnit(selectedUnit);
                    selectedUnit.getLocation().removeUnitFromTile(selectedUnit);
                }
            }

            //update GUI via controller

        }

        if(selectedUnit.getArmy() != null){
            selectedUnit.getArmy().setRallyPoint(tilePathList.get(tilePathList.size() - 1));        //change RP of army to final Tile
            tilePathList.get(tilePathList.size() - 1).addArmyToTile(selectedUnit.getArmy());     //change Tile to have army
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

        return true;
    }
}
