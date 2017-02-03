package com.team7.view;

import com.team7.objects.Player;

import javax.swing.*;
import java.awt.*;

public class MainViewInfo extends JPanel {

    private Player currentPlayer;

        public MainViewInfo()
        {
            JPanel t = new JPanel();
            t.setLayout(new GridLayout(0,1));
            JLabel p = new JLabel("Statistics:");
            p.setFont(new Font("Serif", Font.BOLD, 18));

            t.add(p);   // large first label so the panel takes up more space and is spaced out
            JLabel tt7 = new JLabel("Unit/Structure:      ");
            t.add(tt7);
            JLabel tt = new JLabel("Offensive damage:");
            t.add(tt);
            JLabel tt1 = new JLabel("Defensive damage:");
            t.add(tt1);
            JLabel tt2 = new JLabel("Armor:");
            t.add(tt2);
            JLabel tt3 = new JLabel("Health:");
            t.add(tt3);
            JLabel tt4 = new JLabel("Upkeep:");
            t.add(tt4);
            JLabel tt5 = new JLabel("Production rates:");
            t.add(tt5);
            JLabel tt6 = new JLabel("Items:");
            t.add(tt6);
            this.add( t, BorderLayout.SOUTH );

        }

    public void setCurrentPlayer( Player player ) {
        this.currentPlayer = player;
    }
}
