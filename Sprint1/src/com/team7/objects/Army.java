package com.team7.objects;

import com.team7.ProbabilityGenerator;
import com.team7.objects.unit.Unit;

import java.util.ArrayList;


public class Army {
    private long id;
    private Player owner;
    private ArrayList<Unit> units;
    private CommandQueue commands;
    private int slowestSpeed; // Moves with speed of slowest unit
    private Tile rallyPoint;
    private int defenseDirection;
    private boolean isPowered;
    private int turnsFrozen;
    private String name;



    public Army(Tile startTile, Player player){
        int id = ProbabilityGenerator.randomInteger(0, 99999);
        this.units = new ArrayList<Unit>();
        this.commands = new CommandQueue();
        this.owner = player;
        this.slowestSpeed = 100;
        this.rallyPoint = startTile;
        this.defenseDirection = 0;
        this.isPowered = true;
        this.turnsFrozen = 0;
        this.name = "Army " + id; 
    }

    // Adds unit to Army's ArrayList of Units
    public void addUnitToArmy(Unit unit) {
        if(unit.getType() != "Colonist" && unit.getType() != "Explorer") {
            // Physically add the unit
            this.units.add(unit);
            unit.setArmy(this);

            // Check for new slowest speed
            if(unit.getUnitStats().getMovement() < this.slowestSpeed){
                this.slowestSpeed = unit.getUnitStats().getMovement();
            }
        }
    }

    // Removes unit from Army's ArrayList of Units
    public void removeUnitFromArmy(Unit unit) {

        this.units.remove(unit);
        unit.setArmy(null);
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public void setUnits(ArrayList<Unit> units) {
        this.units = units;
    }

    public CommandQueue getCommandQueue() {
        return commands;
    }

    public void setCommandQueue(CommandQueue commands) {
        this.commands = commands;
    }

    public int getSlowestSpeed() {
        return slowestSpeed;
    }

    public void setSlowestSpeed(int slowestSpeed) {
        this.slowestSpeed = slowestSpeed;
    }

    public Tile getRallyPoint() {
        return rallyPoint;
    }

    public void setRallyPoint(Tile rallyPoint) {
        this.rallyPoint = rallyPoint;
    }


    public boolean isPowered() {
        return isPowered;
    }

    public void powerUp() {


        for(int i = 0; i < units.size(); i++) {
            this.units.get(i).getUnitStats().setUpkeep(4);
            this.units.get(i).setMovesFrozen(2);
        }

        isPowered = true;
    }

    public void powerDown() {

        for(int i = 0; i < units.size(); i++) {
            this.units.get(i).getUnitStats().setUpkeep(1);
        }

        isPowered = false;
    }

    public void decommission() {
        for(int i = 0; i < this.units.size(); i++){
            this.units.get(i).decommission();
        }
    }

    public void disband() {
        for(int i = 0; i < this.units.size(); i++){
            removeUnitFromArmy(this.units.get(i));
        }
        this.owner.removeArmy(this);
    }

    public int getTurnsFrozen() {
        return turnsFrozen;
    }

    public void setTurnsFrozen(int turnsFrozen) {
        this.turnsFrozen = turnsFrozen;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
    public String getName() {
        return name;
    }

    public int getDefenseDirection() {
        return defenseDirection;
    }

    public void setDefenseDirection(int defenseDirection) {
        this.defenseDirection = defenseDirection;
    }

    public void setPowered(boolean powered) {
        isPowered = powered;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void attack(Map map, int direction) {
        Attacker attacker = new Attacker(map, this.units, direction);
        attacker.attack();
    }

    public void moveArmy(Map map, ArrayList<Tile> tiles) {
        Navigator navigator = new Navigator(map, this);
        for(int i = 0; i < tiles.size(); i++) {

            if(!navigator.reDrawMapViaModel(tiles.get(i), units)){
                //user is out of movement, cut arraylist
                for (int j = i; j > 0; j--){        //remove all elements from i and under
                    tiles.remove(j);
                }
                System.out.println("queuedTiles at cursor: " + tiles.toString());
                //send queuedTiles to the commandQ
                StringBuilder sb = new StringBuilder();

                sb.append(" ");
                sb.append(getId());
                sb.append(" move ");
                System.out.println("adding new command because movement not possible: " + sb.toString());
                getCommandQueue().addCommand(new CommandObject(sb.toString(), tiles));
                return;
            }
            navigator.reDrawMapViaModel(tiles.get(i),units);

        }
    }


    // this helper function decodes string of next command in command queue and calls necessary function
    public void decodeNextInstruction(Map map) {

        String commandString = "";
        CommandObject command = null;
        // Get the next command to be run and remove it from the queue
        if (commands.getCommands().size() > 0) {

            command = commands.getCommands().get(0);
            commands.getCommands().remove(0);
            commandString = command.getCommandString();
        }

        System.out.println("Command String: " + commandString);
        if(commandString.contains("attack")) {
            int dir = Integer.parseInt(commandString.substring(commandString.length() - 1));
            attack(map, dir);
        }
        else if(commandString.contains("defend")) {
            int dir = Integer.parseInt(commandString.substring(commandString.length() - 1));
            setDefenseDirection(dir);
        }
        else if(commandString.contains("move")) {
            ArrayList<Tile> tiles = command.getMovementTiles();
            System.out.println("about to MOVE army to: " + tiles.toString());
            moveArmy(map, tiles);
        }
        else if(commandString.contains("wait")) {
            System.out.println("WAIT :)");
        }
        else if(commandString.contains("disband")) {
            this.disband();
        }
        else if(commandString.contains("decommission")) {
            this.decommission();
        }
        else if(commandString.contains("down")) {
            this.powerDown();
        }
        else if(commandString.contains("up")) {
            this.powerUp();
        }
        else if(commandString.contains("cancel")) {
            this.commands.getCommands().clear();
        }

    }

}

