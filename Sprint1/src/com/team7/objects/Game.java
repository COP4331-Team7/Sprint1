package com.team7.objects;

import com.team7.objects.areaEffects.Storm;
import com.team7.objects.areaEffects.VolcanicVent;
import com.team7.objects.resource.HieroglyphicBooks;
import com.team7.objects.structure.Base;
import com.team7.objects.unit.Unit;
import com.team7.objects.unit.combatUnit.MeleeUnit;
import com.team7.objects.unit.combatUnit.RangedUnit;
import com.team7.objects.unit.nonCombatUnit.Colonist;
import com.team7.objects.unit.nonCombatUnit.Explorer;

/*
    The Game class is just inside of the boundary of our “model” (MVC paradigm).
    It keeps track of the Players, the Map, and watches for when a player is defeated, and facilitates gameplay.
*/
public class Game {
    //Array of Player objects representing the two players of the game
    private Player[] players = new Player[2];
    //The game map
    private Map map;
    //The turn number
    private int turn;
    //Current Player
    private Player currentPlayer;

    public Game(Player p1, Player p2) {
        players[0] = p1;
        players[1] = p2;
        turn = 0;
        currentPlayer = players[0];
    }

    //Initializes the map, and runs the turns. Ends the game when a player is defeated
    public void startGame() {

        // create map and populate with items/resources/area effects
        this.map = new Map();
        map.setMapDetails();

        // Put initial 2 explorers and colonists into Player's array to start game
        // Player 1 starts in top right, player 2 starts in bottom left
        players[0].addUnit(new Explorer(this.map.getGrid()[15][3], players[0]));
        players[0].addUnit(new Explorer(this.map.getGrid()[17][4], players[0]));
        players[0].addUnit(new Colonist(this.map.getGrid()[16][4], players[0]));

        Explorer e = new Explorer(this.map.getGrid()[3][17], players[1]);
        e.getUnitStats().setMovement(50);
        e.getUnitStats().setMovement(50);
        Explorer e2 = new Explorer(this.map.getGrid()[3][18], players[1]);
        players[1].addUnit(e);
        players[1].addUnit(e2);
        players[1].addUnit(new Colonist(this.map.getGrid()[4][18], players[1]));



        //INITIAL PLAYER

        //army goes through storm twice, 2 of the 3 die
        Player player1 = players[0];
        Unit melee1 = new MeleeUnit(map.getGrid()[17][2], player1);
        Unit melee2 = new MeleeUnit(map.getGrid()[17][2], player1);
        Unit ranged1 = new RangedUnit(map.getGrid()[17][2], player1);
        melee1.getUnitStats().setHealth(10);
        melee2.getUnitStats().setHealth(40);
        Army army1 = new Army(map.getGrid()[17][2], player1);
        player1.addUnit(melee1);
        player1.addUnit(melee2);
        player1.addUnit(ranged1);
        army1.addUnitToArmy(melee1);
        army1.addUnitToArmy(melee2);
        army1.addUnitToArmy(ranged1);
        army1.setSlowestSpeed(15);
        player1.addArmy(army1);
        this.map.getGrid()[16][2].setAreaEffect(new Storm());

        //explorer picking up resources + screen scrolling
        this.map.getGrid()[14][6].setResource(new HieroglyphicBooks());


        //SECOND PLAYER
        //army goes thru volcano
        Player player2 = players[1];
        Unit melee1_2 = new MeleeUnit(map.getGrid()[4][16], player1);
        Unit melee2_2 = new MeleeUnit(map.getGrid()[4][16], player1);
        Unit ranged1_2 = new RangedUnit(map.getGrid()[4][16], player1);
        Army army1_2 = new Army(map.getGrid()[4][16], player1);
        player2.addUnit(melee1_2);
        player2.addUnit(melee2_2);
        player2.addUnit(ranged1_2);
        army1_2.addUnitToArmy(melee1_2);
        army1_2.addUnitToArmy(melee2_2);
        army1_2.addUnitToArmy(ranged1_2);
        army1_2.setSlowestSpeed(15);
        player2.addArmy(army1_2);
        this.map.getGrid()[5][15].setAreaEffect(new VolcanicVent());

        //move unit and heal him
        Unit explorerHeal = new Explorer(map.getGrid()[6][18], player2);
        explorerHeal.getUnitStats().setHealth(20);
        player2.addUnit(explorerHeal);
        player2.addStructure(new Base(this.map.getGrid()[5][18], player2));

        //move unit infinitely




    }

    public void enterGameLoop() {


        //      start the game
        //      while the game is not over
        //             let the current player issue commands
        //             once the current player has finished their turn
        //             execute existing commands
        //             give the other player control s
        //

 /*       while (!players[0].isDefeated() && !players[1].isDefeated()) {
            executeQueues();
            currentPlayer.takeTurn();
            nextTurn();
        }

        endGame();
*/
    }

    // This function will loop through all armies and structures and execute next command in queue
    // TODO: MAKE THIS PRIVATE
    public void executeQueues() {

        // loop through list of armies and execute next command
        for(int i = 0; i < currentPlayer.getArmies().size(); i++) {
            currentPlayer.getArmies().get(i).decodeNextInstruction(map);
        }

        // loop through list of structures and execute next command
        for(int i = 0; i < currentPlayer.getStructures().size(); i++) {
            ((Base) currentPlayer.getStructures().get(i)).decodeNextInstruction(map);
        }

        // TODO: UPDATE MAP?

    }

    //Switches the turn to the next player
    public void nextTurn() {
        executeQueues();
        currentPlayer.takeTurn();

        if(currentPlayer.isDefeated() == true){
            endGame();
        }

        if(currentPlayer == players[0])
            currentPlayer = players[1];
        else 
            currentPlayer = players[0];
        
        turn = ++turn % 2;
    }

    //Get the current player
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    //Get the current turn
    public int getTurn() {
        return turn;
    }

    //Get the map
    public Map getMap() {
        return map;
    }

    //Get list of players
    public Player[] getPlayers() {
        return players;
    }

    public void setTurn(int num) {
        turn = num;
    }

    public void setCurrentPlayer(int num) {
        if (num > players.length-1) {
            System.out.println("ERROR: Out of bounds request for setCurrentPlayer()");
            return;
        }
        currentPlayer = players[num];
    }


    public void endGame() {

        System.out.println("GAME OVER!!!!" );

        /*   --TODO--
        Display a game over splash screen and exit the program, gunna wait until the GUI is integrated
        to be able to do this. */

        System.exit(0);

    }




    public Tile moveUnit(Player player,Tile current, String command,int commandIndex){
        Tile next = new Tile();
        if (command == "east") {
            next = map.getTile(current.getxCoordinate(), current.getyCoordinate() + 1);
        } else if (command == "west") {
            next = map.getTile(current.getxCoordinate(), current.getyCoordinate() - 1);
        } else if (command == "north") {
            next = map.getTile(current.getxCoordinate() + 1, current.getyCoordinate());
        } else if (command == "south") {
            next = map.getTile(current.getxCoordinate() - 1, current.getyCoordinate());
        }
        return next;

    }

    }
