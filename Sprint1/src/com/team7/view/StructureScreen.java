package com.team7.view;

import javax.swing.*;
import java.awt.*;

public class StructureScreen extends JPanel {

    private ScreenSelectButtons screenSelectBtns = null;
    private JScrollPane jScrollPane = null;

    public StructureScreen() {

        this.setLayout(new BorderLayout());

        screenSelectBtns = new ScreenSelectButtons();
        addScrollPane( this );

        this.add( screenSelectBtns, BorderLayout.NORTH );
    }

    public ScreenSelectButtons getScreenSelectButtons() {
            return screenSelectBtns;
    }

    private void addScrollPane(Container pane) {
            JPanel temp = new JPanel();
            temp.setLayout( new BorderLayout() );
            jScrollPane = new JScrollPane(temp);
            temp.setPreferredSize( new Dimension(800, 800*2) );
            pane.add( jScrollPane  );
    }
}
