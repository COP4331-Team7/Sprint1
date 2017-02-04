package com.team7.view;

import com.team7.objects.Player;
import com.team7.objects.structure.Structure;
import com.team7.objects.unit.Unit;
import com.team7.objects.unit.UnitStats;

import javax.swing.*;
import java.awt.*;

public class MainViewInfo extends JPanel {

    private Unit unit = null;
    private Structure structure = null;

    private JLabel offensiveDamageLabel;
    private JLabel defensiveDamageLabel;
    private JLabel armorLabel;
    private JLabel healthLabel;
    private JLabel upkeepLabel;
    private JLabel productionLabel;
    private JLabel itemLabel;
    private JLabel typeLabel;

        public MainViewInfo()
        {
            JPanel t = new JPanel();
            t.setLayout(new GridLayout(0,1));
            JLabel p = new JLabel("Statistics:");
            p.setFont(new Font("Serif", Font.BOLD, 18));

            t.add(p);   // large first label so the panel takes up more space and is spaced out
            typeLabel= new JLabel("Unit/Structure: ");
            t.add(typeLabel);
            offensiveDamageLabel = new JLabel("Offensive damage:");
            t.add(offensiveDamageLabel);
            defensiveDamageLabel = new JLabel("Defensive damage:");
            t.add(defensiveDamageLabel);
            armorLabel = new JLabel("Armor:");
            t.add(armorLabel);
            healthLabel = new JLabel("Health:");
            t.add(healthLabel);
            upkeepLabel = new JLabel("Upkeep:");
            t.add(upkeepLabel);
            productionLabel = new JLabel("Production rates:");
            t.add(productionLabel);
            itemLabel = new JLabel("Items:");
            t.add(itemLabel);
            this.add( t, BorderLayout.SOUTH );

        }


    public void setUnit(Unit unit) {
        if(unit == null)
            clearStats();
        else
            updateStats( unit );
    }

    public void setStructure(Structure structure) {
        if(structure == null)
            clearStats();
        else
            updateStats( structure );
    }

    public void clearStats() {
        offensiveDamageLabel.setText("Offensive damage:");
        defensiveDamageLabel.setText("Defensive damage:");
        healthLabel.setText("Health");
        armorLabel.setText("Armor");
        upkeepLabel.setText("Upkeep");
        productionLabel.setText("Production rates:");
        itemLabel.setText("Items");
        typeLabel.setText("Unit/Structure: ");
    }

    public  void updateStats( Structure structure ) {

    }
    public  void updateStats( Unit unit ) {
        UnitStats stats = unit.getUnitStats();
        setOffensiveDamageLabel( Integer.toString( stats.getOffensiveDamage() ) );
        setDefensiveDamageLabel( Integer.toString( stats.getDefensiveDamage() ) );
        setArmorLabel( Integer.toString( stats.getArmor() ) );
        setHealthLabel( Integer.toString( stats.getHealth() ) );
        // setUpkeepLabel( Integer.toString( stats.getUpkeep() ) );
        setProductionLabel( "N/A" );
        setUpkeepLabel(  Integer.toString( stats.getUpkeep() ) );
        // setItemLabel( );


    }
    public void setOffensiveDamageLabel(String s) {
            offensiveDamageLabel.setText("Offensive damage: " + s);
    }
    public void setDefensiveDamageLabel(String s) {
        defensiveDamageLabel.setText("Defensive damage: " + s);
    }
    public void setArmorLabel(String s) {
        armorLabel.setText("Armor: " + s);
    }
    public void setHealthLabel(String s) {
        healthLabel.setText("Health: " + s);
    }
    public void setUpkeepLabel(String s) {
        upkeepLabel.setText("Upkeep: " + s);
    }
    public void setProductionLabel(String s) {
        productionLabel.setText("Production rates: " + s);
    }
    public void setItemLabel(String s) {
        itemLabel.setText("Items: " + s);
    }


}
