package com.team7.view;

import com.team7.objects.Player;
import com.team7.objects.Tile;
import com.team7.objects.unit.Unit;
import com.team7.objects.unit.UnitStats;
import com.team7.objects.unit.nonCombatUnit.Colonist;
import com.team7.objects.unit.nonCombatUnit.Explorer;

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
        repaint();
    }

    public ScreenSelectButtons getScreenSelectButtons() {
        return screenSelectBtns;
    }

    private void addElements(Container pane) {
        JPanel temp = new JPanel();

        list1.setModel(new AbstractListModel() {
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
        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                changeListVal(e);
            }
        });

        textArea.setColumns(35);
        textArea.setRows(15);
        textArea.setEditable(false);
        temp.setLayout(new SpringLayout());
        jScrollPane = new JScrollPane(list1);
        temp.setPreferredSize(new Dimension(400, 800));
        JPanel scrollBox = new JPanel();
        scrollBox.add(jScrollPane);
        //list1.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list1.setVisibleRowCount(10);
        JPanel textBox = new JPanel();
        textBox.setPreferredSize(new Dimension(400,800));
        textBox.add(textArea);
        JPanel unitOverveiwComponents = new JPanel();
        unitOverveiwComponents.setLayout(new BoxLayout(unitOverveiwComponents, BoxLayout.LINE_AXIS));
        unitOverveiwComponents.add(scrollBox);
        unitOverveiwComponents.add(textBox);
        scrollBox.setMaximumSize(new Dimension(400, Short.MAX_VALUE));
        textBox.setMaximumSize(new Dimension(400, Short.MAX_VALUE));
        pane.add(unitOverveiwComponents);
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
            if (test.equals(unitString)) {
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