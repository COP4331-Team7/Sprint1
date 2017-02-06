package com.team7.view;

import com.team7.objects.Player;
import com.team7.objects.Tile;
import com.team7.objects.Command;
import com.team7.objects.unit.Unit;
import com.team7.objects.unit.UnitStats;
import com.team7.objects.unit.nonCombatUnit.Colonist;
import com.team7.objects.unit.nonCombatUnit.Explorer;
import com.team7.objects.unit.nonCombatUnit.NonCombatUnit;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;

public class UnitScreen extends JPanel {

    private ScreenSelectButtons screenSelectBtns = null;
    private JScrollPane jScrollPane = null;
    private JScrollPane queueScrollPane;
    private Unit[] units;
    private ArrayList<Unit> unitsInSelectedArmy = new ArrayList<Unit>();
    private JList unitList = new JList();
    private JList<String> queue = new JList<>();
    private JList<String> armyList = new JList<String>();
    private JList unitsInArmyList = new JList();
    private JTextArea textArea = new JTextArea();
    private JPanel unitOverviewComponents = new JPanel();
    private int selectedArmy;

    //List Models
    DefaultListModel<String> armyModel = new DefaultListModel<String>();
    DefaultListModel<String> queueModel = new DefaultListModel<String>();

    //Buttons to be accessed by UnitScreenController
    private JButton addArmy = new JButton("CREATE ARMY");
    private JButton decomissionArmy = new JButton("DISBAND ARMY");
    private JButton addUnit = new JButton("ADD UNIT");
    private JButton removeUnit = new JButton("REMOVE UNIT");
    private JButton cancelCommand = new JButton("CANCEL COMMAND");
    private JButton moveOrderUp = new JButton("⟰");
    private JButton moveOrderDown = new JButton("⟱");

    public UnitScreen() {

        this.setLayout(new BorderLayout());

        screenSelectBtns = new ScreenSelectButtons();
        addElements(this);

        this.add(screenSelectBtns, BorderLayout.NORTH);
        repaint();
    }

    public ScreenSelectButtons getScreenSelectButtons() {
        return screenSelectBtns;
    }

    private void addElements(Container pane) {
        JPanel queuePane = new JPanel(new BorderLayout());

        //Populates the unit list with the current Player's units
        unitList.setModel(new AbstractListModel() {
            //Testing artifacts
            Tile t = new Tile();
            Player p = new Player();
            Unit[] test = {new Colonist(t,p), new Colonist(t,p), new Colonist(t,p), new Explorer(t, p), new Colonist(t,p), new Explorer(t, p),
                    new Colonist(t,p), new Explorer(t, p),
                    new Colonist(t,p), new Explorer(t, p),
                    new Colonist(t,p), new Explorer(t, p),
                    new Colonist(t,p), new Explorer(t, p),
                    new Colonist(t,p), new Explorer(t, p),
                    new Colonist(t,p), new Explorer(t, p),
                    new Colonist(t,p), new Explorer(t, p),
                    new Colonist(t,p), new Explorer(t, p),
                    new Colonist(t,p), new Explorer(t, p),
                    new Colonist(t,p)};

            @Override
            public int getSize() {
                return units.length;
            }

            @Override
            public String getElementAt(int index) {
                return units[index].getType() + " " + units[index].getId();
            }
        });
        queue.setModel(queueModel);

        //Configure init model for unitsInArmyList
        unitsInArmyList.setModel(new AbstractListModel() {
            @Override
            public int getSize() {
                return unitsInSelectedArmy.size();
            }

            @Override
            public Object getElementAt(int index) {
                return unitsInSelectedArmy.get(index);
            }
        });
        //Listens for any changes in selected unit
        unitList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                changeListVal();
            }
        });

        //Create Command Queue
        queueScrollPane = new JScrollPane(queue);
        queueScrollPane.setMaximumSize(new Dimension(200,400));
        queueScrollPane.setPreferredSize(new Dimension(200, 200));
        queuePane.setMaximumSize(new Dimension(200,400));
        JLabel queueLabel = new JLabel("Command Queue", SwingConstants.CENTER);
        queuePane.add(queueLabel, BorderLayout.NORTH);
        queuePane.add(queueScrollPane);
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.add(moveOrderDown, BorderLayout.LINE_END);
        buttonPanel.add(moveOrderUp, BorderLayout.LINE_START);
        buttonPanel.add(cancelCommand, BorderLayout.CENTER);
        queuePane.add(buttonPanel, BorderLayout.SOUTH);
        queue.setVisibleRowCount(10);

        //Format text box
        textArea.setColumns(20);
        textArea.setRows(13);
        textArea.setEditable(false);
        textArea.setPreferredSize(new Dimension(250, 400));
        JPanel textBox = new JPanel(new BorderLayout());
        textBox.setPreferredSize(new Dimension(250,400));
        JLabel statsLabel = new JLabel("Statistics" ,SwingConstants.CENTER);
        textBox.add(statsLabel, BorderLayout.NORTH);
        textBox.add(textArea);

        //Format unit list
        jScrollPane = new JScrollPane(unitList);
        jScrollPane.setPreferredSize(new Dimension(200, 200));
        JPanel scrollBox = new JPanel(new BorderLayout());
        scrollBox.setMaximumSize(new Dimension(200,400));
        JLabel unitSelectLabel = new JLabel("Unit List", SwingConstants.CENTER);
        scrollBox.add(unitSelectLabel, BorderLayout.NORTH);
        scrollBox.add(jScrollPane);
        unitList.setVisibleRowCount(10);

        //Format Army Buttons
        JPanel armyButtons = new JPanel(new GridLayout(1,2));
        armyButtons.add(addArmy);
        armyButtons.add(decomissionArmy);

        //Format Army Select Box
        armyList.setModel(armyModel);
        JPanel armyPanel = new JPanel(new BorderLayout());
        JLabel armySelectLabel = new JLabel("Army Select", SwingConstants.CENTER);
        armyPanel.add(armySelectLabel, BorderLayout.NORTH);
        JScrollPane scrollArmyPane = new JScrollPane(armyList);
        armyPanel.add(scrollArmyPane, BorderLayout.CENTER);
        armyPanel.add(armyButtons, BorderLayout.SOUTH);

        //Format List of units for each Army
        JPanel uAPanel = new JPanel(new BorderLayout());
        JLabel uALabel = new JLabel("Units in army", SwingConstants.CENTER);
        uAPanel.add(uALabel, BorderLayout.NORTH);
        JScrollPane uAScrollPane = new JScrollPane(unitsInArmyList);
        uAPanel.add(uAScrollPane, BorderLayout.CENTER);
        JPanel unitButtons = new JPanel(new GridLayout(1, 2));
        unitButtons.add(addUnit);
        unitButtons.add(removeUnit);
        uAPanel.add(unitButtons, BorderLayout.SOUTH);


        JLabel titleLabel = new JLabel("Unit Overview", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 22));

        //Format and add all components to the data pane
        unitOverviewComponents.setLayout(new GridLayout(2,3));
        unitOverviewComponents.add(scrollBox);
        unitOverviewComponents.add(titleLabel);
        unitOverviewComponents.add(textBox);
        unitOverviewComponents.add(armyPanel);
        unitOverviewComponents.add(queuePane);
        unitOverviewComponents.add(uAPanel);

        pane.add(unitOverviewComponents);
        repaint();
    }

    public void changeListVal() {
        String s = (String)unitList.getSelectedValue();
        String stats = getStats(s);
        textArea.setText(stats);
        setQueueBox(s);
    }

    //--TODO-- Missing functionallity in unit to complete this function
    private void setQueueBox(String unitString) {
        Unit selected = null;
        for (Unit u: units) {
            if (unitString.equals(u.getType() + " " + u.getId())) {
                selected = u;
            }
        }

        if (selected instanceof NonCombatUnit) {
            queueModel.clear();
        } else {
            queueModel.clear();
            //Queue<String> q = selected.getArmy().getCommands().getData();
//            if (q != null) {
//                String[] commands = (String[]) q.toArray();
//                for (String s : commands) {
//                    queueModel.addElement(s);
//                }
//            } else {
//                System.out.println("Command queue for unit " + unitString + " is null");
//            }
        }
    }


    //Finds Unit stats and Unit queue, otherwise display error message
    private String getStats(String unitString) {
        String out = new String();
        for (Unit u: units) {
            String test = u.getType() + " " + u.getId();
            if (test.equals(unitString)) {
                UnitStats stats = u.getUnitStats();
                out = "Offensive Damage: " + stats.getOffensiveDamage()
                        + "\nDefensive Damage: " + stats.getDefensiveDamage()
                        + "\nArmor: " + stats.getArmor()
                        + "\nMovement: " + stats.getMovement()
                        + "\nHealth: " + stats.getHealth()
                        + "\nUpkeep: " + stats.getUpkeep();
                if (u.getArmy() != null) {
                    out += "\nArmy: " + u.getArmy().getName();
                } else {
                    out += "\nArmy: none";
                }
                return out;
            }
        }
        out = "Unit not found";
        return out;
    }

    public void setUnits(ArrayList<Unit> unitList) {
        this.units = unitList.toArray(new Unit[unitList.size()]);
        repaint();
    }

    public void displayMessage(String s) {
        JOptionPane.showMessageDialog(unitOverviewComponents, s, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public DefaultListModel<String> getArmyModel() {
        return this.armyModel;
    }

    public void setQueueModel(ArrayList<Command> commands) {
        queueModel.clear();
        for (Command c : commands) {
            queueModel.addElement(c.getCommandString());
        }
    }

    //Getter methods for every button to be acccessed by the UnitScreenController
    public JButton getAddArmyButton() {
        return addArmy;
    }
    public JButton getDisbandArmyButton() {
        return decomissionArmy;
    }
    public JButton getAddUnitButton() { return addUnit;}
    public JButton getRemoveUnitButton() { return removeUnit;}

    //Getter Methods for every list to be accessed by UnitScreenController
    public JList getUnitList() {
        return unitList;
    }
    public JList<String> getArmyList() {return armyList;}
    public JList getUnitsinArmyList() {return unitsInArmyList;}

    public JButton getCancelCommand() {
        return cancelCommand;
    }

    public JButton getMoveOrderUp() {
        return moveOrderUp;
    }

    public JButton getMoveOrderDown() {
        return moveOrderDown;
    }
}