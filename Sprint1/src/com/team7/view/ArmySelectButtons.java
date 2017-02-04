package com.team7.view;

import javax.swing.*;
import java.awt.*;


public class ArmySelectButtons extends JPanel {
    ArmySelectButtons() {
        JPanel pane = new JPanel(new GridLayout(4, 3));
        //Init Buttons
        JButton A1 = new JButton("Army 1");
        JButton A2 = new JButton("Army 2");
        JButton A3 = new JButton("Army 3");
        JButton A4 = new JButton("Army 4");
        JButton A5 = new JButton("Army 5");
        JButton A6 = new JButton("Army 6");
        JButton A7 = new JButton("Army 7");
        JButton A8 = new JButton("Army 8");
        JButton A9 = new JButton("Army 9");
        JButton A10 = new JButton("Army 10");

        pane.add(A1);
        pane.add(A2);
        pane.add(A3);
        pane.add(A4);
        pane.add(A5);
        pane.add(A6);
        pane.add(A7);
        pane.add(A8);
        pane.add(A9);
        pane.add(A10);

    }
}
