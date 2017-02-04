package com.team7.view;

import com.team7.objects.Player;
import com.team7.objects.unit.Unit;
import com.team7.objects.unit.combatUnit.MeleeUnit;
import com.team7.objects.unit.combatUnit.RangedUnit;
import com.team7.objects.unit.nonCombatUnit.Colonist;
import com.team7.objects.unit.nonCombatUnit.Explorer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Command extends JPanel implements KeyListener {

    private Player currentPlayer = null;
    private  MainViewInfo statusInfo;

    private JButton executeCommandButton = null;
    private JButton endTurnButton = null;

    JLabel modeLabel;
    JLabel typeLabel;
    JLabel typeInstanceLabel;
    JLabel commandLabel;

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

    private final static String[] unitCommands = {"reinforce [army#]",
                                                    "decommission [destroy—immediate, power up not required]",
                                                     "power down",
                                                     "power up" };


    private final static String[] types = { "structure", "unit", "army" };

    private final static String[] structureTypes = { "Base" };

    private final static String[] unitTypes = { "Explorer", "Colonist", "Ranged Unit", "Melee Unit" };


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

    public Command(MainViewInfo statusInfo) {

        this.statusInfo = statusInfo;

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

    private String extractCommand() {
        StringBuilder sb = new StringBuilder();
        sb.append( modeLabel.getText() );
        sb.append( typeLabel.getText() );
        sb.append( typeInstanceLabel.getText() );
        sb.append( commandLabel.getText() );

        String command_string = sb.toString( );
        System.out.println(command_string);
        return  command_string;
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

        if(currType == 0)
            commandLabel.setText("COMMAND (\u2191 / \u2193): " + ((currCommand != -1)?structureCommands[currCommand]:"")); //up / down arrow
        else if (currType == 1)
            commandLabel.setText("COMMAND (\u2191 / \u2193): " + ((currCommand != -1)?unitCommands[currCommand]:"")); //up / down arrow
        else if (currType == 2)
            commandLabel.setText("COMMAND (\u2191 / \u2193): " + ((currCommand != -1)?armyCommands[currCommand]:"")); //up / down arrow

        if(currTypeInstance == -1) {
            typeInstanceLabel.setText("TYPE INSTANCE (\u2190 / \u2192): ");
            statusInfo.clearStats();

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

            if( getNumCommands( currType ) != 0)
                currCommand = ++currCommand % getNumCommands( currType );

        }
        else if(e.getKeyCode() == DOWN_KEY_CODE) {

            if (currCommand > 0) currCommand--;
            else currCommand = getNumCommands( currType ) - 1;

        }
        else if(e.getKeyCode() == RIGHT_KEY_CODE) {

            if( getNumInstances( currMode, currType ) != 0)
                currTypeInstance = ++currTypeInstance % getNumInstances( currMode, currType );

        }
        else if(e.getKeyCode() == LEFT_KEY_CODE) {

            if (currTypeInstance > 0) currTypeInstance--;
            else currTypeInstance = getNumInstances( currMode, currType ) - 1;

        }

        updateCommand();
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
            if(currType == 0)
                size = structureCommands.length;
            else if (currType == 1)
                size = unitCommands.length;
            else if (currType == 2)
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

    public void setCurrentPlayer( Player player ) {
        this.currentPlayer = player;
    }

    public JButton getEndTurnButton() {
        return endTurnButton;
    }

}
