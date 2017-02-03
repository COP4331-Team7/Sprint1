package com.team7.controller;

import com.team7.view.*;
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
        view.getScreen().getHomeScreen().getHomeButtons().getQuitButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == view.getScreen().getHomeScreen().getHomeButtons().getQuitButton()) {
                    System.exit( 0 );
                }
            }
        } );
        view.getScreen().getHomeScreen().getHomeButtons().getPlayButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == view.getScreen().getHomeScreen().getHomeButtons().getPlayButton()) {
                    SwingUtilities.invokeLater(new Runnable(){
                        public void run(){
                            view.getScreen().setCurrScreen( "MAIN" );
                        }
                    });
                }
            }
        } );
    }

    public void createMap(){
        view.getScreen().getMainScreen();
    }
}
