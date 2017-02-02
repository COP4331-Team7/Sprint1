package com.team7.Controller;

import com.team7.View.*;
import com.team7.objects.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeScreenController {
    private Game game;
    private MyFrame view;

    public HomeScreenController(Game game, MyFrame view) {
        this.game = game;
        this.view = view;

        addActionListeners();

    }

    private void addActionListeners() {
        // add ActionListeners to home screen buttons
        view.getHomeScreen().getHomeButtons().getQuitButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == view.getHomeScreen().getHomeButtons().getQuitButton()) {
                    System.exit( 0 );
                }
            }
        } );
        view.getHomeScreen().getHomeButtons().getPlayButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == view.getHomeScreen().getHomeButtons().getPlayButton()) {
                    SwingUtilities.invokeLater(new Runnable(){
                        public void run(){
                            view.setCurrScreen( "MAIN" );
                        }
                    });
                }
            }
        } );
    }

    public void createMap(){
        view.getMainScreen();
    }
}
