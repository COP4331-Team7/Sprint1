package com.team7.Controller;

import com.team7.View.*;
import com.team7.objects.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeScreenController {
    private Game game;
    private View view;

    public HomeScreenController(Game game, View view) {
        this.game = game;
        this.view = view;

        addActionListeners();

    }

    private void addActionListeners() {
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
    }

    public void createMap(){
        view.getFrame().getMainScreen();
    }
}
