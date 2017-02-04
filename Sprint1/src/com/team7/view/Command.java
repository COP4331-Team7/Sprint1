package com.team7.view;

import com.team7.controller.MainScreenController;
import com.team7.objects.Player;
import com.team7.objects.unit.Unit;
import com.team7.objects.unit.combatUnit.MeleeUnit;
import com.team7.objects.unit.combatUnit.RangedUnit;
import com.team7.objects.unit.nonCombatUnit.Colonist;
import com.team7.objects.unit.nonCombatUnit.Explorer;
import javafx.scene.input.KeyCombination;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.awt.Robot;

import static javax.swing.SwingUtilities.convertPointFromScreen;

public class Command extends JPanel implements KeyListener {

    private  Player currentPlayer = null;
    private  MainViewInfo statusInfo;
    private  Screen screen = null;
    private MainScreenController msc;

    private JButton executeCommandButton = null;
    private JButton endTurnButton = null;
    Robot robot;

    int commandOrder = 0;

    JLabel modeLabel;
    JLabel typeLabel;
    JLabel typeInstanceLabel;
    JLabel commandLabel;

    boolean isTrackingPath = false;

    private final static String[] armyCommands = {          "attack [direction] applies to the battle-group",
                                                            "defend [direction] applies to the battle-group",
                                                            "move [changes the rally-point]",
                                                            "wait  [applies to the battle-group]",
                                                            "disband  [the army ceases to exist, all units go to standby]",
                                                            "decommission [destroy all units in the army]",
                                                            "power down [applies to the battle-group]",
                                                            "power up [applies to the battle-group]",
                                                            "cancel queued orders [immediate effect]"};

    private final static String[] structureCommands = {"attack [direction]",
                                                            "make[unit type]",
                                                            "defend [direction]",
                                                            "heal/repair unit",
                                                            "decommission",
                                                            "power down",
                                                            "power up",
                                                            "cancel queued orders [immediate effect]"};

    private final static String[] unitCommands = {    "REINFORCE",
                                                    "DECOMMISSION",
                                                     "POWER DOWN",
                                                     "POWER UP",
                                                      "MOVE"};


   // private final static String[] types = { "STRUCTURE", "UNIT", "ARMY" };

    private final static String[] structureTypes = { "BASE" };

    private final static String[] unitTypes = { "EXPLORER", "COLONIST", "RANGED UNIT", "MELEE UNIT" };


    private final static String[] modes = {  "RALLY POINT",
                                               "STRUCTURE",
                                                    "UNIT",
                                                    "ARMY"  };


    private final static String[] armySubTypes = {"ENTIRE ARMY", "BATTLE GROUP", "REINFORCEMENTS"};

    private int currMode = -1;
    private int currType = -1;
    private int currTypeInstance = -1;
    private int currCommand = -1;

    private static final int CONTROL_KEY_CODE = 4;
    private static final int LEFT_KEY_CODE = 37;
    private static final int UP_KEY_CODE = 38;
    private static final int RIGHT_KEY_CODE = 39;
    private static final int DOWN_KEY_CODE = 40;

    public Command() throws AWTException {

        robot = new Robot();

        JPanel commandSelectPanel = new JPanel();
        commandSelectPanel.setLayout(new GridLayout(0,1));

        modeLabel = new JLabel("MODE (CONTROL + \u2191 / \u2193): "); //up / down arrow
        typeLabel = new JLabel("TYPE (CONTROL + \u2190 / \u2192): "); //left / right arrow
        typeInstanceLabel = new JLabel("TYPE INSTANCE (\u2190 / \u2192): "); //left / right arrow
        commandLabel = new JLabel("COMMAND (\u2191 / \u2193): "); //up / down arrow

        JLabel my_static_label = new JLabel("CONSTRUCT COMMAND BELOW");
        my_static_label.setFont(new Font("Serif", Font.BOLD, 22));

        commandSelectPanel.add(my_static_label);
        commandSelectPanel.add(modeLabel);
        commandSelectPanel.add(typeLabel);
        commandSelectPanel.add(typeInstanceLabel);
        commandSelectPanel.add(commandLabel);

        executeCommandButton = new JButton("EXECUTE COMMAND");
        endTurnButton = new JButton("END TURN");
        commandSelectPanel.add( executeCommandButton );
        commandSelectPanel.add( endTurnButton );

        this.add( commandSelectPanel, BorderLayout.SOUTH );

        addKeyListener(this);
    }

    public void setController( MainScreenController msc ) {
        this.msc = msc;
        this.statusInfo = msc.getStatusInfo();
    }

    public String extractCommand() {
        StringBuilder sb = new StringBuilder();

        sb.append(" ");
        //sb.append( modeLabel.getText().substring(modeLabel.getText().lastIndexOf(":") + 1) );
        sb.append( typeLabel.getText().substring(typeLabel.getText().lastIndexOf(":") + 1) );
        sb.append( typeInstanceLabel.getText().substring(typeInstanceLabel.getText().lastIndexOf(":") + 1) );
        sb.append( commandLabel.getText().substring(commandLabel.getText().lastIndexOf(":") + 1) );

        String command_string = sb.toString( );
        System.out.println("commyString = " + command_string);
        return command_string;
    }


    // update the text displaying the currently selected command
    private void updateCommand() {

        modeLabel.setText("MODE (CONTROL + \u2191 / \u2193): " + ((currMode != -1)?modes[currMode]:"") ); //up / down arrow

        // type based off of mode
        if(currMode == 1)
            typeLabel.setText("TYPE (CONTROL + \u2190 / \u2192): " + ((currType != -1)?structureTypes[currType]:"")); //left / right arrow
        else if (currMode == 2)
            typeLabel.setText("TYPE (CONTROL + \u2190 / \u2192): " + ((currType != -1)?unitTypes[currType]:"")); //left / right arrow
        else if (currMode == 3)
            typeLabel.setText("TYPE (CONTROL + \u2190 / \u2192): " + ((currType != -1)?armySubTypes[currType]:"")); //left / right arrow
        else
            typeLabel.setText("TYPE (CONTROL + \u2190 / \u2192): " ); //left / right arrow

        if(currMode == 1)
            commandLabel.setText("COMMAND (\u2191 / \u2193): " + ((currCommand != -1)?structureCommands[currCommand]:"")); //up / down arrow
        else if (currMode == 2)
            commandLabel.setText("COMMAND (\u2191 / \u2193): " + ((currCommand != -1)?unitCommands[currCommand]:"")); //up / down arrow
        else if (currMode == 3)
            commandLabel.setText("COMMAND (\u2191 / \u2193): " + ((currCommand != -1)?armyCommands[currCommand]:"")); //up / down arrow
        else
            commandLabel.setText("COMMAND (\u2191 / \u2193): ");

        if(currTypeInstance == -1) {
            typeInstanceLabel.setText("TYPE INSTANCE (\u2190 / \u2192): ");
            statusInfo.clearStats();

        }
        else if(currMode == 2 && currType == 0) { // get list of player's Colonist instances
            ArrayList<Unit> units = (ArrayList<Unit>) currentPlayer.getUnits();
            ArrayList<Explorer> explorers = new ArrayList<Explorer>();
            if( !units.isEmpty() ) {    // if there are units on this tile
                for(int n = 0; n < units.size(); n++) {
                    if( units.get(n) instanceof Explorer) {
                        explorers.add((Explorer) units.get(n));
                    }
                }
                typeInstanceLabel.setText("TYPE INSTANCE (\u2190 / \u2192): " + ((currTypeInstance != -1)?explorers.get(currTypeInstance).getId():""));
                statusInfo.setUnit( explorers.get(currTypeInstance) );
            }
        }
        else if(currMode == 2 && currType == 1) { // get list of player's Colonist instances
            ArrayList<Unit> units = (ArrayList<Unit>) currentPlayer.getUnits();
            ArrayList<Colonist> colonists = new ArrayList<Colonist>();
            if( !units.isEmpty() ) {    // if there are units on this tile
                for(int n = 0; n < units.size(); n++) {
                    if( units.get(n) instanceof Colonist) {
                        colonists.add((Colonist) units.get(n));
                    }
                }
                typeInstanceLabel.setText("TYPE INSTANCE (\u2190 / \u2192): " + ((currTypeInstance != -1)?colonists.get(currTypeInstance).getId():""));
                statusInfo.setUnit( colonists.get(currTypeInstance) );
            }
        }
        else if(currMode == 2 && currType == 2) { // get list of player's Ranged Unit instances
            ArrayList<Unit> units = (ArrayList<Unit>) currentPlayer.getUnits();
            ArrayList<RangedUnit> rangeUnits = new ArrayList<RangedUnit>();
            if (!units.isEmpty()) {    // if there are units on this tile
                for (int n = 0; n < units.size(); n++) {
                    if (units.get(n) instanceof RangedUnit) {
                        rangeUnits.add((RangedUnit) units.get(n));
                    }
                }
            }
            typeInstanceLabel.setText("TYPE INSTANCE (\u2190 / \u2192): " + ((currTypeInstance != -1)?rangeUnits.get(currTypeInstance).getId():""));
            statusInfo.setUnit( rangeUnits.get(currTypeInstance) );
        }
        else if(currMode == 2 && currType == 3) { // get list of player's Melee Unit instances
            ArrayList<Unit> units = (ArrayList<Unit>) currentPlayer.getUnits();
            ArrayList<MeleeUnit> meleeUnits = new ArrayList<MeleeUnit>();
            if (!units.isEmpty()) {    // if there are units on this tile
                for (int n = 0; n < units.size(); n++) {
                    if (units.get(n) instanceof MeleeUnit) {
                        meleeUnits.add((MeleeUnit) units.get(n));
                    }
                }
            }
            typeInstanceLabel.setText("TYPE INSTANCE (\u2190 / \u2192): " + ((currTypeInstance != -1)?meleeUnits.get(currTypeInstance).getId():""));
            statusInfo.setUnit( meleeUnits.get(currTypeInstance) );
        }

    }

    public void keyTyped(KeyEvent e)    {}
    public void keyReleased(KeyEvent e) {}
    public void keyPressed(KeyEvent e)  {

        if(e.getKeyCode() == UP_KEY_CODE && e.getModifiers() == CONTROL_KEY_CODE ) {

            currMode = ++currMode % modes.length;

            currType = -1;      // when the user changes MODE, reset the currently selected TYPE
            currTypeInstance = -1;
        }
        else if(e.getKeyCode() == DOWN_KEY_CODE && e.getModifiers() == CONTROL_KEY_CODE ) {

            if (currMode > 0) currMode--;
            else currMode = modes.length - 1;

            currType = -1;      // when the user changes MODE, reset the currently selected TYPE
            currTypeInstance = -1;
        }
        else if(e.getKeyCode() == LEFT_KEY_CODE && e.getModifiers() == CONTROL_KEY_CODE ) {

            if (currType > 0) currType--;
            else currType = getNumTypes( currMode ) - 1;

            currCommand = -1;   // when the user changes TYPE, reset the currently selected COMMAND
            currTypeInstance = -1;
        }
        else if(e.getKeyCode() == RIGHT_KEY_CODE && e.getModifiers() == CONTROL_KEY_CODE ) {

            if( getNumTypes( currMode ) != 0)
                currType = ++currType % getNumTypes( currMode );

            currCommand = -1;   // when the user changes TYPE, reset the currently selected COMMAND
            currTypeInstance = -1;
        }
        else if(e.getKeyCode() == UP_KEY_CODE) {

            if( getNumCommands( currMode ) != 0)
                currCommand = ++currCommand % getNumCommands( currMode );

        }
        else if(e.getKeyCode() == DOWN_KEY_CODE) {

            if (currCommand > 0) currCommand--;
            else currCommand = getNumCommands( currMode ) - 1;

        }
        else if(e.getKeyCode() == RIGHT_KEY_CODE) {

            if( getNumInstances( currMode, currType ) != 0)
                currTypeInstance = ++currTypeInstance % getNumInstances( currMode, currType );

            isTrackingPath = false;
        }
        else if(e.getKeyCode() == LEFT_KEY_CODE) {

            if (currTypeInstance > 0) currTypeInstance--;
            else currTypeInstance = getNumInstances( currMode, currType ) - 1;

            isTrackingPath = false;
        }

        updateCommand();

        // if RALLY POINT or UNIT mode, and command is MOVE
        if( (currMode == 0 || currMode == 2) && currCommand == 4) {

            if(isTrackingPath == false) {
                moveCursorToSelectedUnit();
                msc.moveMode( getCurrSelectedUnit() );
            }

            isTrackingPath = true;

            switch( e.getKeyChar() ) {
                case '1':
                    if(msc.sendCommand( '1' )) { // SW
                        moveMouse(-67, 67);
                    }
                    break;
                case '2':
                    if(msc.sendCommand( '2' )) { // S
                        moveMouse(0, 67);
                    }
                    break;
                case '3':
                    if(msc.sendCommand( '3' )) { // SE
                        moveMouse(67, 67);
                    }
                    break;
                case '4':
                    if(msc.sendCommand( '4' )) { // W
                        moveMouse(-67, 0);
                    }
                    break;
                case '5':
                    commandOrder = 0;
                    msc.updateModel();
                    clearCommand();
                    break;
                case '6':
                    if(msc.sendCommand( '6' )) { // E
                        moveMouse(67, 0);
                    }
                    break;
                case '7':
                    if(msc.sendCommand( '7' )) { // NW
                        moveMouse(-67, -67);
                    }
                    break;
                case '8':
                    if(msc.sendCommand( '8' )) { // N
                        moveMouse(0, -67);
                    }
                    break;
                case '9':
                    if(msc.sendCommand( '9' )){
                        moveMouse(67, -67); // NE
                    }

                case 'z':
                    if(msc.sendCommand( '1' )) { // SW
                        moveMouse(-67, 67);
                    }
                    break;
                case 'x':
                    if(msc.sendCommand( '2' )) { // S
                        moveMouse(0, 67);
                    }
                    break;
                case 'c':
                    if(msc.sendCommand( '3' )) { // SE
                        moveMouse(67, 67);
                    }
                    break;
                case 'a':
                    if(msc.sendCommand( '4' )) { // W
                        moveMouse(-67, 0);
                    }
                    break;
                case 'd':
                    if(msc.sendCommand( '6' )) { // E
                        moveMouse(67, 0);
                    }
                    break;
                case 'q':
                    if(msc.sendCommand( '7' )) { // NW
                        moveMouse(-67, -67);
                    }
                    break;
                case 'w':
                    if(msc.sendCommand( '8' )) { // N
                        moveMouse(0, -67);
                    }
                    break;
                case 'e':
                    if(msc.sendCommand( '9' )){
                        moveMouse(67, -67); // NE
                    }

                    break;
                default:
            }


        }

    }


    private int getNumTypes(int currMode) {        // get # of options the current MODE has
            int size = 0;
            if(currMode == 1)
                size = structureTypes.length;
            else if (currMode == 2)
                size = unitTypes.length;
            else if (currMode == 3)
                size = armySubTypes.length;
            return size;
    }

    private int getNumCommands(int currType) {      // get # of options the current TYPE has
            int size = 0;
            if(currType == 1)
                size = structureCommands.length;
            else if (currType == 2)
                size = unitCommands.length;
            else if (currType == 3)
                size = armyCommands.length;
            return size;
    }

    private int getNumInstances(int currMode, int currType) {      // get # instances of a given type

        if(currMode == 2 && currType == 0) { // get list of player's Explorer instances
            ArrayList<Unit> units = (ArrayList<Unit>) currentPlayer.getUnits();
            ArrayList<Explorer> explorers = new ArrayList<Explorer>();
            if( !units.isEmpty() ) {    // if there are units on this tile
                for(int n = 0; n < units.size(); n++) {
                    if( units.get(n) instanceof Explorer) {
                        explorers.add((Explorer) units.get(n));
                    }
                }
            }
            return explorers.size();
        }
        else if(currMode == 2 && currType == 1) { // get list of player's Colonist instances
            ArrayList<Unit> units = (ArrayList<Unit>) currentPlayer.getUnits();
            ArrayList<Colonist> colonists = new ArrayList<Colonist>();
            if (!units.isEmpty()) {    // if there are units on this tile
                for (int n = 0; n < units.size(); n++) {
                    if (units.get(n) instanceof Colonist) {
                        colonists.add((Colonist) units.get(n));
                    }
                }
            }
            return colonists.size();
        }
        else if(currMode == 2 && currType == 2) { // get list of player's Ranged Unit instances
            ArrayList<Unit> units = (ArrayList<Unit>) currentPlayer.getUnits();
            ArrayList<RangedUnit> rangeUnits = new ArrayList<RangedUnit>();
            if (!units.isEmpty()) {    // if there are units on this tile
                for (int n = 0; n < units.size(); n++) {
                    if (units.get(n) instanceof RangedUnit) {
                        rangeUnits.add((RangedUnit) units.get(n));
                    }
                }
            }
            return rangeUnits.size();
        }
        else if(currMode == 2 && currType == 3) { // get list of player's Melee Unit instances
            ArrayList<Unit> units = (ArrayList<Unit>) currentPlayer.getUnits();
            ArrayList<MeleeUnit> meleeUnits = new ArrayList<MeleeUnit>();
            if (!units.isEmpty()) {    // if there are units on this tile
                for (int n = 0; n < units.size(); n++) {
                    if (units.get(n) instanceof MeleeUnit) {
                        meleeUnits.add((MeleeUnit) units.get(n));
                    }
                }
            }
            return meleeUnits.size();
        }
        else
            return 0;
    }

    public void clearCommand() {
        currMode = -1;
        currType = -1;
        currTypeInstance = -1;
        currCommand = -1;
        isTrackingPath = false;
        updateCommand();
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public void setCurrentPlayer( Player player ) {
        this.currentPlayer = player;
    }

    public JButton getEndTurnButton() {
        return endTurnButton;
    }
    public JButton getExecuteCommandButton() {
        return executeCommandButton;
    }
    public void queueCommand() {
        String commands = extractCommand();
        System.out.println("commands  = "  + commands );

//        String[] parts = command.split(" ");
//        System.out.println("type = "  + parts[0] );
//        System.out.println("instance = "  + parts[1] );
    }

    public Unit getCurrSelectedUnit() {

        Unit selection = null;

        if(currMode == 2 && currType == 0) { // get list of player's Explorer instances
            ArrayList<Unit> units = (ArrayList<Unit>) currentPlayer.getUnits();
            ArrayList<Explorer> explorers = new ArrayList<Explorer>();
            if( !units.isEmpty() ) {    // if there are units on this tile
                for(int n = 0; n < units.size(); n++) {
                    if( units.get(n) instanceof Explorer) {
                        explorers.add((Explorer) units.get(n));
                    }
                }
                selection = explorers.get( currTypeInstance );
            }
        }
        else if(currMode == 2 && currType == 1) { // get list of player's Colonist instances
            ArrayList<Unit> units = (ArrayList<Unit>) currentPlayer.getUnits();
            ArrayList<Colonist> colonists = new ArrayList<Colonist>();
            if( !units.isEmpty() ) {    // if there are units on this tile
                for(int n = 0; n < units.size(); n++) {
                    if( units.get(n) instanceof Colonist) {
                        colonists.add((Colonist) units.get(n));
                    }
                }
                selection = colonists.get( currTypeInstance );
            }
        }

        return selection;
    }

    public void moveMouse(int x, int y) {

        final Point pTemp = new Point( (int)MouseInfo.getPointerInfo().getLocation().getX(), (int)MouseInfo.getPointerInfo().getLocation().getY());

        convertPointFromScreen(pTemp, screen);

        System.out.println("x = " + pTemp.getX() + " y = " + pTemp.getY() );
        if(pTemp.getX() - 67 <= 0) {
            robot.mouseMove((int)MouseInfo.getPointerInfo().getLocation().getX() + 67, (int)MouseInfo.getPointerInfo().getLocation().getY());
            msc.getMainView().zoomToDestination( (msc.getMainView().getXdest() - 1), msc.getMainView().getYdest() );
            return;
        }
        if(pTemp.getX() + 67 >= 737 - 67) {
            robot.mouseMove((int)MouseInfo.getPointerInfo().getLocation().getX() - 67, (int)MouseInfo.getPointerInfo().getLocation().getY());
            msc.getMainView().zoomToDestination( (msc.getMainView().getXdest() + 1), msc.getMainView().getYdest() );
            return;
        }
        if(pTemp.getY() + 67 >= 536 ) {
            robot.mouseMove((int)MouseInfo.getPointerInfo().getLocation().getX() , (int)MouseInfo.getPointerInfo().getLocation().getY() - 67);
            msc.getMainView().zoomToDestination( (msc.getMainView().getXdest()), (msc.getMainView().getYdest() + 1) );
            return;
        }
        if(pTemp.getY() - 67 <= 67 ) {
            robot.mouseMove((int)MouseInfo.getPointerInfo().getLocation().getX() , (int)MouseInfo.getPointerInfo().getLocation().getY() + 67);
            msc.getMainView().zoomToDestination( (msc.getMainView().getXdest() ), (msc.getMainView().getYdest() - 1) );
            return;
        }




        final Point p = new Point( (int)MouseInfo.getPointerInfo().getLocation().getX(), (int)MouseInfo.getPointerInfo().getLocation().getY());

        int x_loc2 = (int)p.getX(); // where it is
        int y_loc2 = (int)p.getY();

        double dx = ( x ) / ((double) 30);
        double dy = ( y ) / ((double) 30);

        for(int i = 0; i <= 30; i++) {
            robot.mouseMove( (int)(x_loc2 + dx * i), (int)(y_loc2 + dy * i));
            try{ Thread.sleep(10); }
            catch(Exception e) {}
        }



    }

    public void moveCursorToSelectedUnit() {
        Unit selection = getCurrSelectedUnit();
        if(selection != null) {

            msc.getMainView().getXdest();
            msc.getMainView().getYdest();

          //  System.out.println("focus: x = " + (msc.getMainView().getXdest()) + "y = " + msc.getMainView().getYdest());
          //  System.out.println("before: x = " + (selection.getLocation().getxCoordinate()) + "y = " + (selection.getLocation().getyCoordinate()));

            selection.getLocation().getxCoordinate();
            selection.getLocation().getyCoordinate();
        }

        int currTileX = 11/2, currTileY = 7/2;
        if(selection != null){
            currTileX = selection.getLocation().getxCoordinate() - msc.getMainView().getXdest() + 1;
            currTileY = selection.getLocation().getyCoordinate() - msc.getMainView().getYdest() + 2;
          //  System.out.println("MOVE: x = " + currTileX + "y = " + currTileY);
        }



        final Point p = new Point( (int)MouseInfo.getPointerInfo().getLocation().getX(), (int)MouseInfo.getPointerInfo().getLocation().getY());

        int x_loc2 = (int)p.getX(); // where it is
        int y_loc2 = (int)p.getY();

        // System.out.println("before: x = " + p.getX() + "y = " + p.getY());
        convertPointFromScreen(p, screen);

        int x_loc = (int)p.getX();   // where it should be
        int y_loc = (int)p.getY();


        double dx = ( (x_loc2 + (currTileX*67 - x_loc)) - x_loc2 ) / ((double) 30);
        double dy = ( (y_loc2 + (currTileY*67 - y_loc)) - y_loc2 ) / ((double) 30);
        // System.out.println("after: x = " + x_loc + "y = " + y_loc);

        // robot.mouseMove( 400, 340);

        try{ Thread.sleep(50); }
        catch(Exception e) {}
        for(int i = 0; i <= 30; i++) {
            robot.mouseMove( (int)(x_loc2 + dx * i), (int)(y_loc2 + dy * i));
            try{ Thread.sleep(10); }
            catch(Exception e) {}
        }
    }

}
