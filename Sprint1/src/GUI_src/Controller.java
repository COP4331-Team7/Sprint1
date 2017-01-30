import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

class Controller
{
     // private Model model;
     private View view;

     public Controller(View view)
     {
        this.view = view;
        addActionListeners(); 
     } 

     public void addActionListeners()
     {
        while( view.getFrame() == null ) {      // waiting on view construction
            System.out.println("waiting ...");
            try   { Thread.sleep(50);  }
            catch (Exception e)  {}
        }
    
        // add ActionListeners to home screen buttons
        view.getFrame().getHomeScreen().getHomeButtons().getQuitButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 if(e.getSource() == view.getFrame().getHomeScreen().getHomeButtons().getQuitButton()) {
                    System.exit( 0 );
                  }
             }
        } );
        view.getFrame().getHomeScreen().getHomeButtons().getPlayButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 if(e.getSource() == view.getFrame().getHomeScreen().getHomeButtons().getPlayButton()) {
                         SwingUtilities.invokeLater(new Runnable(){
                         public void run(){
                            view.getFrame().setCurrScreen( "MAIN" );
                         }
                         });
                  }
             }
        } );
                
        // add ActionListeners screen selection buttons on MainScreen
        view.getFrame().getMainScreen().getScreenSelectButtons().getMainScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 if(e.getSource() == view.getFrame().getMainScreen().getScreenSelectButtons().getMainScreenButton()) {
                    view.getFrame().setCurrScreen( "MAIN" );
                  }
             }
        } );
        view.getFrame().getMainScreen().getScreenSelectButtons().getUnitScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 if(e.getSource() == view.getFrame().getMainScreen().getScreenSelectButtons().getUnitScreenButton()) {
                    view.getFrame().setCurrScreen( "UNIT_OVERVIEW" );
                  }
             }
        } );
        view.getFrame().getMainScreen().getScreenSelectButtons().getStructureScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 if(e.getSource() == view.getFrame().getMainScreen().getScreenSelectButtons().getStructureScreenButton()) {
                    view.getFrame().setCurrScreen( "STRUCTURE_OVERVIEW" );
                  }
             }
        } );
        // add ActionListeners screen selection buttons on UnitScreen
        view.getFrame().getUnitScreen().getScreenSelectButtons().getMainScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 if(e.getSource() == view.getFrame().getUnitScreen().getScreenSelectButtons().getMainScreenButton()) {
                    view.getFrame().setCurrScreen( "MAIN" );
                  }
             }
        } );
        view.getFrame().getUnitScreen().getScreenSelectButtons().getUnitScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 if(e.getSource() == view.getFrame().getUnitScreen().getScreenSelectButtons().getUnitScreenButton()) {
                    view.getFrame().setCurrScreen( "UNIT_OVERVIEW" );
                  }
             }
        } );
        view.getFrame().getUnitScreen().getScreenSelectButtons().getStructureScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 if(e.getSource() == view.getFrame().getUnitScreen().getScreenSelectButtons().getStructureScreenButton()) {
                    view.getFrame().setCurrScreen( "STRUCTURE_OVERVIEW" );
                  }
             }
        } );
        // add ActionListeners screen selection buttons on StructureScreen
        view.getFrame().getStructureScreen().getScreenSelectButtons().getMainScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 if(e.getSource() == view.getFrame().getStructureScreen().getScreenSelectButtons().getMainScreenButton()) {
                    view.getFrame().setCurrScreen( "MAIN" );
                  }
             }
        } );
        view.getFrame().getStructureScreen().getScreenSelectButtons().getUnitScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 if(e.getSource() == view.getFrame().getStructureScreen().getScreenSelectButtons().getUnitScreenButton()) {
                    view.getFrame().setCurrScreen( "UNIT_OVERVIEW" );
                  }
             }
        } );
        view.getFrame().getStructureScreen().getScreenSelectButtons().getStructureScreenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 if(e.getSource() == view.getFrame().getStructureScreen().getScreenSelectButtons().getStructureScreenButton()) {
                    view.getFrame().setCurrScreen( "STRUCTURE_OVERVIEW" );
                  }
             }
        } );

     }

}