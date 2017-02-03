package com.team7.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Command extends JPanel implements KeyListener {

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

    public Command() {

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
        commandSelectPanel.add(new JLabel());
        commandSelectPanel.add(new JLabel());
        this.add( commandSelectPanel, BorderLayout.SOUTH );

        addKeyListener(this);
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

    }


    public void keyTyped(KeyEvent e)    {}
    public void keyReleased(KeyEvent e) {}
    public void keyPressed(KeyEvent e)  {

        if(e.getKeyCode() == UP_KEY_CODE && e.getModifiers() == CONTROL_KEY_CODE ) {

            currMode = ++currMode % modes.length;

            currType = -1;      // when the user changes MODE, reset the currently selected TYPE
        }
        else if(e.getKeyCode() == DOWN_KEY_CODE && e.getModifiers() == CONTROL_KEY_CODE ) {

            if (currMode > 0) currMode--;
            else currMode = modes.length - 1;

            currType = -1;      // when the user changes MODE, reset the currently selected TYPE
        }
        else if(e.getKeyCode() == LEFT_KEY_CODE && e.getModifiers() == CONTROL_KEY_CODE ) {

            if (currType > 0) currType--;
            else currType = getNumTypes( currMode ) - 1;

            currCommand = -1;   // when the user changes TYPE, reset the currently selected COMMAND
        }
        else if(e.getKeyCode() == RIGHT_KEY_CODE && e.getModifiers() == CONTROL_KEY_CODE ) {

            if( getNumTypes( currMode ) != 0)
                currType = ++currType % getNumTypes( currMode );

            currCommand = -1;   // when the user changes TYPE, reset the currently selected COMMAND
        }
        else if(e.getKeyCode() == UP_KEY_CODE) {

            if( getNumCommands( currType ) != 0)
                currCommand = ++currCommand % getNumCommands( currType );

        }
        else if(e.getKeyCode() == DOWN_KEY_CODE) {

            if (currCommand > 0) currCommand--;
            else currCommand = getNumCommands( currType ) - 1;

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



}
