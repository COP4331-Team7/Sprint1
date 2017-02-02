package GUI_src;

import com.team7.Main;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class View
{
    private static final int WIDTH  = 800;
    private static final int HEIGHT = 800;
    private MyFrame frame = null;

    public View() 
    {
        // SwingUtilities.invokeLater causes the Runnable to be executed asynchronously on the Event Dispatch Thread:
        // It queues up a task (GUI update) on the EDT and instantly returns.
        // Used to prevent long tasks from freezing up the GUI
        SwingUtilities.invokeLater( new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        } );
    }
    
    // Create the GUI and show it.
    private void createAndShowGUI()
    {
        frame = new MyFrame( WIDTH, HEIGHT);                       // setup new frame
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );    // exit when the user closes the frame
        frame.setVisible( true );                                  // make the frame visible
    }

    public MyFrame getFrame() {
        return frame;
    }
}


