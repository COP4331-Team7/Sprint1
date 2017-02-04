package com.team7.view;

import com.team7.objects.Army;
import com.team7.objects.CommandQueue;
import com.team7.objects.Player;
import com.team7.objects.Tile;
import com.team7.objects.unit.Unit;
import com.team7.objects.unit.UnitStats;
import com.team7.objects.unit.nonCombatUnit.Colonist;
import com.team7.objects.unit.nonCombatUnit.Explorer;
import com.team7.objects.unit.nonCombatUnit.NonCombatUnit;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;

public class UnitScreen extends JPanel {

    private ScreenSelectButtons screenSelectBtns = null;
    private JScrollPane jScrollPane = null;
    private JScrollPane queueScrollPane;
    private Unit[] units;
    private JList list1 = new JList();
    private JList queue = new JList();
    private JTextArea textArea = new JTextArea();

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
        list1.setModel(new AbstractListModel() {
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
            public Object getElementAt(int index) {
                return units[index].getType() + " " + units[index].getId();
            }
        });
        queue.setModel(new AbstractListModel() {
            @Override
            public int getSize() {
                return 0;
            }

            @Override
            public Object getElementAt(int index) {
                return null;
            }
        });
        //Listens for any changes in selected unit
        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                changeListVal(e);
            }
        });

        //Create Command Queue
        queueScrollPane = new JScrollPane(queue);
        queueScrollPane.setMaximumSize(new Dimension(200,400));
        queueScrollPane.setPreferredSize(new Dimension(200, 200));
        queuePane.setMaximumSize(new Dimension(200,400));
        JLabel queueLabel = new JLabel("Command Queue");
        queuePane.add(queueLabel, BorderLayout.NORTH);
        queuePane.add(queueScrollPane);
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
        jScrollPane = new JScrollPane(list1);
        jScrollPane.setPreferredSize(new Dimension(200, 200));
        JPanel scrollBox = new JPanel(new BorderLayout());
        scrollBox.setMaximumSize(new Dimension(200,400));
        JLabel unitSelectLabel = new JLabel("Unit List", SwingConstants.CENTER);
        scrollBox.add(unitSelectLabel, BorderLayout.NORTH);
        scrollBox.add(jScrollPane);
        list1.setVisibleRowCount(10);

        //Format Army Select Box
        JPanel ArmyFormatter = new JPanel(new GridLayout(1,3));
        ArmyFormatter.add(new JLabel());
        ArmySelectButtons buttons = new ArmySelectButtons();
        ArmyFormatter.add(buttons);
        ArmyFormatter.add(new JLabel());



        //Format and add all components to the data pane
        JPanel unitOverviewComponents = new JPanel();
        unitOverviewComponents.setLayout(new GridLayout(2,3));
        unitOverviewComponents.add(scrollBox);
        unitOverviewComponents.add(queueScrollPane);
        unitOverviewComponents.add(textBox);
        unitOverviewComponents.add(ArmyFormatter);
        unitOverviewComponents.add(new JLabel());
        unitOverviewComponents.add(new JLabel());

        scrollBox.setPreferredSize(new Dimension(400, 500));
        textBox.setMaximumSize(new Dimension(400, 400));
        queueScrollPane.setPreferredSize(new Dimension(200,200));
        queueScrollPane.revalidate();
        scrollBox.revalidate();
        textBox.revalidate();
        pane.add(unitOverviewComponents);
        repaint();
    }

    private void changeListVal(javax.swing.event.ListSelectionEvent e) {
        String s = (String)list1.getSelectedValue();
        String stats = getStats(s);
        textArea.setText(stats);
    }

    //--TODO-- Missing functionallity in unit to complete this function
    private void setQueueBox(Unit u) {
        CommandQueue q =  new CommandQueue();
        if (u instanceof NonCombatUnit) {
            //Fetch command queue from unit directly
        } else {
            //Fetch command from this unit's army
        }
    }

    //Finds Unit stats and Unit queue, otherwise display error message
    private String getStats(String unitString) {
        String out = new String();
        for (Unit u: units) {
            String test = u.getType() + " " + u.getId();
            if (test.equals(unitString)) {
                setQueueBox(u);
                UnitStats stats = u.getUnitStats();
                out = "Offensive Damage: " + stats.getOffensiveDamage()
                        + "\nDefensive Damage: " + stats.getDefensiveDamage()
                        + "\nArmor: " + stats.getArmor()
                        + "\nMovement: " + stats.getMovement()
                        + "\nHealth: " + stats.getHealth()
                        + "\nUpkeep: " + stats.getUpkeep();
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
}