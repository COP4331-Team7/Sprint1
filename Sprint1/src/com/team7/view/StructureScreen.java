package com.team7.view;

import com.team7.objects.structure.Structure;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StructureScreen extends JPanel {

    private ScreenSelectButtons screenSelectBtns = null;
    private JScrollPane jScrollPane = null;

    //Lists
    private JList structureList = new JList();
    private JList structureCommands = new JList();

    //Models
    private DefaultListModel strucListModel = new DefaultListModel();
    private DefaultListModel comListModel = new DefaultListModel();

    //Buttons
    private JButton produceUnit = new JButton("PRODUCE UNIT");
    private JButton cancelCommand = new JButton("CANCEL COMMAND");
    private JButton moveOrderUp = new JButton("⟰");
    private JButton moveOrderDown = new JButton("⟱");

    //Data to display
    private ArrayList<Structure> structures;

    public StructureScreen() {

        this.setLayout(new BorderLayout());

        screenSelectBtns = new ScreenSelectButtons();

        this.add( screenSelectBtns, BorderLayout.NORTH );

        //Overarching panel
        JPanel infoPane = new JPanel(new GridLayout(1,2));

        //3 Panels that fit into infoPane
        JPanel buildingListPanel = new JPanel(new BorderLayout());
        JPanel commandPanel = new JPanel(new BorderLayout());
        JPanel statsPanel = new JPanel(new BorderLayout());

        //Labels for each Panel
        JLabel buildingListLabel = new JLabel("Structure List", SwingConstants.CENTER);
        JLabel commandPanelLabel = new JLabel("Commands", SwingConstants.CENTER);
        JLabel statsPanelLabel = new JLabel("Stats:", SwingConstants.CENTER);

        //Add Labels to their panels
        buildingListPanel.add(buildingListLabel, BorderLayout.NORTH);
        commandPanel.add(commandPanelLabel, BorderLayout.NORTH);
        statsPanel.add(statsPanelLabel, BorderLayout.NORTH);

        //Format Structure List
        JScrollPane scrollStrucList = new JScrollPane(structureList);
        buildingListPanel.add(scrollStrucList, BorderLayout.CENTER);

        //Format Command List
        JScrollPane commandScrollPane = new JScrollPane(structureCommands);
        commandPanel.add(commandScrollPane, BorderLayout.CENTER);
        JPanel commandButtonPanel = new JPanel(new BorderLayout());
        commandButtonPanel.add(cancelCommand, BorderLayout.CENTER);
        commandButtonPanel.add(moveOrderDown, BorderLayout.LINE_END);
        commandButtonPanel.add(moveOrderUp, BorderLayout.LINE_START);
        commandButtonPanel.add(produceUnit, BorderLayout.PAGE_END);
        commandPanel.add(commandButtonPanel, BorderLayout.SOUTH);

        //Format Stats box
        JTextArea statsTextArea = new JTextArea();
        statsPanel.add(statsTextArea, BorderLayout.CENTER);


        //Attribute Panel on right, listPanel on left
        JPanel attributePanel = new JPanel(new GridLayout(2, 1));
        JPanel listPanel = new JPanel(new BorderLayout());

        listPanel.add(buildingListPanel, BorderLayout.CENTER);
        attributePanel.add(commandPanel);
        attributePanel.add(statsPanel);

        infoPane.add(listPanel);
        infoPane.add(attributePanel);

        this.add(infoPane, BorderLayout.CENTER);
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

    public void setStructures(ArrayList<Structure> structures) {
        this.structures = structures;
    }
}
