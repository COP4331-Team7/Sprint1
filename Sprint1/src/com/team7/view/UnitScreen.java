package com.team7.view;

import com.team7.objects.unit.Unit;
import com.team7.objects.unit.UnitStats;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;

public class UnitScreen extends JPanel {

    private ScreenSelectButtons screenSelectBtns = null;
    private JScrollPane jScrollPane = null;
    private Unit[] units;
    private JList list1 = new JList();
    private JTextArea textArea = new JTextArea();

    public UnitScreen() {

        this.setLayout(new BorderLayout());

        screenSelectBtns = new ScreenSelectButtons();
        addElements(this);

        this.add(screenSelectBtns, BorderLayout.NORTH);
    }

    public ScreenSelectButtons getScreenSelectButtons() {
        return screenSelectBtns;
    }

    private void addElements(Container pane) {
        JPanel temp = new JPanel();

        list1.setModel(new AbstractListModel() {
            @Override
            public int getSize() {
                return units.length;
            }

            @Override
            public Object getElementAt(int index) {
                return units[index].getType() + " " + units[index].getId();
            }
        });
        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                changeListVal(e);
            }
        });

        textArea.setColumns(35);
        textArea.setRows(10);
        temp.setLayout(new BorderLayout());
        jScrollPane = new JScrollPane(temp);
        jScrollPane.add(list1);
        temp.setPreferredSize(new Dimension(800, 800 * 2));
        JPanel scrollBox = new JPanel();
        scrollBox.add(jScrollPane);
        JPanel textBox = new JPanel();
        textBox.setPreferredSize(new Dimension(800,800));
        textBox.add(textArea);
        pane.add(textBox, BorderLayout.EAST);
        pane.add(scrollBox, BorderLayout.WEST);
    }

    private void changeListVal(javax.swing.event.ListSelectionEvent e) {
        String s = (String)list1.getSelectedValue();
        String stats = getStats(s);
        textArea.setText(stats);
    }

    private String getStats(String unitString) {
        String out = new String();
        for (Unit u: units) {
            String test = u.getType() + " " + u.getId();
            if (test == unitString) {
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
    }
}